package com.sistema.produtos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_PESSOA")
public class Pessoa extends AbstractEntity<Long> {

    private String nome;
    private String endereco;
    private String telefone;
    private String cpf;
    private String cnpj;
    @OneToMany(mappedBy = "cliente")
    private List<Venda> vendas;

    public Pessoa(String nome, String endereco, String telefone, String cpf, String cnpj) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.vendas = new ArrayList<>();
    }

    public Pessoa() {

    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public String nomeClasse(){
        return this.getClass().getSimpleName().toLowerCase();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
