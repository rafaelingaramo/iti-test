# Backend - ITI
Aplicação que faz o gerenciamento do Usuário

- Proposta aplicada: https://github.com/itidigital/backend-challenge

**Para executar o projeto**

- Estou embeddando um maven junto ao projeto
- ./mvnw clean test para executar os testes
- ./mvnw spring-boot:run para executar o projeto
  
**Mudanças do modelo de avaliação**

1) foi solicitado que retornasse um boleano (true|false) para avaliar se uma senha é valida, mudei o contexto para ser mais fidedigno à um sistema de verdade, e que refletisse melhor uma API Rest


**Testes**

1) Junit5 para testes de unidade
2) SpringMockMVC para testes de integração

**Clean Architecture**
1) Estamos utilizando o conceito de clean arch para estruturar as regras de negócio
2) Classe PasswordValidator, contém somente um método (execute) para validar se uma senha é valida
3) Usamos o conceito de DSL para fazer as validações de forma apartada
4) Utilizamos REGEXP para validação individual dos requisitos de negócio
```
Nove ou mais caracteres
Ao menos 1 dígito
Ao menos 1 letra minúscula
Ao menos 1 letra maiúscula
Ao menos 1 caractere especial
Considere como especial os seguintes caracteres: !@#$%^&*()-+
Não possuir caracteres repetidos dentro do conjunto
```

5) Utilizamos o mecanismo de Exceptions para tratar o erro
6) o Handling de exceptions é feito com ControllerAdvice

**Dominio**
A estruturação de pacotes/classes é feita utilizando o **dominio** que neste cenário é password
