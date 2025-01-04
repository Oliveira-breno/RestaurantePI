package com.breno.Restaurante.Service;

import com.breno.Restaurante.model.Mesa;
import com.breno.Restaurante.model.MesaProduto;
import com.breno.Restaurante.model.MovimentoCaixa;
import com.breno.Restaurante.model.Produto;
import com.breno.Restaurante.repository.MesaProdutoRepository;
import com.breno.Restaurante.repository.MesaRepository;
import com.breno.Restaurante.repository.MovimentoCaixaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MesaService {

    @Autowired
    private MovimentoCaixaRepository movimentoCaixaRepository;
    
    
    @Autowired
    private MesaRepository mesaRepository;
     @Autowired
    private MesaProdutoRepository mesaProdutoRepository;
    @Autowired
    private ProdutoService produtoService;
     @Autowired
    private MovimentoCaixaService movimentoCaixaService; 

    public void adicionarProdutoNaMesa(Long mesaId, Produto produto, int quantidade) {
    Mesa mesa = mesaRepository.findById(mesaId).orElseThrow(() -> new IllegalArgumentException("Mesa não encontrada"));

    // Verifica se o produto já foi adicionado à mesa
    Optional<MesaProduto> mesaProdutoExistente = mesaProdutoRepository.findByMesaAndProduto(mesa, produto);

    if (mesaProdutoExistente.isPresent()) {
        // Se o produto já existe, atualize a quantidade
        MesaProduto mesaProduto = mesaProdutoExistente.get();
        mesaProduto.setQuantidade(mesaProduto.getQuantidade() + quantidade);
        mesaProdutoRepository.save(mesaProduto);
    } else {
        // Caso contrário, adicione o produto à mesa
        MesaProduto mesaProduto = new MesaProduto();
        mesaProduto.setMesa(mesa);
        mesaProduto.setProduto(produto);
        mesaProduto.setQuantidade(quantidade);
        mesaProdutoRepository.save(mesaProduto);
    }

    // Atualiza o total da mesa
    atualizarTotalMesa(mesa);
}

private void atualizarTotalMesa(Mesa mesa) {
    BigDecimal total = BigDecimal.ZERO;
    for (MesaProduto mesaProduto : mesa.getMesaProdutos()) {
        total = total.add(mesaProduto.getProduto().getValor().multiply(BigDecimal.valueOf(mesaProduto.getQuantidade())));
    }
    mesa.setTotal(total);
    mesaRepository.save(mesa);
}

    public Mesa buscarMesaPorId(Long mesaId) {
        return mesaRepository.findById(mesaId).orElseThrow(() -> new RuntimeException("Mesa não encontrada"));
    }
    public List<Mesa> listarMesas() {
        return mesaRepository.findAll();
    }

  public void finalizarMesa(Long mesaId) {
    Mesa mesa = mesaRepository.findById(mesaId)
        .orElseThrow(() -> new IllegalArgumentException("Mesa não encontrada"));
    
       if (!mesa.isOcupada()) {
        throw new IllegalStateException("Mesa já está finalizada ou não está ocupada");
    }

    // Registrar o valor total da mesa diretamente no movimento de caixa
    MovimentoCaixa movimento = new MovimentoCaixa();
    movimento.setDescricao("Finalização da Mesa " + mesa.getId());
    movimento.setValor(mesa.getTotal()); // Usando o valor total da mesa
    movimento.setDataHora(LocalDateTime.now());
    movimentoCaixaRepository.save(movimento);
    
     mesa.setOcupada(false);
     mesa.setTotal(BigDecimal.ZERO);
    mesaRepository.save(mesa);
    
}


 // Método para salvar a mesa
    public void salvarMesa(Mesa mesa) {
        mesaRepository.save(mesa);
    }
    
    
}
