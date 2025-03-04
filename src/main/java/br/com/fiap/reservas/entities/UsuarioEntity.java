package br.com.fiap.reservas.entities;

import io.micrometer.common.util.StringUtils;

public class UsuarioEntity {
    private final String nome;
    private final String email;
    private final String senha;

    public UsuarioEntity(String nome, String email, String senha) {
        if (StringUtils.isBlank(nome)) {
            throw new IllegalArgumentException("Nome Inválido");
        }
        if (StringUtils.isBlank(email)) {
            throw new IllegalArgumentException("Email Inválido");
        }
        if (StringUtils.isBlank(senha)) {
            throw new IllegalArgumentException("Senha Inválida");
        }
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
}
