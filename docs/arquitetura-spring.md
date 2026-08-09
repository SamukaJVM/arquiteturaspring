# Arquitetura e Estrutura do Framework Spring

Esta seção apresenta os fundamentos da **arquitetura do Spring e do Spring Boot**, desde o funcionamento do container e do contexto da aplicação até a construção de uma arquitetura MVC completa, passando por **Inversão de Controle (IoC)**, **Injeção de Dependências (DI)**, gerenciamento de Beans e configuração externalizada.

---

## 📚 Conteúdo

### 1. Como funciona o Spring Boot

Entendimento dos principais conceitos do Spring Boot e de como ele simplifica a criação e configuração de aplicações Java.

* O que é Spring Boot
* Convenções e configurações automáticas
* Auto Configuration
* Starters
* Configuração da aplicação
* Inicialização da aplicação
* Relação entre Spring Framework e Spring Boot

---

### 2. Composição da arquitetura do Spring

Visão geral dos principais componentes que formam o ecossistema Spring.

```text
                    Spring Framework
                          │
        ┌─────────────────┼─────────────────┐
        │                 │                 │
       Core              Web              Data
        │                 │                 │
     IoC / DI       Spring MVC       Spring Data
        │                 │                 │
     Beans          Controllers        JPA
        │                 │
        └────────────┬────┘
                     │
                Spring Boot
                     │
            Auto Configuration
                     │
             Aplicação Java
```

Principais conceitos:

* Spring Core
* IoC Container
* Dependency Injection
* Beans
* Spring MVC
* Spring Data
* Spring Boot
* Auto Configuration

---

### 3. O que é o Container Spring e seus componentes

O **Spring Container** é responsável por criar, configurar, gerenciar e disponibilizar os objetos utilizados pela aplicação.

Entre suas principais responsabilidades estão:

* Instanciar Beans
* Configurar Beans
* Gerenciar o ciclo de vida dos Beans
* Resolver dependências
* Realizar Injeção de Dependências
* Controlar escopos
* Gerenciar o contexto da aplicação

O container trabalha principalmente através do:

```text
ApplicationContext
        │
        ├── Bean Definitions
        ├── Beans
        ├── Dependency Injection
        ├── Configuration
        └── Lifecycle Management
```

---

### 4. Entendendo a classe Application

A classe principal da aplicação Spring Boot normalmente contém o método:

```java
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

O método:

```java
SpringApplication.run(...)
```

é responsável por iniciar a aplicação e criar o **Application Context**.

A anotação:

```java
@SpringBootApplication
```

combina funcionalidades importantes do Spring:

```text
@SpringBootApplication
        │
        ├── @SpringBootConfiguration
        ├── @EnableAutoConfiguration
        └── @ComponentScan
```

---

### 5. Criando o modelo do contexto

O **Application Context** representa o contexto no qual a aplicação Spring é executada.

De forma simplificada:

```text
Application
     │
     ▼
SpringApplication.run()
     │
     ▼
ApplicationContext
     │
     ├── Bean A
     ├── Bean B
     ├── Bean C
     ├── Service
     ├── Repository
     └── Controller
```

O contexto mantém os Beans disponíveis para serem utilizados pelas diferentes partes da aplicação.

---

### 6. Criando uma Configuration e registrando um Bean

É possível registrar Beans manualmente utilizando:

```java
@Configuration
public class AppConfig {

    @Bean
    public MeuServico meuServico() {
        return new MeuServico();
    }
}
```

### `@Configuration`

Indica que a classe contém configurações da aplicação.

### `@Bean`

Indica que o objeto retornado pelo método deve ser gerenciado pelo Spring Container.

Fluxo:

```text
@Configuration
       │
       ▼
     @Bean
       │
       ▼
Spring Container
       │
       ▼
   MeuServico
```

---

### 7. Testando o fluxo através do Postman

Após criar uma aplicação Spring MVC, podemos expor um endpoint HTTP e testar seu funcionamento utilizando o **Postman**.

Exemplo:

```text
Postman
   │
   │ HTTP Request
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Repository
   │
   ▼
Database
```

Exemplo de requisição:

```http
GET http://localhost:8080/produtos
```

O Postman permite verificar se o fluxo completo da aplicação está funcionando corretamente.

---

# 🔗 Injeção de Dependências e Beans

## 8. Utilizando Qualifiers

Quando existem múltiplos Beans do mesmo tipo, o Spring pode não saber qual deles deve ser injetado.

Exemplo:

```java
@Bean
public Pagamento pagamentoPix() {
    return new PagamentoPix();
}

