CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    telefone VARCHAR(15),
    email VARCHAR(255) NOT NULL UNIQUE,
    tipo_usuario ENUM('ESTUDANTE', 'PROFESSOR') NOT NULL
);