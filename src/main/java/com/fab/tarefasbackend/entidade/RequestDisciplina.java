package com.fab.tarefasbackend.entidade;

import jakarta.validation.constraints.NotNull;

public record RequestDisciplina(
        Integer id,
        @NotNull String nome,
        @NotNull String professorNome,
        @NotNull String horario,
        @NotNull Integer cursoId
) {
}