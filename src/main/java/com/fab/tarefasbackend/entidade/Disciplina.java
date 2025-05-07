package com.fab.tarefasbackend.entidade;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "disciplina")
@Entity(name = "disciplina")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String professorNome;
    private String horario;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    public Disciplina(RequestDisciplina requestDisciplina, Curso curso) {
        this.nome = requestDisciplina.nome();
        this.professorNome = requestDisciplina.professorNome();
        this.horario = requestDisciplina.horario();
        this.curso = curso;
    }
}