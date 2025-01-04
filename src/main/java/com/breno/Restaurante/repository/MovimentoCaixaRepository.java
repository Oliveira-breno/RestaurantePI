/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.breno.Restaurante.repository;

import com.breno.Restaurante.model.MovimentoCaixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentoCaixaRepository extends JpaRepository<MovimentoCaixa, Long> {
}
