package br.com.fiap.reservas.controller;

import br.com.fiap.reservas.entities.UsuarioEntity;
import br.com.fiap.reservas.interfaces.IUsuarioGateway;
import br.com.fiap.reservas.usecases.CadastrarUsuarioUseCase;

public class CadastrarUsuarioController {

    private final IUsuarioGateway usuarioGateway;

    public CadastrarUsuarioController(IUsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public void cadastrarUsuario(String nome, String email, String senha) {
        UsuarioEntity usuarioEntity = CadastrarUsuarioUseCase.cadastrarUsuario(nome, email, senha);

        usuarioGateway.cadastrarUsuario(usuarioEntity);
    }

}
