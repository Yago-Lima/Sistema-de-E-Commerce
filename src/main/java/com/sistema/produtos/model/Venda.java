package com.sistema.produtos.model;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_VENDA")
@Scope("session")
@Component
public class Venda extends AbstractEntity<Long> implements Serializable{


    @ManyToOne
    private Pessoa cliente;

    private LocalDateTime data;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    private List<ItemVenda> itemVendaList = new ArrayList<>();

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
