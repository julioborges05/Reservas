package br.com.fiap.reservas.gateway;

import br.com.fiap.reservas.entities.UsuarioEntity;
import br.com.fiap.reservas.interfaces.IUsuarioGateway;

public class UsuarioGateway implements IUsuarioGateway {

    private final IUsuarioGateway usuarioDatabaseGateway;

    public UsuarioGateway(IUsuarioGateway usuarioDatabaseGateway) {
        this.usuarioDatabaseGateway = usuarioDatabaseGateway;
    }

    @Override
    public UsuarioEntity buscarUsuarioPorId(Long id) {
        return usuarioDatabaseGateway.buscarUsuarioPorId(id);
    }

    @Override
    public UsuarioEntity cadastrarUsuario(UsuarioEntity usuarioEntity) {
        return usuarioDatabaseGateway.cadastrarUsuario(usuarioEntity);
    }
}
