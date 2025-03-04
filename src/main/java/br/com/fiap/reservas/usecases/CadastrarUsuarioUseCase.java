package br.com.fiap.reservas.usecases;

import br.com.fiap.reservas.entities.UsuarioEntity;

public class CadastrarUsuarioUseCase {

    public static UsuarioEntity cadastrarUsuario(String nome, String email, String senha) {
        if (nome == null || nome.isEmpty()) {
            throw new RuntimeException("Nome inválido");
        }
        if (email == null || email.isEmpty()) {
            throw new RuntimeException("Email inválido");
        }
        if (senha == null || senha.isEmpty()) {
            throw new RuntimeException("Senha inválida");
        }

        return new UsuarioEntity(nome, email, senha);
    }
}
