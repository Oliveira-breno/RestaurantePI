
package com.breno.Restaurante.repository;

import com.breno.Restaurante.model.Mesa;
import com.breno.Restaurante.model.MesaProduto;
import com.breno.Restaurante.model.Produto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MesaProdutoRepository extends JpaRepository<MesaProduto, Long> {
        Optional<MesaProduto> findByMesaAndProduto(Mesa mesa, Produto produto);
           List<MesaProduto> findByMesaId(Long mesaId);
}
