package com.sistema.produtos.controller;

import com.sistema.produtos.model.*;
import com.sistema.produtos.model.validator.ItemListNotFoundExeption;
import com.sistema.produtos.repository.PessoaRepository;
import com.sistema.produtos.repository.ProdutoRepository;
import com.sistema.produtos.repository.UsuarioRepository;
import com.sistema.produtos.repository.VendaRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;

@Transactional
@Controller
@Scope("request")
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaRepository vr;
    @Autowired
    private ProdutoRepository pr;
    @Autowired
    private PessoaRepository pessoaR;
    @Autowired
    private Venda venda;

    @PostMapping("/add")
    public ModelAndView addItemCarrinho(ItemVenda itemVenda,
                                        @RequestParam("produtoID") Long idProduto,
                                        RedirectAttributes attributes) {

        boolean existe = false;
        // verifica se existe um produto na lista
        for (ItemVenda item : venda.getItemVendaList()) {
            if (item.getProduto().getId().equals(idProduto)) {
                existe = true;
                item.setQuantidade(item.getQuantidade() + itemVenda.getQuantidade());
                break;
            }
        }
        Produto produto = pr.findById(idProduto);
        itemVenda.setProduto(produto);
        itemVenda.setVenda(venda);
        //se não existe o produto na lista
        if (!existe) {
            if (produto != null) {
                venda.getItemVendaList().add(itemVenda);
            } else {
                attributes.addFlashAttribute("mensagem", "Produto não encontrado.");
                return new ModelAndView("redirect:/produtos/list");
            }
        }
        attributes.addFlashAttribute("mensagem", String.format("%d x %s adicionado(s) ao carrinho", itemVenda.getQuantidade(), produto.getNome()));
        return new ModelAndView("redirect:/produtos/list");
    }

    @GetMapping("/carrinho")
    public ModelAndView carrinhoList(ModelMap model){
        model.addAttribute("venda",venda);
        return new ModelAndView("venda/carrinho",model);
    }
    @GetMapping("/finalizar")
    public ModelAndView finalizarVenda(HttpSession session){
        venda.setCliente(pessoaR.findByUser(getUsuarioSessao().getName()));

        vr.save(venda);
        session.removeAttribute("venda");
        return new ModelAndView("redirect:/vendas/list");
    }
    @GetMapping("/list")
    public ModelAndView list(ModelMap model){
        Pessoa p = pessoaR.findByUser(getUsuarioSessao().getName());

        if (p != null) {
            model.addAttribute("vendas", vr.findByClientName(p.getNome()));
        } else {
            model.addAttribute("mensagem","sem vendas localizadas para o usuario atual");
            model.addAttribute("vendas", Collections.emptyList());
        }

        return new ModelAndView("venda/list");
    }
    @GetMapping("/details/{id}")
    public ModelAndView listItemVenda(@PathVariable Long id,ModelMap model) throws ItemListNotFoundExeption {

        if (id != null) {
            model.addAttribute("itensVenda", vr.findItensVendaById(id));
        } else {
            model.addAttribute("mensagem","sem vendas localizadas para o usuario atual");
            throw new ItemListNotFoundExeption("Erro: Lista Vazia");
        }
        return new ModelAndView("venda/details");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable Long id, ModelMap model) {
        vr.delete(vr.findById(id));
        Pessoa p = pessoaR.findByUser(getUsuarioSessao().getName());

        if (p != null) {
            model.addAttribute("vendas", vr.findByClientName(p.getNome()));
        } else {
            model.addAttribute("mensagem","sem vendas localizadas para o usuario atual");
            model.addAttribute("vendas", Collections.emptyList());
        }
        return new ModelAndView("venda/list");
    }
    @GetMapping("carrinho/remove/{id}")
    public ModelAndView removeCarrinho(@PathVariable Long id, ModelMap model) {
        venda.getItemVendaList().remove(vr.findById(id));
        model.addAttribute("venda",venda);

        return new ModelAndView("venda/list");
    }


    private Authentication getUsuarioSessao(){
        return SecurityContextHolder.getContext().getAuthentication();
    }


}
