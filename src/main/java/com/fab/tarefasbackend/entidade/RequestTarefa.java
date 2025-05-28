package com.fab.tarefasbackend.entidade;

import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;

public record RequestTarefa(
        Integer id,
        @NotNull String titulo,
        String descricao,
        LocalDate prazo,
        @NotNull StatusTarefa status,
        @NotNull Integer usuarioId,
        @NotNull Integer disciplinaId
) {
}