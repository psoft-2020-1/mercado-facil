# Mercado Fácil
 
Um supermercado da cidade de Campina Grande precisa de um sistema que gerencie o estoque e venda de produtos na sua loja. Neste sistema, o administrador deve obter uma visão geral e o controle sobre o funcionamento do supermercado, por exemplo, ele deve poder adicionar novos produtos, acompanhar quantas unidades do produto estão disponíveis, alterar preços, ser notificado sobre eventos críticos, gerenciar as vendas e oferecer alguns serviços personalizados para o cliente.

## User Stories já implementadas

- Eu, como administrador, gostaria de adicionar um novo produto no sistema, informando seu ID, nome, código de barra, fabricante, e categoria;
- Eu, como administrador, gostaria de consultar a disponibilidade e o preço de cada produto do supermercado;
- Eu, como administrador, gostaria de criar lotes associados aos produtos, informando a quantidade de itens disponíveis.
- Eu, como usuário comum, gostaria de adicionar compras a um carrinho, informando o ID e a quantidade de itens que quero comprar. 
- Eu, como usuário comum, gostaria de consultar os produtos no carrinho.
- Eu, como usuário comum, gostaria de comprar todos os produtos que estão no carrinho.
- Eu, como usuário comum, gostaria de poder esvaziar o carrinho.



## Estrutura básica

- Um projeto: MercadoFacil;
- Um Controller RestApiController que implementa os endpoints da API Rest.
- Dois repositórios são utilizados: ProdutoRepository, LoteRepository e Carrinho que são responsáveis por manipular as entidades Produto, Lote e Compra em um banco de dados em memória;
- O modelo é composto pelas classes Produto.java, Lote.java e Compra.java que podem ser
encontradas no pacote model;
- O pacote exceptions guarda as classes de exceções que podem ser levantadas
dentro do sistema;
- Não há implementação de frontend, mas o projeto fornece uma interface de acesso à API via swagger.

## Tecnologias
Código base gerado via [start.sprint.io](https://start.spring.io/#!type=maven-project&language=java&platformVersion=2.3.3.RELEASE&packaging=jar&jvmVersion=1.8&groupId=com.example&artifactId=EstoqueFacil&name=EstoqueFacil&description=Projeto%20Estoque%20Facil&packageName=com.example.EstoqueFacil&dependencies=web,actuator,devtools,data-jpa,h2) com as seguintes dependências:  

- Spring Web
- Spring Actuator
- Spring Boot DevTools
- Spring Data JPA
- H2 Database

## Endereços úteis

- [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- [http://localhost:8080/h2](http://localhost:8080/swagger-ui.html)
