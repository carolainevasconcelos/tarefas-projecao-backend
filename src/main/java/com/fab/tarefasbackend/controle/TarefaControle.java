package com.fab.tarefasbackend.controle;

import com.fab.tarefasbackend.entidade.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefa")
@Tag(name = "Tarefa", description = "Endpoints para gerenciamento de tarefas")
public class TarefaControle {

    @Autowired
    private TarefaRepositorio repositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private DisciplinaRepositorio disciplinaRepositorio;

    @GetMapping
    @Operation(summary = "Listar todas as tarefas")
    public ResponseEntity getTarefas() {
        var allTarefas = repositorio.findAll();
        return ResponseEntity.ok(allTarefas);
    }

    @PostMapping
    @Operation(summary = "Cadastrar uma nova tarefa")
    public ResponseEntity cadastrarTarefa(@RequestBody @Valid RequestTarefa data) {
        Usuario usuario = usuarioRepositorio.getReferenceById(data.usuarioId());
        Disciplina disciplina = disciplinaRepositorio.getReferenceById(data.disciplinaId());

        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(data.titulo());
        tarefa.setDescricao(data.descricao());
        tarefa.setPrazo(data.prazo());
        tarefa.setStatus(data.status());
        tarefa.setUsuario(usuario);
        tarefa.setDisciplina(disciplina);

        repositorio.save(tarefa);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Editar dados de uma tarefa")
    public ResponseEntity editarTarefa(@RequestBody @Valid RequestTarefa data) {
        Tarefa tarefa = repositorio.getReferenceById(data.id());
        Usuario usuario = usuarioRepositorio.getReferenceById(data.usuarioId());
        Disciplina disciplina = disciplinaRepositorio.getReferenceById(data.disciplinaId());

        tarefa.setTitulo(data.titulo());
        tarefa.setDescricao(data.descricao());
        tarefa.setPrazo(data.prazo());
        tarefa.setStatus(data.status());
        tarefa.setUsuario(usuario);
        tarefa.setDisciplina(disciplina);

        repositorio.save(tarefa);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir uma tarefa por ID")
    public ResponseEntity excluirTarefa(@PathVariable int id) {
        if (!repositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}