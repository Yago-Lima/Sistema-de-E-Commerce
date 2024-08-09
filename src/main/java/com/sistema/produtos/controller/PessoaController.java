package com.sistema.produtos.controller;

import com.sistema.produtos.model.Pessoa;
import com.sistema.produtos.repository.PessoaRepository;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@Transactional
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    PessoaRepository repository;

    @GetMapping("/form")
    public String form(Pessoa pessoa) {
        return "pessoa/form";
    }

    @PostMapping("/save")
    public ModelAndView save(Pessoa pessoa, RedirectAttributes attributes) {
        try {
            repository.save(pessoa);
            attributes.addFlashAttribute("mensagem", pessoa.getNome() + " Inserido com sucesso!");
        } catch (PersistenceException e) {
            e.printStackTrace();
            attributes.addFlashAttribute("mensagem", "Erro ao salvar");
        }
        return new ModelAndView("redirect:/pessoas/form");
    }

    @GetMapping("/list")
    public ModelAndView list(ModelMap model) {
        model.addAttribute("pessoas", repository.findAll());
        return new ModelAndView("pessoa/list", model);
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable Long id, ModelMap model) {
        repository.delete(repository.findById(id));
        model.addAttribute("pessoas", repository.findAll());
        return new ModelAndView("pessoa/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("pessoa", repository.findById(id));
        return new ModelAndView("pessoa/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("pessoa") Pessoa pessoa) {
        repository.update(pessoa);
        return new ModelAndView("redirect:/pessoas/list");
    }

}
