# Helpdesk API Java

API REST para gerenciamento de chamados de suporte. O projeto foi criado como uma vitrine backend com fluxo de atendimento, filtros, comentários e regras de status.

## Funcionalidades

- Abertura de chamados.
- Listagem com filtros por status e prioridade.
- Consulta detalhada de chamado.
- Atribuição de técnico responsável.
- Alteração de status.
- Registro de comentários no histórico do chamado.
- Bloqueio de alterações em chamados fechados.
- Documentação via Swagger UI.

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
mvn spring-boot:run
```

Swagger:

```text
http://localhost:8080/docs
```

## Perfil PostgreSQL

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=postgres
```

Variáveis:

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

## Exemplo de Chamado

```json
{
  "requesterName": "Ana Silva",
  "requesterEmail": "ana@email.com",
  "title": "Erro ao acessar sistema",
  "description": "Usuária relata falha ao tentar acessar o painel principal.",
  "priority": "HIGH"
}
```

## Próximos Passos

- Adicionar autenticação com Spring Security e JWT.
- Criar perfis de usuário, técnico e administrador.
- Implementar testes de service e controller.
- Adicionar paginação nas listagens.
- Criar migrations com Flyway.
