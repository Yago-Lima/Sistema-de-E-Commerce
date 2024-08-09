package com.sistema.produtos.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "TB_ITEM_VENDA")
public class ItemVenda extends AbstractEntity<Long>{


    private int quantidade;

    @ManyToOne()
    private Produto produto;

    @ManyToOne
    private Venda venda;

    public ItemVenda(int quantidade, Produto produto, Venda venda) {
        this.quantidade = quantidade;
        this.produto = produto;
        this.venda = venda;
    }

    public ItemVenda() {

    }

    public BigDecimal total(){
        return BigDecimal.valueOf(quantidade).multiply(produto.getValor());
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
