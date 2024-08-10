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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Controller
@SessionAttributes("carrinho")
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaRepository vr;
    @Autowired
    private ProdutoRepository pr;
    @Autowired
    private PessoaRepository pessoaR;

    @ModelAttribute("carrinho")
    public Venda getVenda() {
        Venda venda = new Venda();
        venda.setItemVendaList(new ArrayList<>());
        return venda;
    }

    @PostMapping("/add")
    public ModelAndView addItemCarrinho(@RequestParam Long id,
                                        @RequestParam int quantidade,
                                        @RequestParam String nome,
                                        @ModelAttribute("carrinho") Venda carrinho,
                                        RedirectAttributes attributes) {

        boolean existe = false;
        for (ItemVenda item : carrinho.getItemVendaList()) {
            if (item.getProduto().getId().equals(id)) {
                existe = true;
                item.setQuantidade(item.getQuantidade() + quantidade);
                break;
            }
        }

        if (!existe) {
            Produto produto = pr.findById(id);
            if (produto != null) {
                carrinho.getItemVendaList().add(new ItemVenda(quantidade, produto, carrinho));
            } else {
                attributes.addFlashAttribute("mensagem", "Produto não encontrado.");
                return new ModelAndView("redirect:/produtos/list");
            }
        }

        attributes.addFlashAttribute("mensagem", String.format("%d x %s adicionado(s) ao carrinho", quantidade, nome));
        return new ModelAndView("redirect:/produtos/list");
    }

    @GetMapping("/carrinho")
    public ModelAndView carrinhoList(@ModelAttribute("carrinho") Venda carrinho){
        return new ModelAndView("venda/carrinho");
    }
    @GetMapping("/finalizar")
    public ModelAndView finalizarVenda(@ModelAttribute("carrinho") Venda carrinho){
        carrinho.setCliente(new Pessoa("maria","rua 8","64999","adsd","21312321"));
        carrinho.getCliente().getVendas().add(carrinho);

          vr.save(carrinho);
        carrinho = getVenda();
        return new ModelAndView("redirect:vendas/list");
    }
    @GetMapping("/list")
    public ModelAndView list(ModelMap model){
        model.addAttribute("vendas", vr.findAll());
        return new ModelAndView("venda/list");
    }


}
