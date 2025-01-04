package com.breno.Restaurante.controller;

import com.breno.Restaurante.Service.MovimentoCaixaService;
import com.breno.Restaurante.model.MovimentoCaixa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movimentoCaixa")
public class MovimentoCaixaController {

    @Autowired
    private MovimentoCaixaService movimentoCaixaService;

    @GetMapping("/listar")
    public String listarMovimentos(Model model) {
    List<MovimentoCaixa> movimentos = movimentoCaixaService.listarTodos();
    model.addAttribute("movimentos", movimentos);
    return "movimentoCaixaList";
}

}
