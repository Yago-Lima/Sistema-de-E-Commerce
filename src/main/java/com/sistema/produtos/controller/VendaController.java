package com.sistema.produtos.controller;

import com.sistema.produtos.model.ItemVenda;
import com.sistema.produtos.model.Pessoa;
import com.sistema.produtos.model.Produto;
import com.sistema.produtos.model.Venda;
import com.sistema.produtos.repository.PessoaRepository;
import com.sistema.produtos.repository.ProdutoRepository;
import com.sistema.produtos.repository.VendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

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
    public ModelAndView finalizarVenda(){
        Pessoa p = pessoaR.findById(1L);
        venda.setCliente(p);
            venda.getCliente().getVendas().add(venda);

        vr.save(venda);
        venda = new Venda();
        return new ModelAndView("redirect:/vendas/list");
    }
    @GetMapping("/list")
    public ModelAndView list(ModelMap model){
        model.addAttribute("vendas", vr.findAll());
        return new ModelAndView("venda/list");
    }


}
