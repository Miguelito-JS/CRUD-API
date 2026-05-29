# Sistema CRUD de Estoque de Veiculos

Projeto didatico orientado a objetos para gestao de estoque de veiculos, usando Java, Spring Boot, API REST e MySQL.

## Funcionalidades

- Cadastro de marcas.
- Cadastro de modelos vinculados a uma marca.
- Cadastro de veiculos com modelo, marca, ano, cor, preco, quilometragem e status.
- Consulta de veiculos com filtros por marca, modelo, faixa de preco, ano e status.
- Atualizacao completa de veiculos.
- Atualizacao rapida de preco, quilometragem e status.
- Remocao de veiculos vendidos ou descontinuados.
- Frontend simples para listar, filtrar, cadastrar, editar e remover veiculos.

## Tecnologias

- Java 8 ou superior
- Spring Boot
- Spring Web
- Spring Data JPA
- Bean Validation
- MySQL
- HTML, CSS e JavaScript

## Estrutura principal

```text
src/main/java/br/com/estoque/veiculos
- controller
- dto
- model
- repository
- service
- EstoqueVeiculosApplication.java

src/main/resources
- application.properties
- static/index.html

script_banco.sql
pom.xml
```

## Banco de dados

Abra o MySQL Workbench e execute:

```sql
script_banco.sql
```

O script cria o banco `estoque_veiculos` e as tabelas:

- `marcas`
- `modelos`
- `veiculos`

Ele tambem insere alguns dados iniciais para teste.

## Configuracao

Confira o arquivo:

```text
src/main/resources/application.properties
```

Ajuste usuario e senha do MySQL conforme seu ambiente:

```properties
spring.datasource.username=root
spring.datasource.password=191221
```

## Como executar

Na pasta do projeto, execute:

```bash
mvn spring-boot:run
.\apache-maven-3.9.16\bin\mvn.cmd spring-boot:run
```

Depois acesse:

```text
http://localhost:8080
```

## Endpoints da API

### Marcas

```http
GET /api/marcas
GET /api/marcas/{id}
POST /api/marcas
PUT /api/marcas/{id}
DELETE /api/marcas/{id}
```

Exemplo de cadastro:

```json
{
  "nome": "Toyota"
}
```

### Modelos

```http
GET /api/modelos
GET /api/modelos?marcaId=1
GET /api/modelos/{id}
POST /api/modelos
PUT /api/modelos/{id}
DELETE /api/modelos/{id}
```

Exemplo de cadastro:

```json
{
  "nome": "Corolla",
  "marcaId": 1
}
```

### Veiculos

```http
GET /api/veiculos
GET /api/veiculos?marcaId=1&modeloId=1&precoMin=100000&precoMax=150000&ano=2022&status=DISPONIVEL
GET /api/veiculos/{id}
POST /api/veiculos
PUT /api/veiculos/{id}
PATCH /api/veiculos/{id}/informacoes
DELETE /api/veiculos/{id}
```

Exemplo de cadastro:

```json
{
  "modeloId": 1,
  "ano": 2022,
  "cor": "Prata",
  "preco": 128900.00,
  "quilometragem": 32000,
  "status": "DISPONIVEL"
}
```

Exemplo de atualizacao rapida:

```json
{
  "preco": 125000.00,
  "quilometragem": 34000,
  "status": "RESERVADO"
}
```

## Status aceitos

- `DISPONIVEL`
- `RESERVADO`
- `VENDIDO`
- `DESCONTINUADO`

## Entrega em PDF

Para montar o PDF solicitado pelo professor, inclua prints de:

- Projeto aberto no VS Code ou IntelliJ.
- Banco `estoque_veiculos` criado no MySQL.
- Aplicacao rodando no terminal com `mvn spring-boot:run`.
- Tela `http://localhost:8080` listando veiculos.
- Cadastro de um veiculo.
- Filtro por marca, modelo, preco, ano ou status.
- Edicao de preco, quilometragem e status.
- Remocao de um veiculo.
- Codigos principais: entidades, repositories, services, controllers e `script_banco.sql`.

## Publicacao no GitHub

Com Git instalado, execute:

```bash
git init
git add .
git commit -m "Sistema CRUD de estoque de veiculos"
git branch -M main
git remote add origin https://github.com/SEU_USUARIO/estoque-veiculos.git
git push -u origin main
```

Depois envie o link publico do repositorio.
