package com.breno.Restaurante.controller;

import com.breno.Restaurante.Service.EntregaService;
import com.breno.Restaurante.Service.ProdutoService;
import com.breno.Restaurante.model.Entrega;
import com.breno.Restaurante.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/entregas")
public class EntregaController {

    @Autowired
    private EntregaService entregaService;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/listar")
    public String listarEntregas(Model model) {
        List<Entrega> entregas = entregaService.listarTodas();
        model.addAttribute("entregas", entregas);
        return "entregas";
    }

    @GetMapping("/adicionar")
    public String adicionarEntregaForm(Model model) {
        model.addAttribute("produtos", produtoService.listarProdutos());
        model.addAttribute("entrega", new Entrega());
        return "adicionarEntrega";
    }

   @PostMapping("/adicionar")
public String adicionarEntrega(
        @RequestParam String nomeCliente,
        @RequestParam String endereco,
        @RequestParam List<Long> produtoIds
) {
    // Buscar os produtos com base nos IDs fornecidos
    List<Produto> produtos = produtoService.buscarProdutosPorIds(produtoIds);

    // Garantir que o produto não seja adicionado mais de uma vez
    List<Produto> produtosUnicos = produtos.stream().distinct().collect(Collectors.toList());

    // Criar a entrega e associar os produtos
    Entrega entrega = new Entrega();
    entrega.setNomeCliente(nomeCliente);
    entrega.setEndereco(endereco);
    entrega.setProdutos(produtosUnicos);

    // Adicionar a entrega ao banco de dados
    entregaService.adicionarEntrega(entrega);

    return "redirect:/entregas/listar";
}


}
