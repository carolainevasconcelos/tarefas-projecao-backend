package com.fab.tarefasbackend.entidade;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepositorio extends JpaRepository<Tarefa, Integer> {
}