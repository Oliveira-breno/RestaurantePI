package com.breno.Restaurante.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    @Version  
    private Long version;
    
        @OneToMany(mappedBy = "mesa", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<MesaProduto> mesaProdutos;

    public List<MesaProduto> getMesaProdutos() {
        return mesaProdutos;
    }

    public void setMesaProdutos(List<MesaProduto> mesaProdutos) {
        this.mesaProdutos = mesaProdutos;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    private boolean ocupada;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Produto> produtos = new ArrayList<>();

    private BigDecimal total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

   
}
