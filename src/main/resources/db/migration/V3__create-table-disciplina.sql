CREATE TABLE disciplina (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    professor_nome VARCHAR(255),
    horario VARCHAR(50),
    curso_id INT,
    FOREIGN KEY (curso_id) REFERENCES curso(id) ON DELETE CASCADE
);