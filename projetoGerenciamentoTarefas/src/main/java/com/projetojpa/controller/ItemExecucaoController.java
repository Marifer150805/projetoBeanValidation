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

import com.projetojpa.entities.ItemExecucao;
import com.projetojpa.service.ItemExecucaoService;

@RestController
@RequestMapping("/ItemExecucao")
public class ItemExecucaoController {
    
    private final ItemExecucaoService ItemExecucaoService;
    
    @Autowired
    public ItemExecucaoController(ItemExecucaoService ItemExecucaoService) {
        this.ItemExecucaoService = ItemExecucaoService;
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<ItemExecucao> getProductById(@PathVariable Long codigo) {
    	ItemExecucao ItemExecucao = ItemExecucaoService.getItemExecucaoById(codigo);
        if (ItemExecucao != null) {
            return ResponseEntity.ok(ItemExecucao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<ItemExecucao>> getAllItemExecucao() {
        List<ItemExecucao> ItemExecucao = ItemExecucaoService.getAllItemExecucao();
        return ResponseEntity.ok(ItemExecucao);
    }

    @PostMapping("/")
    public ResponseEntity<ItemExecucao> criarItemExecucao(@RequestBody ItemExecucao ItemExecucao) {
    	ItemExecucao criarItemExecucao = ItemExecucaoService.salvarItemExecucao(ItemExecucao);
        return ResponseEntity.status(HttpStatus.CREATED).body(criarItemExecucao);
    }
   

    @PutMapping("/{codigo}")
    public ResponseEntity<ItemExecucao> updateItemExecucao(@PathVariable Long codigo, @RequestBody ItemExecucao ItemExecucao) {
    	ItemExecucao updatedItemExecucao= ItemExecucaoService.updateItemExecucao(codigo, ItemExecucao);
        if (updatedItemExecucao != null) {
            return ResponseEntity.ok(updatedItemExecucao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<String> deleteItemExecucao(@PathVariable Long codigo) {
        boolean deleted = ItemExecucaoService.deleteItemExecucao(codigo);
        if (deleted) {
        	 return ResponseEntity.ok().body("O produto foi excluído com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
       
    
}