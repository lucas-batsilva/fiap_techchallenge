# FIAP Tech Challenge | Pós Tech

Projeto de Gestão de Pedidos para uma lanchonete desenvolvido para avaliação do curso de Pós-graduação em Arquitetura de Software da FIAP.

# Por onde começar

A stack definida para este projeto foi a seguinte:
- Java v17
- Postgres v16.0

O projeto está configurado para ser executado através de containers Docker. Para facilitar o processo de subir todo o ambiente necessário estamos utilizando o _docker compose_, então para subir a aplicação basta executar o seguinte comando: 

```sh
docker compose up -d
```

A primeira vez que o comando for executado pode demorar um pouco pois o docker precisa baixar as imagens utilizadas nos containers, mas feito isso a aplicação estará disponível em `localhost:8080/api` 

# Documentação com Swagger

A documentação dos endpoints da API pode ser consultada através da seguinte url: `localhost:8080/api/swagger-ui/index.html`

# Estrutura do Projeto

Com o intuito de tornar a separação de responsabilidades um pouco mais "óbvia" o projeto foi subdividido em três módulos principais: **api**, **application** e **database**. O _módulo api_ é o responsável por inicializar todo o projeto, é aquele que contém a classe "Main" carregada pelo Spring, 
e tem como objetivo prover a interface de entrada/saída para os consumidores da API. O _módulo database_, como o nome sugere, está responsável pela interação com banco de dados e nesse módulo estão todas as classes responsáveis por efetuar as operações de CRUD. Por fim
_o módulo application_ é onde estão implementadas as características do negócio, nesse caso, tudo o que descreve a lanchonete usada no desafio.

# Desenho da arquitetura

* ### Requisitos do negócio:
    Os requisitos do negócio (problema) podem ser acessados através deste [link](https://miro.com/app/board/uXjVMomeqoU=/). Nele contém um conjunto de artefatos que detalham o negócio, utilizando-se de práticas do DDD.

* ### Requisitos de infraestrutura:
    Abaixo é possível visualizar o desenho de solução da aplicação, que detalha em nível de infraestrutura a abordagem utilizada, tal como decisões arquiteturais e serviços utilizados para atender o problema mencionado acima. 
    
  ![desenho-de-solucao.jpg](desenho-de-solucao.jpg)

# Como executar

Para executar chamadas aos endpoints da aplicação, é necessário que se tenha o aplicativo postman instalado. A collection do postman com as respectivas requisições pode ser encontrada no caminho `collection/Tech Challeng.postman_collection.json`. Também se faz necessário possuir o arquivo com as variáveis `collection/Tech Challeng - Test.postman_environment.json`. Após baixar os dois arquivos, basta importá-los ao postman.

Após importada a collection e o environment, ao clicar com o botão direito na collection importada, posteriormente clicando em `"Run collection"`, é possível executar todas as requisições de chamadas à aplicação de forma encadeada. Desta forma, é exibida uma visualização de testes automatizados do postman, que mostra o resultado de cada uma das execuções, comparando-os com os respectivos resultados esperados.