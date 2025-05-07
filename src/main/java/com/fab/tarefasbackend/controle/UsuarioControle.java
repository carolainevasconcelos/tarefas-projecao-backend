package com.fab.tarefasbackend.controle;

import com.fab.tarefasbackend.entidade.RequestUsuario;
import com.fab.tarefasbackend.entidade.Usuario;
import com.fab.tarefasbackend.entidade.UsuarioRepositorio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuário", description = "Endpoints para gerenciamento de usuários")
public class UsuarioControle {

    @Autowired
    private UsuarioRepositorio repositorio;

    @GetMapping
    @Operation(summary = "Listar todos os usuários")
    public ResponseEntity getUsuarioById() {
        var allUsuario = repositorio.findAll();
        return ResponseEntity.ok(allUsuario);
    }

    @PostMapping
    @Operation(summary = "Cadastrar um novo usuário")
    public ResponseEntity cadastrarUsuario(@RequestBody @Valid RequestUsuario data) {
        Usuario newUsuario = new Usuario();
        newUsuario.setNome(data.nome());
        newUsuario.setCpf(data.cpf());
        newUsuario.setTelefone(data.telefone());
        newUsuario.setEmail(data.email());
        newUsuario.setTipoUsuario(data.tipoUsuario());
        repositorio.save(newUsuario);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Editar dados de um usuário")
    public ResponseEntity editarUsuario(@RequestBody @Valid RequestUsuario data) {
        Usuario usuario = repositorio.getReferenceById(data.id());
        usuario.setNome(data.nome());
        usuario.setCpf(data.cpf());
        usuario.setTelefone(data.telefone());
        usuario.setEmail(data.email());
        usuario.setTipoUsuario(data.tipoUsuario());
        repositorio.save(usuario);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um usuário por ID")
    public ResponseEntity excluirUsuario(@PathVariable int id) {
        if (!repositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}