@Bean
public Pagamento pagamentoCartao() {
    return new PagamentoCartao();
}
```

Nesse cenário, podemos utilizar `@Qualifier`:

```java
@Autowired
@Qualifier("pagamentoPix")
private Pagamento pagamento;
```

O `@Qualifier` permite indicar explicitamente qual Bean deve ser utilizado.

---

## 9. Beans Primary e annotations de Qualifiers

Outra alternativa é utilizar:

```java
@Primary
```

Exemplo:

```java
@Bean
@Primary
public Pagamento pagamentoPix() {
    return new PagamentoPix();
}
```

Quando houver mais de uma implementação, o Bean marcado com `@Primary` será considerado a opção padrão.

### Comparação

| Recurso      | Função                                        |
| ------------ | --------------------------------------------- |
| `@Primary`   | Define o Bean padrão                          |
| `@Qualifier` | Seleciona explicitamente um Bean              |
| `@Bean`      | Registra um objeto no container               |
| `@Component` | Registra automaticamente uma classe como Bean |

---

# 🏗️ Arquitetura MVC

## 10. Estudando a estrutura de um projeto MVC

Uma aplicação Spring MVC pode ser organizada em diferentes camadas.

```text
src/main/java
│
└── com.exemplo.app
    │
    ├── controller
    │
    ├── service
    │
    ├── repository
    │
    ├── entity
    │
    ├── dto
    │
    └── config
```

Cada camada possui uma responsabilidade específica.

```text
Cliente
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Repository
   │
   ▼
Database
```

---

## 11. Iniciando pela entidade

A **Entity** representa os dados e, quando utilizando JPA, normalmente corresponde a uma tabela do banco de dados.

Exemplo:

```java
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private BigDecimal preco;
}
```

Responsabilidade principal:

* Representar o modelo persistente
* Mapear tabelas do banco
* Definir atributos
* Definir relacionamentos

---

## 12. Codificando as camadas da arquitetura completa

Uma arquitetura MVC tradicional pode ser organizada da seguinte maneira:

```text
┌─────────────────────────┐
│        Controller       │
│   Entrada HTTP / REST   │
└────────────┬────────────┘
             │
             ▼
┌─────────────────────────┐
│         Service         │
│     Regras de negócio   │
└────────────┬────────────┘
             │
             ▼
┌─────────────────────────┐
│       Repository        │
│   Acesso aos dados      │
└────────────┬────────────┘
             │
             ▼
┌─────────────────────────┐
│        Database         │
└─────────────────────────┘
```

### Controller

Responsável pela comunicação HTTP.

```java
@RestController
@RequestMapping("/produtos")
public class ProdutoController {
}
```

### Service

Responsável pelas regras de negócio.

```java
@Service
public class ProdutoService {
}
```

### Repository

Responsável pelo acesso aos dados.

```java
@Repository
public interface ProdutoRepository
        extends JpaRepository<Produto, Long> {
}
```

### Entity

Representa o modelo persistente:

```java
@Entity
public class Produto {
}
```

---

## 13. Demonstrando a arquitetura em funcionamento

Fluxo completo de uma requisição:

```text
┌────────────┐
│   Cliente  │
└─────┬──────┘
      │ HTTP
      ▼
┌────────────┐
│ Controller │
└─────┬──────┘
      │
      ▼
┌────────────┐
│  Service   │
└─────┬──────┘
      │
      ▼
┌────────────┐
│ Repository │
└─────┬──────┘
      │
      ▼
┌────────────┐
│  Database  │
└────────────┘
```

O retorno percorre o caminho inverso:

```text
Database
   │
   ▼
Repository
   │
   ▼
Service
   │
   ▼
Controller
   │
   ▼
Cliente
```

---

# 🧩 Componentes e Injeção de Dependências

## 14. Trabalhando com Components

O Spring disponibiliza diversas annotations para registrar componentes automaticamente.

Principais:

```java
@Component
@Service
@Repository
@Controller
@RestController
```

Todas estão relacionadas ao mecanismo de **component scanning** do Spring.

Exemplo:

```java
@Component
public class Calculadora {
}
```

O Spring identifica a classe durante o scan e registra uma instância dela como Bean.

---

## 15. Aprofundando em Injeção de Dependências

A **Injeção de Dependências (Dependency Injection)** permite que uma classe receba suas dependências em vez de criá-las diretamente.

### Sem DI

```java
public class PedidoService {

    private PagamentoService pagamentoService =
        new PagamentoService();
}
```

### Com DI

```java
@Service
public class PedidoService {

    private final PagamentoService pagamentoService;

    public PedidoService(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }
}
```

O Spring é responsável por fornecer a dependência.

```text
Spring Container
       │
       ├── PagamentoService
       │
       └── PedidoService
               │
               └── recebe PagamentoService
```

A **injeção pelo construtor** é geralmente a abordagem preferida por favorecer:

* Imutabilidade
* Testabilidade
* Dependências explícitas
* Código mais previsível

---

# ♻️ Ciclo de Vida e Escopo dos Beans

## 16. Escopo dos Beans — `@Scope`

O Spring permite definir como os Beans serão criados e compartilhados.

Um dos escopos mais comuns é:

```java
@Scope("singleton")
```

No escopo `singleton`, o Spring mantém uma única instância do Bean dentro daquele Application Context.

Exemplo:

```text
ApplicationContext
       │
       ▼
   MeuService
       │
       ├── Controller A
       ├── Controller B
       └── Controller C
