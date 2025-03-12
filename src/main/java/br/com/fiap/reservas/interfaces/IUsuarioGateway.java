package br.com.fiap.reservas.interfaces;

import br.com.fiap.reservas.entities.UsuarioEntity;

public interface IUsuarioGateway {

    UsuarioEntity buscarUsuarioPorId(Long id);

    UsuarioEntity cadastrarUsuario(UsuarioEntity usuarioEntity);

}
