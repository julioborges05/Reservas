package br.com.fiap.reservas.entities;

import io.micrometer.common.util.StringUtils;

public class UsuarioEntity {

    private Long id;
    private String nome;
    private String email;
    private String senha;

    public UsuarioEntity() {}

    public UsuarioEntity(String nome, String email, String senha) {
        validaNome(nome);
        validaEmail(email);
        validaSenha(senha);

        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public UsuarioEntity(Long id, String nome, String email, String senha) {
        this(nome, email, senha);
        validaId(id);

        this.id = id;
    }

    private static void validaId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id Inválido");
        }
    }

    private static void validaSenha(String senha) {
        if (StringUtils.isBlank(senha)) {
            throw new IllegalArgumentException("Senha Inválida");
        }
    }

    private static void validaEmail(String email) {
        if (StringUtils.isBlank(email)) {
            throw new IllegalArgumentException("Email Inválido");
        }
    }

    private static void validaNome(String nome) {
        if (StringUtils.isBlank(nome)) {
            throw new IllegalArgumentException("Nome Inválido");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
