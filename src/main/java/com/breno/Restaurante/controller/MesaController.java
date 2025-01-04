package com.breno.Restaurante.controller;

import com.breno.Restaurante.Service.MesaService;
import com.breno.Restaurante.Service.ProdutoService;
import com.breno.Restaurante.model.Mesa;
import com.breno.Restaurante.model.Produto;
import com.breno.Restaurante.repository.MesaRepository;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/mesas")
public class MesaController {

    @Autowired
    private MesaService mesaService;
    @Autowired
    private MesaRepository mesaRepository;
    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/listar")
    public String listarMesas(Model model) {
        model.addAttribute("mesas", mesaService.listarMesas());
        return "mesas";
    }
    @GetMapping("/adicionar")
public String adicionarMesaForm(Model model) {
    return "adicionarMesa";  // Retorna a página de formulário para adicionar a mesa
}
@PostMapping("/adicionar")
public String adicionarMesa(@RequestParam Long id, @RequestParam boolean ocupada) {
    Optional<Mesa> mesaExistente = mesaRepository.findById(id);
    if (mesaExistente.isPresent()) {
        Mesa mesa = mesaExistente.get();
        mesa.setOcupada(ocupada);
        mesa.setTotal(BigDecimal.ZERO);  // Inicializa o total com zero
        mesaService.salvarMesa(mesa);    // Atualiza a mesa existente
    } else {
        // Caso a mesa não exista, cria uma nova
        Mesa novaMesa = new Mesa();
        novaMesa.setOcupada(ocupada);
        novaMesa.setTotal(BigDecimal.ZERO);
        mesaService.salvarMesa(novaMesa); // Cria a nova mesa
    }
    return "redirect:/mesas/listar";  // Redireciona para a lista de mesas
}



   @GetMapping("/adicionarProduto/{mesaId}")
public String adicionarProdutoNaMesaForm(@PathVariable Long mesaId, Model model) {
    // Adiciona a lista de produtos para o formulário
    model.addAttribute("produtos", produtoService.listarProdutos());
    model.addAttribute("mesaId", mesaId); // Passa o ID da mesa para o formulário
    return "adicionarProdutoMesa"; // Nome da página de formulário
}



     @PostMapping("/adicionarProduto/{mesaId}")
    public String adicionarProdutoNaMesa(
            @PathVariable Long mesaId, 
            @RequestParam Long produtoId, 
            @RequestParam int quantidade) {
        
        Produto produto = produtoService.buscarProdutoPorId(produtoId);
        mesaService.adicionarProdutoNaMesa(mesaId, produto, quantidade);
        
        return "redirect:/mesas/listar"; // Redireciona para a lista de mesas após a operação
    }

    @PostMapping("/finalizar/{mesaId}")
    public String finalizarMesa(@PathVariable Long mesaId) {
    mesaService.finalizarMesa(mesaId);
    return "redirect:/mesas/listar";
}
}
