package com.sistema.produtos.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_PESSOA")
public class Pessoa extends AbstractEntity<Long> {

    @NotBlank(message = "O nome não pode estar vazio")
    @Size(max = 100, message = "O nome não pode ter mais de 100 caracteres")
    private String nome;

    @Size(max = 255, message = "O endereço não pode ter mais de 255 caracteres")
    private String endereco;

    @Pattern(regexp = "\\d{10,11}", message = "O telefone deve ter 10 ou 11 dígitos")
    private String telefone;

    private String cpf;

    private String cnpj;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;


    @OneToMany(mappedBy = "cliente" ,cascade = CascadeType.ALL)
    private List<Venda> vendas;
    public Pessoa(long l) {
        super(l);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Pessoa(String nome, String endereco, String telefone, String cpf, String cnpj, Usuario usuario) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.usuario = usuario;
        this.vendas = new ArrayList<>();
    }

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

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

