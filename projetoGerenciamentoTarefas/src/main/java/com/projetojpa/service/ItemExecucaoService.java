package com.projetojpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetojpa.entities.ItemExecucao;
import com.projetojpa.repository.ItemExecucaoRepository;

@Service
public class ItemExecucaoService {
    private final ItemExecucaoRepository ItemExecucaoRepository;
    
    @Autowired
    public ItemExecucaoService(ItemExecucaoRepository ItemExecucaoRepository) {
        this.ItemExecucaoRepository = ItemExecucaoRepository;
    }

    public List<ItemExecucao> getAllItemExecucao() {
        return ItemExecucaoRepository.findAll();
    }

    public ItemExecucao getItemExecucaoById(Long codigo) {
        Optional<ItemExecucao> ItemExecucao = ItemExecucaoRepository.findById(codigo);
        return ItemExecucao.orElse(null);
    }

    public ItemExecucao salvarItemExecucao(ItemExecucao ItemExecucao) {
        return ItemExecucaoRepository.save(ItemExecucao);
    }

    public ItemExecucao updateItemExecucao(Long codigo,ItemExecucao updatedItemExecucao) {
        Optional<ItemExecucao> existingItemExecucao = ItemExecucaoRepository.findById(codigo);
        if (existingItemExecucao.isPresent()) {
            updatedItemExecucao.setCodigo(codigo);
            return ItemExecucaoRepository.save(updatedItemExecucao);
        }
        return null;
    }

    public boolean deleteItemExecucao(Long codigo) {
        Optional<ItemExecucao> existingItemExecucao = ItemExecucaoRepository.findById(codigo);
        if (existingItemExecucao.isPresent()) {
        	ItemExecucaoRepository.deleteById(codigo);
            return true;
        }
        return false;
    }
}
