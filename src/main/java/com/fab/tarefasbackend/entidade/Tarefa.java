// src/main/java/com/fab/tarefasbackend/entidade/Tarefa.java
package com.fab.tarefasbackend.entidade;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate; // Importar LocalDate

@Table(name = "tarefa")
@Entity(name = "tarefa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titulo;
    private String descricao;
    private LocalDate prazo; // Tipo alterado

    @Enumerated(EnumType.STRING)
    private StatusTarefa status;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "disciplina_id", nullable = false)
    private Disciplina disciplina;

    public Tarefa(RequestTarefa requestTarefa, Usuario usuario, Disciplina disciplina) {
        this.titulo = requestTarefa.titulo();
        this.descricao = requestTarefa.descricao();
        this.prazo = requestTarefa.prazo();
        this.status = requestTarefa.status();
        this.usuario = usuario;
        this.disciplina = disciplina;
    }
}