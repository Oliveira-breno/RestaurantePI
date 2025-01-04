package com.breno.Restaurante.controller;

import com.breno.Restaurante.Service.ProdutoService;
import com.breno.Restaurante.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

   
    @GetMapping("/listar")
    public String listarProdutos(Model model) {
        model.addAttribute("produtos", produtoService.listarProdutos());
        return "produtos"; // Nome do arquivo HTML para listar produtos
    }

    @GetMapping("/adicionar")
    public String adicionarProdutoForm(Model model) {
        model.addAttribute("produto", new Produto());
        return "adicionarProduto"; // Nome do arquivo HTML para adicionar produto
    }

    @PostMapping("/cadastrar")
    public String adicionarProduto(@ModelAttribute Produto produto) {
        produtoService.salvarProduto(produto);
        return "redirect:/produtos/listar"; // Redireciona para a lista de produtos
    }

   
}
