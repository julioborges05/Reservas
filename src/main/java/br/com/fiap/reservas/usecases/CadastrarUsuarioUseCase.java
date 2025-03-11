package br.com.fiap.reservas.usecases;

import br.com.fiap.reservas.entities.UsuarioEntity;

public class CadastrarUsuarioUseCase {

    public static UsuarioEntity cadastrarUsuario(String nome, String email, String senha) {
        return new UsuarioEntity(nome, email, senha);
    }
}
