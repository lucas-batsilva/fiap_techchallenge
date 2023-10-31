package br.com.edu.fiap.techchallengelanchonete.controller;

import br.com.edu.fiap.techchallengelanchonete.domain.Categoria;
import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.Cliente;
import br.com.edu.fiap.techchallengelanchonete.domain.Produto;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.CPF;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Email;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Nome;
import br.com.edu.fiap.techchallengelanchonete.usecase.ClienteUseCase;
import br.com.edu.fiap.techchallengelanchonete.usecase.ProdutoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @GetMapping("/teste")
    public String teste()
    {
        return ">> Cream cracker >>";
    }
}
