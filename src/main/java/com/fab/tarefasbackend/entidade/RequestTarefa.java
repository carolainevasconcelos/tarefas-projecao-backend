package com.fab.tarefasbackend.entidade;

import java.util.Date;

public record RequestTarefa(int id, String titulo, String descricao, Date prazo, StatusTarefa status, int usuarioId, int disciplinaId) {
}