package br.com.fiap.reservas.entities;

import io.micrometer.common.util.StringUtils;

public class UsuarioEntity {

    private Long id;
    private final String nome;
    private final String email;
    private final String senha;

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
