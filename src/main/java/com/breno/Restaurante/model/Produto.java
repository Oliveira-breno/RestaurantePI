package com.breno.Restaurante.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private BigDecimal valor;
    private String categoria;
    private String descricao;
    
      @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<MesaProduto> mesaProdutos; 

    public List<MesaProduto> getMesaProdutos() {
        return mesaProdutos;
    }

    public void setMesaProdutos(List<MesaProduto> mesaProdutos) {
        this.mesaProdutos = mesaProdutos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

   
}
