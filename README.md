# Post Comment API

API REST de estudo com Spring Boot + MongoDB para gerenciamento de usuarios, posts e comentarios.

## Visao geral

Este projeto expõe endpoints para:

- CRUD de usuários
- Consulta de posts por id
- Busca de posts por título
- Busca completa de posts por texto e intervalo de datas
- Listagem de posts de um usuário

O projeto usa MongoDB com documentos embutidos para autor e comentários em `Post`.

## Tecnologias

- Java 25
- Spring Boot 4.0.5
- Spring Web
- Spring Data MongoDB
- Maven Wrapper
- MongoDB

## Estrutura principal

- `src/main/java/com/example/postcommentapi/resources`
  - `UserResource.java`
  - `PostResource.java`
- `src/main/java/com/example/postcommentapi/services`
  - `UserService.java`
  - `PostService.java`
- `src/main/java/com/example/postcommentapi/repository`
  - `UserRepository.java`
  - `PostRepository.java`
- `src/main/java/com/example/postcommentapi/domain`
  - `User.java`
  - `Post.java`
- `src/main/java/com/example/postcommentapi/config`
  - `Instantiation.java`

## Modelo de dados (resumo)

### User

- `id`
- `name`
- `email`
- `posts` (`@DBRef` para `Post`)

### Post

- `id`
- `date`
- `title`
- `body`
- `author` (`AuthorDTO`)
- `comments` (`List<CommentDTO>` embutida)

### CommentDTO

- `text`
- `date`
- `author` (`AuthorDTO`)

## Configuração

Arquivo: `src/main/resources/application.properties`

```properties
spring.application.name=postcommentapi
spring.mongodb.uri=mongodb://localhost:27017/post-comment-mongo
```

## Como executar

### Pré-requisitos

- Java 25 instalado
- MongoDB rodando em `localhost:27017`

### Rodar a aplicação

```powershell
cd "C:\Users\rodri\OneDrive\Documentos\Estudos\Javinha\postcommentapi"
.\mvnw.cmd spring-boot:run
```

### Rodar testes

```powershell
cd "C:\Users\rodri\OneDrive\Documentos\Estudos\Javinha\postcommentapi"
.\mvnw.cmd test
```

## Seed de dados

A classe `Instantiation` popula dados iniciais no startup e atualmente executa:

- `userRepository.deleteAll()`
- `postRepository.deleteAll()`
- insercao de usuários, posts e comentários de exemplo

Isso significa que o banco é resetado a cada subida da aplicação.

## Endpoints

Base URL local:

```text
http://localhost:8080
```

### Users

#### Listar usuários

- `GET /users`

#### Buscar usuário por id

- `GET /users/{id}`

#### Criar usuário

- `POST /users`
- Body JSON:

```json
{
  "name": "Joao Silva",
  "email": "joao@email.com"
}
```

#### Atualizar usuário

- `PUT /users/{id}`
- Body JSON:

```json
{
  "name": "Joao Silva Atualizado",
  "email": "joao@email.com"
}
```

#### Remover usuário

- `DELETE /users/{id}`

#### Listar posts de um usuario

- `GET /users/{id}/posts`

### Posts

#### Buscar post por id

- `GET /posts/{id}`

#### Buscar por titulo

- `GET /posts/titlesearch?text=viagem`

#### Busca completa

- `GET /posts/fullsearch?text=dia&minDate=2018-01-01&maxDate=2018-12-31`

Busca em:

- `title`
- `body`
- `comments.text`

com filtro por intervalo de data.

## Erros

Excecoes de recurso nao encontrado são tratadas por `ResourceExceptionHandler` com retorno padronizado (404), ex.:

```json
{
  "timestamp": 1713915000000,
  "status": 404,
  "error": "Object not found",
  "message": "User not found. Id: ...",
  "path": "/users/..."
}
```

## Exemplo rápido com Postman

1. Execute a API.
2. Faça `GET /users` para obter ids.
3. Use um id em `GET /users/{id}/posts`.
4. Teste `GET /posts/titlesearch?text=bom`.
5. Teste `GET /posts/fullsearch?text=dia&minDate=2018-01-01&maxDate=2018-12-31`.

