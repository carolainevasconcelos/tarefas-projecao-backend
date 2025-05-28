package com.fab.tarefasbackend.entidade;

import jakarta.validation.constraints.NotNull;

public record RequestCurso(
        Integer id,
        @NotNull String nome,
        String descricao
) {
}