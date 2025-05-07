package com.fab.tarefasbackend.entidade;

import jakarta.persistence.*;
import lombok.*;

@Table(name="usuario")
@Entity(name="usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String tipoUsuario;

    public Usuario(RequestUsuario requestUsuario){
        this.nome = requestUsuario.nome();
        this.cpf = requestUsuario.cpf();
        this.telefone = requestUsuario.telefone();
        this.email = requestUsuario.email();
        this.tipoUsuario = requestUsuario.tipoUsuario();
    }
}
