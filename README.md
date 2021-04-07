# Backend - ITI
Aplicação que faz o gerenciamento do Usuário

- Proposta aplicada: https://github.com/itidigital/backend-challenge

Desse todo vamos apenas focar no desenvolvimento da validaçao da senha do usuario
como nao existe semantica em API Rest que condiza com **validar senha**, vamos criar
um endpoint que em **teoria** deveria mudar a senha do usuario, mas como a proposta
e somente validar, vamos apenas criar uma estrutura para tal.

O Projeto foi concebido utilizando alguns conceitos de Clean Architecture, com 
uma estrutura **basica** de DDD, note o pacote raiz: br.com.iti.**password**, estamos falando 
do dominio password aqui.

criei a classe PasswordValidator.java que é um UseCase, que tem como função validar uma 
senha informada.

Estou usando o conceito de DSL para determinar o comportamento interno da classe.


**Para executar o projeto**

- Estou embeddando um maven junto ao projeto
- mvn spring-boot:run para executar a o spring
- mvn clean test para executar os testes
  

- Testes
    - Testes unitários com JUNIT5
    - Testes de integração com SpringTest e JUNIT5

