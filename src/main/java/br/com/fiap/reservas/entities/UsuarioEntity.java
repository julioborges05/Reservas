package br.com.fiap.reservas.entities;

import io.micrometer.common.util.StringUtils;

public class UsuarioEntity {
    private final String nome;

    public UsuarioEntity(String nome) {
        if (StringUtils.isBlank(nome)) {
            throw new IllegalArgumentException("Nome Inválido");
        }
        this.nome = nome;
    }
}
