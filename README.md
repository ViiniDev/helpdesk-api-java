# Helpdesk API Java

API REST para gerenciamento de chamados de suporte. O projeto foi criado como uma vitrine backend com fluxo de atendimento, filtros, comentarios e regras de status.

## Funcionalidades

- Abertura de chamados.
- Listagem com filtros por status e prioridade.
- Consulta detalhada de chamado.
- Atribuicao de tecnico responsavel.
- Alteracao de status.
- Registro de comentarios no historico do chamado.
- Bloqueio de alteracoes em chamados fechados.
- Documentacao via Swagger UI.

## Tecnologias

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Bean Validation
- H2 Database
- PostgreSQL
- Maven
- Swagger/OpenAPI

## Como Executar

```bash
mvn clean package
java -jar target/helpdesk-api-java-0.0.1-SNAPSHOT.jar
```

Swagger:

```text
http://localhost:8080/docs
```

## Perfil PostgreSQL

```bash
mvn clean package
java -jar target/helpdesk-api-java-0.0.1-SNAPSHOT.jar --spring.profiles.active=postgres
```

Variaveis:

```text
DB_URL=jdbc:postgresql://localhost:5432/helpdesk
DB_USER=postgres
DB_PASSWORD=sua_senha
```

## Endpoints Principais

```http
POST /api/tickets
GET /api/tickets
GET /api/tickets/{id}
PATCH /api/tickets/{id}/assign
PATCH /api/tickets/{id}/status
POST /api/tickets/{id}/comments
```

## Exemplos de Requisicoes

Criar chamado:

```json
{
  "requesterName": "Ana Silva",
  "requesterEmail": "ana@email.com",
  "title": "Erro ao acessar sistema",
  "description": "Usuario relata falha ao tentar acessar o painel principal.",
  "priority": "HIGH"
}
```

Atribuir tecnico:

```json
{
  "technician": "Vinicius"
}
```

Alterar status:

```json
{
  "status": "IN_PROGRESS"
}
```

Adicionar comentario:

```json
{
  "author": "Vinicius",
  "message": "Chamado recebido e em analise inicial."
}
```

## Proximos Passos

- Adicionar autenticacao com Spring Security e JWT.
- Criar perfis de usuario, tecnico e administrador.
- Implementar testes de service e controller.
- Adicionar paginacao nas listagens.
- Criar migrations com Flyway.
