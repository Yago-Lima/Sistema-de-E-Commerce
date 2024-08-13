package com.sistema.produtos.controller;

import com.sistema.produtos.model.Produto;
import com.sistema.produtos.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Transactional
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    ProdutoRepository repository;

    @GetMapping("/form")
    public String form(Produto produto) {
        return "produto/form";
    }

    @PostMapping("/save")
    public ModelAndView save(Produto produto, RedirectAttributes attributes) {
        try {
            repository.save(produto);
            attributes.addFlashAttribute("mensagem", produto.getNome() + " Inserido com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("mensagem", "Erro ao salvar");
        }
        return new ModelAndView("redirect:/produtos/form");
    }

    @GetMapping("/list")
    public ModelAndView list(ModelMap model) {
        model.addAttribute("produtos", repository.findAll());
        return new ModelAndView("produto/list", model);
    }
    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable Long id) {
        repository.delete(repository.findById(id));
        return new ModelAndView("redirect:/produtos/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("produto", repository.findById(id));
        return new ModelAndView("produto/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("produto") Produto produto) {
        repository.update(produto);
        return new ModelAndView("redirect:/produtos/list");
    }
}
