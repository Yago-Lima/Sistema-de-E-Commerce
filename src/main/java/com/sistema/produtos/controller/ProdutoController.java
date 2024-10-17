package com.sistema.produtos.controller;

import com.sistema.produtos.model.ItemVenda;
import com.sistema.produtos.model.Produto;
import com.sistema.produtos.repository.ProdutoRepository;
import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
    public ModelAndView save(@Valid Produto produto, BindingResult result, RedirectAttributes attributes) {
        try {
            if (result.hasErrors()){
                return new ModelAndView("produto/form");
            }
            repository.save(produto);
            attributes.addFlashAttribute("mensagem", produto.getNome() + " Inserido com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("mensagem", "Erro ao salvar");
        }
        return new ModelAndView("redirect:/produtos/form");
    }

    @GetMapping("/list")
    public ModelAndView list(ItemVenda itemVenda, ModelMap model) {
        model.addAttribute("itemVenda", itemVenda);
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
    public ModelAndView update(@ModelAttribute("produto") @Valid Produto produto, BindingResult result) {
        if (result.hasErrors()){
            return new ModelAndView("produto/form");
        }
        repository.update(produto);
        return new ModelAndView("redirect:/produtos/list");
    }
    @GetMapping("/search")
    public ModelAndView search(@RequestParam("nome") String nome,
                               @Nullable @RequestParam("preco-min") Double precoMin,
                               @Nullable @RequestParam("preco-max") Double precoMax,
                               ModelMap model) {
        List<Produto> produtos;
        if (!nome.isBlank()) {
            produtos = repository.findByName(nome);
            model.addAttribute("produtos", produtos);
        } else {
            if (precoMin != null && precoMax != null) {
                produtos = repository.findByPrice(precoMin, precoMax);
                model.addAttribute("produtos", produtos);
            } else {
                if (precoMin == null) {
                    precoMin = 1.0;
                }
                if (precoMax == null) {
                    precoMax = 9999999999999.0;
                }
                produtos = repository   .findByPrice(precoMin, precoMax);
                model.addAttribute("produtos", produtos);
            }
        }
        return new ModelAndView("produto/list", model);
    }
    @GetMapping("/searchdate")
    public ModelAndView searchByDate(){
        return  new ModelAndView();
    }
}
