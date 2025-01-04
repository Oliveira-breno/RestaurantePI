package com.breno.Restaurante.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "mesa_produtos")
public class MesaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mesa_id")
    private Mesa mesa;

    @ManyToOne
    @JoinColumn(name = "produtos_id", nullable = false)
    private Produto produto;

    private int quantidade;

    public MesaProduto() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // Método para calcular o total
    public BigDecimal getTotal() {
        return produto.getValor().multiply(BigDecimal.valueOf(quantidade));
    }
}
