package br.com.fiap.reservas.infra.gateway;

import br.com.fiap.reservas.entities.UsuarioEntity;
import br.com.fiap.reservas.infra.repository.usuario.Usuario;
import br.com.fiap.reservas.infra.repository.usuario.UsuarioRepository;
import br.com.fiap.reservas.interfaces.IUsuarioGateway;

import java.util.Optional;

public class UsuarioRepositorioJpa implements IUsuarioGateway {

    private final UsuarioRepository usuarioRepository;

    public UsuarioRepositorioJpa(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioEntity buscarUsuarioPorId(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            return new UsuarioEntity(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha());
        }

        throw new RuntimeException("Usuário não encontrado");
    }
}
