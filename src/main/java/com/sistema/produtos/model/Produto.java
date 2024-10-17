package com.sistema.produtos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Entity
@Table(name = "TB_PRODUTO")
public class Produto extends AbstractEntity<Long>{


    @NotBlank(message = "não pode ser Vazio")
    @Size(max = 255, message = "Maximo de 255 caracteres atingido")
    private String nome;

    @NotNull(message = "não pode ser Vazio")
    @DecimalMin(value = "0.00", inclusive = false, message = "O valor deve ser maior que zero")
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
