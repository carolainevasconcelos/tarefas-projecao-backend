package com.fab.tarefasbackend.dto;

public record ResponseDisciplina(
        Integer id,
        String nome,
        String professorNome,
        String horario,
        Integer cursoId
) {
    public ResponseDisciplina(com.fab.tarefasbackend.entidade.Disciplina disciplina) {
        this(disciplina.getId(),
                disciplina.getNome(),
                disciplina.getProfessorNome(),
                disciplina.getHorario(),
                disciplina.getCurso().getId());
    }
}