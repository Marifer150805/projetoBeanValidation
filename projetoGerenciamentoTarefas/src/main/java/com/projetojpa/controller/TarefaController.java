package com.projetojpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetojpa.entities.Tarefa;
import com.projetojpa.service.TarefaService;

@RestController
@RequestMapping("/Tarefa")
public class TarefaController {
    
    private final TarefaService TarefaService;
    
    @Autowired
    public TarefaController(TarefaService TarefaService) {
        this.TarefaService = TarefaService;
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Tarefa> getProductById(@PathVariable Long codigo) {
    	Tarefa Tarefa = TarefaService.getTarefaById(codigo);
        if (Tarefa != null) {
            return ResponseEntity.ok(Tarefa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Tarefa>> getAllTarefa() {
        List<Tarefa> Tarefa = TarefaService.getAllTarefa();
        return ResponseEntity.ok(Tarefa);
    }

    @PostMapping("/")
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa Tarefa) {
    	Tarefa criarTarefa = TarefaService.salvarTarefa(Tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(criarTarefa);
    }
   

    @PutMapping("/{codigo}")
    public ResponseEntity<Tarefa> updateTarefa(@PathVariable Long codigo, @RequestBody Tarefa Tarefa) {
    	Tarefa updatedTarefa= TarefaService.updateTarefa(codigo, Tarefa);
        if (updatedTarefa != null) {
            return ResponseEntity.ok(updatedTarefa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<String> deleteTarefa(@PathVariable Long codigo) {
        boolean deleted = TarefaService.deleteTarefa(codigo);
        if (deleted) {
        	 return ResponseEntity.ok().body("O produto foi excluído com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
       
    
}