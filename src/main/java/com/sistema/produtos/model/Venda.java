package com.sistema.produtos.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TB_VENDA")
public class Venda extends AbstractEntity<Long>{


    @ManyToOne
    private Pessoa cliente;

    @Column( columnDefinition = "TIMESTAMP")
    private LocalDateTime data;

    @OneToMany(mappedBy = "venda")
    private List<ItemVenda> itemVendaList;

    public Venda() {
        this.data = LocalDateTime.now();
    }

    public LocalDateTime getData() {
        return data;
    }

    public double total() {
        double total = 0.0;
        for (ItemVenda itemVenda :itemVendaList){
            total += itemVenda.total().doubleValue();
        }
        return total;
    }

    public Pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }

    public List<ItemVenda> getItemVendaList() {
        return itemVendaList;
    }

    public void setItemVendaList(List<ItemVenda> itemVendaList) {
        this.itemVendaList = itemVendaList;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }


}
