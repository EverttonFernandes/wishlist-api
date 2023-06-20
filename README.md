# Wishlist API

Este projeto consiste em uma API que representa a lista de desejos de uma pessoa usuária em um e-commerce. Através dessa API, a pessoa usuária pode realizar as seguintes operações:

- Visualizar todos os produtos da lista de desejos
- Visualizar um produto específico da lista de desejos
- Inserir novos produtos na lista de desejos
- Deletar produtos da lista de desejos quando necessário

Essa API permite que os usuários gerenciem seus produtos desejados de forma conveniente e eficiente em um ambiente de e-commerce.

## Tecnologias Utilizadas

- Java 11: [Java](https://www.java.com/)
- Spring Boot: [Spring](https://docs.spring.io)
- Gerenciador de dependências: [Gradle](https://docs.gradle.org/)
- Banco de Dados: [MongoDB](https://www.mongodb.com)
- Testes funcionais: [Jest.js](https://jestjs.io/)
- Gerenciador de Pacotes: [npm](https://www.npmjs.com/)

## Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas em seu sistema:

- Java (versão 11)
- Node.js (versão v12.18.3)
- MongoDB (versão 4.4)

## Configuração do Ambiente

Para subir a API, é necessário que o mongo esteja rodando, para isso utilize: 
```bash
service mongodb start
```
ou
```bash
sudo service mongodb start
```

## Para executar os testes de unidade:
É necessário apenas rodar o comando na raíz do projeto: 
```bash
./gradlew clean test
```

### Para instalar as dependências do teste funcional:
Dentro da pasta __functionalTest rodar o comando:
```bash
npm install
```

### Para rodar os testes funcionais:
Os testes funcionais são executados em uma instância local da wishlist-api, por padrão, que está configurada para rodar na porta 8080 (http://localhost:8080). Portanto, certifique-se de que a instância da API esteja sendo executada antes de iniciar os testes funcionais. Para executar os testes funcionais, utilize o seguinte comando:
```bash
npm run test
```