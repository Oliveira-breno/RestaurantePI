
package com.breno.Restaurante.repository;

import com.breno.Restaurante.model.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {
}
