package com.sistema.produtos.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_ROLE")
public class Role extends AbstractEntity<Long> implements  GrantedAuthority {

    private String nome;

    @ManyToMany(mappedBy = "roles")
    private List<Usuario> usuarios;

    public Role(String nome) {
        this.nome = nome;
    }

    public Role(){ usuarios = new ArrayList<>();
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