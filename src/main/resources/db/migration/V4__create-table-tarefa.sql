    CREATE TABLE tarefa (
        id INT AUTO_INCREMENT PRIMARY KEY,
        titulo VARCHAR(255) NOT NULL,
        descricao TEXT,
        prazo DATE,
        status ENUM('PENDENTE', 'EM ANDAMENTO', 'CONCLUÍDO'),
        usuario_id INT,
        disciplina_id INT,
        FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE,
        FOREIGN KEY (disciplina_id) REFERENCES disciplina(id) ON DELETE CASCADE
    );