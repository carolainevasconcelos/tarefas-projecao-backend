package com.fab.tarefasbackend.controle;

import com.fab.tarefasbackend.entidade.Curso;
import com.fab.tarefasbackend.entidade.CursoRepositorio;
import com.fab.tarefasbackend.entidade.RequestCurso;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/curso")
@Tag(name = "Curso", description = "Endpoints para gerenciamento de cursos")
public class CursoControle {

    @Autowired
    private CursoRepositorio repositorio;

    @GetMapping
    @Operation(summary = "Listar todos os cursos")
    public ResponseEntity<Iterable<Curso>> getCursos() {
        var allCursos = repositorio.findAll();
        return ResponseEntity.ok(allCursos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar curso por ID")
    public ResponseEntity<Curso> getCursoById(@PathVariable int id) {
        return repositorio.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Cadastrar um novo curso")
    public ResponseEntity cadastrarCurso(@RequestBody @Valid RequestCurso data) {
        Curso newCurso = new Curso();
        newCurso.setNome(data.nome());
        newCurso.setDescricao(data.descricao());
        repositorio.save(newCurso);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Editar dados de um curso")
    public ResponseEntity editarCurso(@RequestBody @Valid RequestCurso data) {
        if (data.id() == null || !repositorio.existsById(data.id())) {
            return ResponseEntity.notFound().build();
        }
        Curso curso = repositorio.getReferenceById(data.id());
        curso.setNome(data.nome());
        curso.setDescricao(data.descricao());
        repositorio.save(curso);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um curso por ID")
    public ResponseEntity excluirCurso(@PathVariable int id) {
        if (!repositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}