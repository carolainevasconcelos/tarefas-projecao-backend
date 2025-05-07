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
@Tag(name = "Curso", description = "Endpoints para gerenciamento de curso")
public class CursoControle {
    @Autowired
    private CursoRepositorio repositorio;

    @GetMapping
    @Operation(summary = "Listar todos os cursos")
    public ResponseEntity getCursoById(){
        var allCurso = repositorio.findAll();
        return ResponseEntity.ok(allCurso);
    }

    @PostMapping
    @Operation(summary = "Cadastrar um novo curso")
    public ResponseEntity cadastrarCurso(@RequestBody @Valid RequestCurso data){
        Curso curso = new Curso();
        curso.setNome(data.nome());
        curso.setDescricao(data.descricao());
        repositorio.save(curso);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Editar dados de um curso")
    public ResponseEntity editarCurso(@RequestBody @Valid RequestCurso data){
        Curso newCurso = repositorio.getReferenceById(data.id());
        newCurso.setNome(data.nome());
        newCurso.setDescricao(data.descricao());
        repositorio.save(newCurso);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um curso por ID")
    public  ResponseEntity excluirCurso(@PathVariable int id){
        if (!repositorio.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        repositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