```

Outros escopos incluem:

* `singleton`
* `prototype`
* `request`
* `session`
* `application`
* `websocket`

---

## 17. Inicialização Lazy dos Beans

Por padrão, muitos Beans são inicializados durante a criação do contexto.

Com:

```java
@Lazy
```

podemos adiar a criação do Bean até que ele seja realmente necessário.

Exemplo:

```java
@Lazy
@Service
public class RelatorioService {
}
```

Fluxo:

```text
Aplicação inicia
       │
       ▼
Bean não é criado imediatamente
       │
       ▼
Primeiro uso
       │
       ▼
Bean é criado
```

Isso pode ser útil quando determinado componente possui uma inicialização pesada ou não é necessário imediatamente.

---

# ⚙️ Configuração Externalizada

## 18. Arquivos de configuração — `application.properties`

O Spring Boot permite separar configurações do código-fonte.

Arquivo:

```text
src/main/resources/application.properties
```

Exemplo:

```properties
server.port=8081

app.nome=Minha Aplicação
app.versao=1.0
```

Essa abordagem permite alterar configurações sem modificar diretamente o código Java.

Também é possível utilizar:

```text
application.yml
```

Exemplo:

```yaml
server:
  port: 8081

app:
  nome: Minha Aplicação
  versao: 1.0
```

---

## 19. Lendo propriedades com `@Value`

Podemos acessar propriedades do arquivo de configuração utilizando `@Value`.

`application.properties`:

```properties
app.nome=Minha Aplicação
```

Java:

```java
@Component
public class AppInfo {

    @Value("${app.nome}")
    private String nome;
}
```

O Spring injeta o valor configurado:

```text
application.properties
        │
        ▼
   app.nome
        │
        ▼
      @Value
        │
        ▼
      String
```

---

## 20. Utilizando classes de Configuration Properties

Quando temos várias propriedades relacionadas, podemos utilizar:

```java
@ConfigurationProperties
```

Exemplo:

```properties
app.nome=Minha Aplicação
app.versao=1.0
app.descricao=Sistema de gerenciamento
```

Classe:

```java
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private String nome;
    private String versao;
    private String descricao;
}
```

Nesse caso, as propriedades são agrupadas em uma classe específica.

### Vantagens

* Organização
* Tipagem
* Centralização das configurações
* Melhor manutenção
* Evita excesso de `@Value`
* Facilita configurações maiores

---

# 🧠 Conceitos Fundamentais

Ao finalizar esta seção, os principais conceitos que devem estar consolidados são:

| Conceito                   | Objetivo                                                  |
| -------------------------- | --------------------------------------------------------- |
| Spring Boot                | Simplificar a criação e configuração de aplicações Spring |
| Spring Container           | Gerenciar os objetos da aplicação                         |
| ApplicationContext         | Representar o contexto da aplicação                       |
| Bean                       | Objeto gerenciado pelo Spring                             |
| `@Configuration`           | Definir uma classe de configuração                        |
| `@Bean`                    | Registrar um Bean manualmente                             |
| `@Component`               | Registrar automaticamente um componente                   |
| `@Service`                 | Representar a camada de serviço                           |
| `@Repository`              | Representar a camada de persistência                      |
| `@Controller`              | Representar um Controller MVC                             |
| `@RestController`          | Criar Controllers REST                                    |
| Dependency Injection       | Fornecer dependências automaticamente                     |
| `@Qualifier`               | Selecionar um Bean específico                             |
| `@Primary`                 | Definir um Bean como padrão                               |
| `@Scope`                   | Definir o escopo de um Bean                               |
| `@Lazy`                    | Adiar a criação de um Bean                                |
| `@Value`                   | Ler propriedades individualmente                          |
| `@ConfigurationProperties` | Mapear grupos de propriedades                             |

---

# 🎯 Objetivo da Seção

Ao concluir este conteúdo, você deverá ser capaz de compreender **como uma aplicação Spring Boot é inicializada, como o Spring Container gerencia seus Beans e como as dependências são resolvidas**, além de conseguir estruturar uma aplicação utilizando uma arquitetura em camadas.

O fluxo fundamental a ser compreendido é:

```text
                    Spring Boot
                         │
                         ▼
                 Application Class
                         │
                         ▼
                ApplicationContext
                         │
          ┌──────────────┼──────────────┐
          │              │              │
       Controller       Service      Repository
          │              │              │
          └──────────────┼──────────────┘
                         │
                         ▼
                       Entity
                         │
                         ▼
                      Database
```

A partir dessa base, torna-se mais fácil avançar para temas como **Spring Data JPA, REST, validação, tratamento de erros, testes, segurança com Spring Security, documentação de APIs e demais recursos do ecossistema Spring**.
