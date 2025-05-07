package com.fab.tarefasbackend.controle;

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
    public ResponseEntity getDisciplinas() {
        var allDisciplinas = repositorio.findAll();
        return ResponseEntity.ok(allDisciplinas);
    }

    @PostMapping
    @Operation(summary = "Cadastrar uma nova disciplina")
    public ResponseEntity cadastrarDisciplina(@RequestBody @Valid RequestDisciplina data) {
        Curso curso = cursoRepositorio.getReferenceById(data.cursoId());

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
        Disciplina disciplina = repositorio.getReferenceById(data.id());
        Curso curso = cursoRepositorio.getReferenceById(data.cursoId());

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
