package com.sistema.produtos.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "TB_ITEM_VENDA")
public class ItemVenda extends AbstractEntity<Long>{

    @Min(value = 1, message = "A quantidade deve ser maior que 0")
    private int quantidade;

    @ManyToOne
    @NotNull(message = "O produto não pode ser nulo")
    private Produto produto;

    @ManyToOne
    @NotNull(message = "A venda não pode ser nula")
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

