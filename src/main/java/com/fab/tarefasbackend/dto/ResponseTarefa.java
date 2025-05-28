package com.fab.tarefasbackend.dto;

import com.fab.tarefasbackend.entidade.StatusTarefa;
import java.time.LocalDate;

public record ResponseTarefa(
        Integer id,
        String titulo,
        String descricao,
        LocalDate prazo,
        StatusTarefa status,
        Integer usuarioId,
        Integer disciplinaId
) {
    public ResponseTarefa(com.fab.tarefasbackend.entidade.Tarefa tarefa) {
        this(tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getPrazo(),
                tarefa.getStatus(),
                tarefa.getUsuario().getId(),
                tarefa.getDisciplina().getId());
    }
}