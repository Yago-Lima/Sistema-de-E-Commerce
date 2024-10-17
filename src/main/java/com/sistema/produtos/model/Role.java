package com.sistema.produtos.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_ROLE")
public class Role extends AbstractEntity<Long> implements GrantedAuthority, Serializable {

    @Column(nullable = false, unique = true)
    private String nome;

    @ManyToMany(mappedBy = "roles")
    private List<Usuario> usuarios = new ArrayList<>();

    public Role(String nome) {
        this.nome = nome;
    }

    public Role() {
    }

    @Override
    public String getAuthority() {
        return nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

}
