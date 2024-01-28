package br.com.edu.fiap.techchallengelanchonete.domain;

import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.Cliente;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Codigo;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.DataCriacao;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Valor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Data
@ToString
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class Pedido extends DomainObject {
    private List<ItemPedido> itens;
    private Cliente cliente;
    private Pagamento pagamento;
    private StatusPedido status;
    private Codigo codigo;
    private DataCriacao data;

    public Pedido() {
        this.codigo = new Codigo();
        this.codigo.gerarCodigo();

        this.pagamento = new Pagamento(StatusPagamento.AGUARDANDO);
        this.status = StatusPedido.AGUARDANDO_PAGAMENTO;
        this.itens = new ArrayList<>();
        this.data = new DataCriacao();
    }

    public static List<Pedido> ordenarListagem(List<Pedido> pedidos) {
        List<Pedido> pedidosOrdenados = new ArrayList<>(pedidos);

        pedidosOrdenados
                .sort(Comparator
                .comparing((Pedido pedido) -> {
                    switch (pedido.getStatus()) {
                        case RECEBIDO: return 0;
                        case EM_PREPARACAO: return 1;
                        case PRONTO: return 2;
                        default: return 3;
                    }
                })
                .thenComparing(pedido -> pedido.getData().getValor()));

        pedidosOrdenados =
                pedidosOrdenados
                        .stream()
                        .filter(pedido -> pedido.getStatus() != StatusPedido.FINALIZADO && pedido.getStatus() != StatusPedido.AGUARDANDO_PAGAMENTO)
                        .collect(Collectors.toList());

        return pedidosOrdenados;
    }

    public Valor getValor() {
        Valor valor = new Valor(new BigDecimal(0));

        for (ItemPedido item: itens) {
            valor.getValor().add(item.getValor().getValor());
        }

        return valor;
    }

    public boolean validaProximoStatus(StatusPedido status) {
        var recebidoValido =
                this.status == StatusPedido.AGUARDANDO_PAGAMENTO
                        && status == StatusPedido.RECEBIDO;
        var emPreparacaoValido =
                this.status == StatusPedido.RECEBIDO
                        && status == StatusPedido.EM_PREPARACAO;
        var prontoValido =
                this.status == StatusPedido.EM_PREPARACAO
                        && status == StatusPedido.PRONTO;
        var finalizadoValido =
                this.status == StatusPedido.PRONTO
                        && status == StatusPedido.FINALIZADO;

        return recebidoValido || emPreparacaoValido || prontoValido || finalizadoValido;
    }

    public void confirmaPagamento(StatusPagamento status) {
        this.pagamento.setStatus(status);
    }
}
