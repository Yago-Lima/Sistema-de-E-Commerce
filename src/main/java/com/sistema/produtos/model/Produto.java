package com.sistema.produtos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Entity
@Table(name = "TB_PRODUTO")
public class Produto extends AbstractEntity<Long>{


    @NotBlank(message = "Descrição não pode ser Vazio")
    @Size(max = 255, message = "Maximo de 255 caracteres atingido")
    private String nome;

    private BigDecimal valor;

    public String getNome() {
        return nome;
    }

    public void setNome(String descricao) {
        this.nome = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }


    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
