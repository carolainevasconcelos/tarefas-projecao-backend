// src/main/java/com/fab/tarefasbackend/controle/TarefaControle.java
package com.fab.tarefasbackend.controle;

import com.fab.tarefasbackend.dto.ResponseTarefa; // Importe o novo DTO
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
    public ResponseEntity<Iterable<ResponseTarefa>> getTarefas() { // Retorna Iterable de ResponseTarefa
        var allTarefas = repositorio.findAll();
        var responseTarefas = allTareadas.stream()
                .map(ResponseTarefa::new) // Converte cada Tarefa para ResponseTarefa
                .toList();
        return ResponseEntity.ok(responseTarefas);
    }

    @GetMapping("/{id}") // NOVO ENDPOINT para buscar por ID
    @Operation(summary = "Buscar tarefa por ID")
    public ResponseEntity<ResponseTarefa> getTarefaById(@PathVariable int id) {
        return repositorio.findById(id)
                .map(tarefa -> ResponseEntity.ok(new ResponseTarefa(tarefa))) // Converte para DTO
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Cadastrar uma nova tarefa")
    public ResponseEntity cadastrarTarefa(@RequestBody @Valid RequestTarefa data) {
        // Verifica se usuarioId e disciplinaId foram fornecidos no RequestTarefa
        if (data.usuarioId() == null || data.disciplinaId() == null) {
            return ResponseEntity.badRequest().body("Usuario ID and Disciplina ID are required.");
        }

        Usuario usuario = usuarioRepositorio.findById(data.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado com ID: " + data.usuarioId()));
        Disciplina disciplina = disciplinaRepositorio.findById(data.disciplinaId())
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada com ID: " + data.disciplinaId()));

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
        if (data.id() == null || !repositorio.existsById(data.id())) {
            return ResponseEntity.notFound().build();
        }
        if (data.usuarioId() == null || data.disciplinaId() == null) {
            return ResponseEntity.badRequest().body("Usuario ID and Disciplina ID are required.");
        }

        Tarefa tarefa = repositorio.getReferenceById(data.id()); // Pega a referência para atualizar
        Usuario usuario = usuarioRepositorio.findById(data.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado com ID: " + data.usuarioId()));
        Disciplina disciplina = disciplinaRepositorio.findById(data.disciplinaId())
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada com ID: " + data.disciplinaId()));


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