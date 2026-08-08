# Arquitetura e Estrutura do Framework

Estudo da arquitetura do Spring e dos principais mecanismos utilizados pelo Spring Boot para gerenciamento de contexto, Beans, dependências e configuração da aplicação.

## 📚 Conteúdo

### Spring Boot e Container

* Como funciona o Spring Boot
* Composição da arquitetura do Spring
* Spring Container e seus componentes
* Entendendo a classe `Application`
* Criando o modelo do `ApplicationContext`
* Criando `@Configuration` e registrando `@Bean`
* Testando o fluxo através do Postman

### Beans e Injeção de Dependências

* Utilizando `@Qualifier`
* `@Primary` e Qualifiers
* Trabalhando com `@Component`
* Aprofundando em Injeção de Dependências
* Escopo dos Beans com `@Scope`
* Inicialização Lazy com `@Lazy`

### Arquitetura MVC

* Estrutura de um projeto MVC
* Criação da Entity
* Implementação das camadas da arquitetura
* Controller
* Service
* Repository
* Demonstração do fluxo completo da aplicação

### Configuração da Aplicação

* Configuração externalizada
* `application.properties`
* Leitura de propriedades com `@Value`
* Classes de `@ConfigurationProperties`

## 🎯 Objetivo

Compreender como o **Spring Boot inicializa e gerencia uma aplicação**, como funciona o **Spring Container**, o ciclo de vida dos **Beans**, a **Injeção de Dependências** e a organização de uma aplicação utilizando **arquitetura MVC**.
