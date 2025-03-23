package br.com.fiap.reservas.controller.dto;

import br.com.fiap.reservas.entities.UsuarioEntity;

public record UsuarioDto(Long id, String nome, String email, String senha) {

    public UsuarioDto(UsuarioEntity usuarioEntity) {
        this(usuarioEntity.getId(), usuarioEntity.getNome(), usuarioEntity.getEmail(), usuarioEntity.getSenha());
    }

}
