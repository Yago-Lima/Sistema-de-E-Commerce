package com.sistema.produtos.model;

import com.sistema.produtos.model.AbstractEntity;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
public class Usuario extends AbstractEntity<Long> implements UserDetails {

    public Usuario(String login, String password, List<Role> roles) {
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

    private String login;

    private String password;

    @ManyToMany
    private List<Role> roles = new ArrayList<>();

    public Usuario() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}