package com.breno.Restaurante.Service;

import com.breno.Restaurante.model.MesaProduto;
import com.breno.Restaurante.repository.MesaProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Pichau
 */
@Service
public class MesaProdutoService {

    @Autowired
    private MesaProdutoRepository mesaProdutoRepository;

    public void adicionarMesaProduto(MesaProduto mesaProduto) {
        mesaProdutoRepository.save(mesaProduto);  // Salva o produto na mesa
    }
}

