package com.breno.Restaurante.Service;

import com.breno.Restaurante.model.Entrega;
import com.breno.Restaurante.model.Produto;
import com.breno.Restaurante.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private MovimentoCaixaService movimentoCaixaService;

    // Método que lista todas as entregas
    public List<Entrega> listarTodas() {
        return entregaRepository.findAll();
    }

    // Método que recebe uma entrega e os produtos para calcular o total e salvar
  public Entrega adicionarEntrega(Entrega entrega) {
    // Calcular o total da entrega
    BigDecimal total = BigDecimal.ZERO;
    for (Produto produto : entrega.getProdutos()) {
        total = total.add(produto.getValor());
    }

    entrega.setTotal(total);

    // Salvar a entrega no banco de dados
    Entrega entregaSalva = entregaRepository.save(entrega);

    // Registrar no movimento de caixa
    movimentoCaixaService.registrarMovimento(total, "entrada", "Venda de entrega para " + entrega.getNomeCliente());

    return entregaSalva;
}

}
