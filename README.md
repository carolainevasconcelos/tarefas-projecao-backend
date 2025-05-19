# Tarefas Projeção - Backend

Este é o repositório do backend do projeto **Tarefas Projeção**, um sistema para organização de tarefas estudantis. Foi desenvolvido em **Java com Spring Boot**, usando **MySQL** como banco de dados.

## Instalação e execução

### 1. Clonar o repositório

git clone https://github.com/carolainevasconcelos/tarefas-projecao-backend.git

### 2. Abrir no IntelliJ (ou outra IDE)

Importe como projeto Maven
Aguarde as dependências carregarem

### 3. Configurar o banco de dados

Crie um banco de dados no MySQL chamado `tarefas_projecao` e configure o `application.properties`:

spring.datasource.url=jdbc:mysql://localhost:3306/tarefas_projecao

spring.datasource.username=seu_usuario

spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update

spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

### 4. Rodar o projeto

- mvn clean install
- mvn spring-boot:run

## Endpoints da API

Usuário
- GET /usuario → Listar todos os usuários
- POST /usuario → Cadastro de usuário  
- PUT /usuario → Edição de usuário  
- DELETE /usuario/{id} → Exclusão de usuário

Curso
- GET /curso → Listar todos os cursos
- POST /curso → Cadastro de curso
- PUT /curso/{id} → Edição de curso
- DELETE /curso/{id} → Exclusão de curso

Disciplina
- GET /disciplina → Listar todas as disciplinas
- POST /disciplina → Cadastro de disciplina
- PUT /disciplina/{id} → Edição de disciplina
- DELETE /disciplina/{id} → Exclusão de disciplina

Tarefa
- GET /tarefa → Listar todas as tarefas
- POST /tarefa → Cadastro de tarefa
- PUT /tarefa/{id} → Edição de tarefa
- DELETE /tarefa/{id} → Exclusão de tarefa

A documentação da API gerada automaticamente pode ser acessada através do Swagger UI:

http://localhost:8080/swagger-ui/index.html

## Entidades principais

- **Usuário** (Estudante ou Professor)  
- **Tarefa**  
- **Disciplina**  
- **Curso**

## Diagramas UML

Abaixo estão os links para os diagramas do projeto hospedados no Google Drive:

1. [Diagrama de Classes](https://drive.google.com/file/d/16VBlpcDVnOqG2i5MBKWSxDTCjVE-Vvym/view?usp=sharing)  
2. [Diagrama Físico](https://drive.google.com/file/d/1zUaGAI18bS0p36HumA5XJ4JOQx_JPz_j/view?usp=sharing)  
3. [Diagrama Conceitual](https://drive.google.com/file/d/1zUaGAI18bS0p36HumA5XJ4JOQx_JPz_j/view?usp=drive_link)  

## Autora

Desenvolvido por [@carolainevasconcelos](https://github.com/carolainevasconcelos) 
