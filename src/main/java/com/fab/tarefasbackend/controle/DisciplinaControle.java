package com.fab.tarefasbackend.controle;

import com.fab.tarefasbackend.dto.ResponseDisciplina;
import com.fab.tarefasbackend.entidade.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/disciplina")
@Tag(name = "Disciplina", description = "Endpoints para gerenciamento de disciplinas")
public class DisciplinaControle {

    @Autowired
    private DisciplinaRepositorio repositorio;

    @Autowired
    private CursoRepositorio cursoRepositorio;

    @GetMapping
    @Operation(summary = "Listar todas as disciplinas")
    public ResponseEntity<Iterable<ResponseDisciplina>> getDisciplinas() {
        var allDisciplinas = repositorio.findAll();
        var responseDisciplinas = allDisciplinas.stream()
                .map(ResponseDisciplina::new)
                .toList();
        return ResponseEntity.ok(responseDisciplinas);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar disciplina por ID")
    public ResponseEntity<ResponseDisciplina> getDisciplinaById(@PathVariable int id) {
        return repositorio.findById(id)
                .map(disciplina -> ResponseEntity.ok(new ResponseDisciplina(disciplina)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Cadastrar uma nova disciplina")
    public ResponseEntity cadastrarDisciplina(@RequestBody @Valid RequestDisciplina data) {
        if (data.cursoId() == null) {
            return ResponseEntity.badRequest().body("Curso ID is required.");
        }

        Curso curso = cursoRepositorio.findById(data.cursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado com ID: " + data.cursoId()));

        Disciplina disciplina = new Disciplina();
        disciplina.setNome(data.nome());
        disciplina.setProfessorNome(data.professorNome());
        disciplina.setHorario(data.horario());
        disciplina.setCurso(curso);

        repositorio.save(disciplina);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Editar dados de uma disciplina")
    public ResponseEntity editarDisciplina(@RequestBody @Valid RequestDisciplina data) {
        if (data.id() == null || !repositorio.existsById(data.id())) {
            return ResponseEntity.notFound().build();
        }
        if (data.cursoId() == null) {
            return ResponseEntity.badRequest().body("Curso ID is required.");
        }

        Disciplina disciplina = repositorio.getReferenceById(data.id());
        Curso curso = cursoRepositorio.findById(data.cursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado com ID: " + data.cursoId()));

        disciplina.setNome(data.nome());
        disciplina.setProfessorNome(data.professorNome());
        disciplina.setHorario(data.horario());
        disciplina.setCurso(curso);

        repositorio.save(disciplina);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir uma disciplina por ID")
    public ResponseEntity excluirDisciplina(@PathVariable int id) {
        if (!repositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}