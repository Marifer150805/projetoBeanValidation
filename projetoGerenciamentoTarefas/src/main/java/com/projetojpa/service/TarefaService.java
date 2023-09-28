package com.projetojpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetojpa.entities.Tarefa;
import com.projetojpa.repository.TarefaRepository;

@Service
public class TarefaService {
    private final TarefaRepository TarefaRepository;
    
    @Autowired
    public  TarefaService(TarefaRepository TarefaRepository) {
        this.TarefaRepository = TarefaRepository;
    }

    public List<Tarefa> getAllTarefa() {
        return  TarefaRepository.findAll();
    }

    public Tarefa getTarefaById(Long codigo) {
        Optional<Tarefa> Tarefa = TarefaRepository.findById(codigo);
        return Tarefa.orElse(null);
    }

    public Tarefa salvarTarefa(Tarefa Tarefa) {
        return TarefaRepository.save(Tarefa);
    }

    public Tarefa updateTarefa(Long codigo, Tarefa updatedTarefa) {
        Optional<Tarefa> existingTarefa =  TarefaRepository.findById(codigo);
        if (existingTarefa.isPresent()) {
            updatedTarefa.setCodigo(codigo);
            return TarefaRepository.save(updatedTarefa);
        }
        return null;
    }

    public boolean deleteTarefa(Long codigo) {
        Optional<Tarefa> existingTarefa = TarefaRepository.findById(codigo);
        if (existingTarefa.isPresent()) {
        	TarefaRepository.deleteById(codigo);
            return true;
        }
        return false;
    }
}
