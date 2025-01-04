package com.breno.Restaurante.Service;

import com.breno.Restaurante.model.MovimentoCaixa;
import com.breno.Restaurante.repository.MovimentoCaixaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimentoCaixaService {

    @Autowired
    private MovimentoCaixaRepository movimentoCaixaRepository;

    
    public void registrarMovimento(BigDecimal valor, String tipo, String descricao) {
        MovimentoCaixa movimento = new MovimentoCaixa();
        movimento.setDataHora(LocalDateTime.now());
        movimento.setValor(valor);
        movimento.setTipo(tipo);
        movimento.setDescricao(descricao);
        movimentoCaixaRepository.save(movimento);
    }
    
   public List<MovimentoCaixa> listarTodos() {
        return movimentoCaixaRepository.findAll();
    }
}