package br.com.fiap.reservas.infra.repository.usuario;

import br.com.fiap.reservas.entities.UsuarioEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;

    public Usuario() {
    }

    public Usuario(UsuarioEntity usuarioEntity) {
        this.id = usuarioEntity.getId();
        this.nome = usuarioEntity.getNome();
        this.email = usuarioEntity.getEmail();
        this.senha = usuarioEntity.getSenha();
    }
    public Usuario(Long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
