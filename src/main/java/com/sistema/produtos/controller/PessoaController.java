package com.sistema.produtos.controller;

import com.sistema.produtos.model.Pessoa;
import com.sistema.produtos.model.Role;
import com.sistema.produtos.model.Usuario;
import com.sistema.produtos.repository.PessoaRepository;
import com.sistema.produtos.repository.RoleRepository;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;


@Controller
@Transactional
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    PessoaRepository repository;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/form")
    public String form(Pessoa pessoa) {
        if (pessoa.getUsuario() == null) {
            pessoa.setUsuario(new Usuario());//inicializa o usuario para evitar ponteiro nulo
            pessoa.getUsuario().setRoles(new ArrayList<>());
        }
        return "pessoa/form";
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid Pessoa pessoa, BindingResult result, RedirectAttributes attributes) {
        try {
            if (result.hasErrors()) {
                return new ModelAndView("pessoa/form");
            }
            pessoa.getUsuario().setPassword(encriptador(pessoa.getUsuario().getPassword()));//codifica a senha do usuario
            pessoa.getUsuario().getRoles().add(roleRepository.findByName("ROLE_USER"));


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
        model.addAttribute("rolesDisponiveis", roleRepository.findAll());
        model.addAttribute("pessoa", repository.findById(id));
        return new ModelAndView("pessoa/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(Pessoa pessoa) {
        if (pessoa.getUsuario().getPassword() != null && !pessoa.getUsuario().getPassword().isBlank()) {
            pessoa.getUsuario().setPassword(encriptador(pessoa.getUsuario().getPassword()));
        }
        // gambiarra necessária
        List<Role> novasroles = new ArrayList<>();
        for (Role role : pessoa.getUsuario().getRoles()) {
            role.getNome().replace("id=", "");
            role.setNome(role.getNome().replace("id=", ""));
            novasroles.add(roleRepository.findById(Long.valueOf(role.getNome())));
        }
        pessoa.getUsuario().setRoles(novasroles);

        repository.update(pessoa);
        return new ModelAndView("redirect:/pessoas/list");
    }


    private String encriptador(String senha) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(senha);
    }

}
