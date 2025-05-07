package com.fab.tarefasbackend.entidade;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "curso")
@Entity(name = "curso")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String descricao;

    public Curso(RequestCurso requestCurso){
        this.nome = requestCurso.nome();
        this.descricao = requestCurso.descricao();
    }
}
