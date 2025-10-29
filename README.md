# 🎓 FeedMaker API

API desenvolvida em **Spring Boot** para gerenciar feedbacks entre **alunos, professores e coordenadores**.  
O sistema permite cadastro de professores, login de usuários e envio de avaliações (feedbacks) com cálculo de médias gerais e por período.

---

## 🚀 Tecnologias utilizadas

- **Java 17+**
- **Spring Boot 3+**
- **Spring Web**
- **Spring Data JPA**
- **Spring Security**
- **PostgreSQL**
- **Lombok**
- **BCrypt** (para hash de senhas)

---

## 🧩 Estrutura do Banco de Dados

### Tabela `user`
```sql
CREATE TABLE "user" (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(255) CHECK (type IN ('STUDENT', 'TEACHER', 'COORDINATOR')) NOT NULL,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);
```

### Tabela `Class`
```sql
CREATE TABLE Class (
    id SERIAL PRIMARY KEY,
    teacher_id INT NOT NULL,
    student_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(255),
    CONSTRAINT fk_class_student
        FOREIGN KEY (student_id) REFERENCES "user"(id) ON DELETE CASCADE,
    CONSTRAINT fk_class_teacher
        FOREIGN KEY (teacher_id) REFERENCES "user"(id) ON DELETE CASCADE
);
```

### Tabela `Feedback`
```sql
CREATE TABLE Feedback (
    id SERIAL PRIMARY KEY,
    score INT NOT NULL,
    body TEXT NOT NULL,
    student_id INT NOT NULL,
    teacher_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_feedback_student FOREIGN KEY (student_id) REFERENCES "user"(id) ON DELETE CASCADE,
    CONSTRAINT fk_feedback_teacher FOREIGN KEY (teacher_id) REFERENCES "user"(id) ON DELETE CASCADE
);
```

### Tabela `Student_Class`
```sql
CREATE TABLE Student_Class (
    id SERIAL PRIMARY KEY,
    class_id INT NOT NULL,
    student_id INT NOT NULL,
    CONSTRAINT fk_student_class_class FOREIGN KEY (class_id) REFERENCES Class(id) ON DELETE CASCADE,
    CONSTRAINT fk_student_class_student FOREIGN KEY (student_id) REFERENCES "user"(id) ON DELETE CASCADE
);
```

---

## ⚙️ Configuração do Projeto

### 1. Clonar o repositório
```bash
git clone https://github.com/seu-usuario/feedmaker-api.git
cd feedmaker-api
```

### 2. Configurar o banco de dados
No arquivo `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/feedmaker
spring.datasource.username=postgres
spring.datasource.password=suasenha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Executar o projeto
Via Maven:
```bash
mvn spring-boot:run
```

A aplicação iniciará em:  
👉 http://localhost:8080

---

## 🔐 Autenticação

Atualmente a API possui **criptografia de senha com BCrypt**, mas as rotas estão **abertas para testes**.  
Em uma futura versão, será implementado **JWT** para autenticação e autorização por tipo de usuário (`STUDENT`, `TEACHER`, `COORDINATOR`).

---

## 🧠 Rotas da API

### 👤 Autenticação

#### `POST /auth/login`
Faz login de um usuário existente.

**Body:**
```json
{
  "username": "maria",
  "password": "123456"
}
```

**Retorno (exemplo):**
```json
{
  "message": "Login realizado com sucesso",
  "user": {
    "id": 1,
    "name": "Maria",
    "type": "STUDENT"
  }
}
```

---

### 🧑‍🏫 Professores

#### `POST /teachers`
Cria um novo professor.

**Body:**
```json
{
  "name": "Carlos Silva",
  "username": "carlos",
  "password": "123456",
  "type": "TEACHER"
}
```

#### `GET /teachers`
Retorna todos os professores.

#### `GET /teachers/{id}`
Retorna um professor pelo ID.

#### `PUT /teachers/{id}`
Atualiza um professor.

**Body:**
```json
{
  "name": "Carlos Souza",
  "username": "carlos"
}
```

#### `DELETE /teachers/{id}`
Remove um professor pelo ID.

---

### 💬 Feedbacks

#### `POST /feedback`
Cria um novo feedback para um professor.

**Body:**
```json
{
  "score": 5,
  "body": "Excelente aula!",
  "studentId": 2,
  "teacherId": 1
}
```

#### `GET /feedback/teacher/{teacherId}`
Lista todos os feedbacks de um professor.

#### `GET /feedback/teacher/{teacherId}/average`
Retorna a média geral das notas de um professor.

**Exemplo de retorno:**
```json
{
  "teacherId": 1,
  "averageScore": 4.8
}
```

#### `GET /feedback/teacher/{teacherId}/average?start=2025-01-01&end=2025-06-30`
Retorna a média de avaliações dentro de um período.

**Exemplo de retorno:**
```json
{
  "teacherId": 1,
  "averageScore": 4.5,
  "period": {
    "start": "2025-01-01",
    "end": "2025-06-30"
  }
}
```

---

## 🧰 Futuras melhorias

- [ ] Autenticação JWT
- [ ] Autorização por tipo de usuário
- [ ] Paginação de resultados
- [ ] Upload de imagem do professor
- [ ] Dashboard para coordenadores

---

## 👨‍💻 Autor

**Leonardo Morais**  
Desenvolvido para o projeto **FeedMaker** — Sistema de feedback entre alunos e professores.
