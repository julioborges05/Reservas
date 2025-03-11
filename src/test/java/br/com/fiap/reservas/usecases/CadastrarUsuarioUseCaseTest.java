package br.com.fiap.reservas.usecases;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CadastrarUsuarioUseCaseTest {

    @Test
    void cadastrarUsuario() {
        assertDoesNotThrow(() -> {
            CadastrarUsuarioUseCase.cadastrarUsuario("nome", "email", "senha");
        });
    }

}
