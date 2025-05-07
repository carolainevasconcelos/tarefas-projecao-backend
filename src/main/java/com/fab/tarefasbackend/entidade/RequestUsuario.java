package com.fab.tarefasbackend.entidade;

public record RequestUsuario(int id, String nome, String cpf, String telefone, String email, String tipoUsuario) {
}
