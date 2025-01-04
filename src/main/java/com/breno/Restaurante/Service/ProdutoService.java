
package com.breno.Restaurante.Service;
import com.breno.Restaurante.model.Produto;
import com.breno.Restaurante.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

   public List<Produto> buscarProdutosPorIds(List<Long> ids) {
    return produtoRepository.findAllById(ids);
   
}
public Produto buscarProdutoPorId(Long id) {
    return produtoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
}
}
