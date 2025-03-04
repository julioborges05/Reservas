package br.com.fiap.reservas.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UsuarioEntityTest {

    @Test
    void validaNome() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new UsuarioEntity(null, "email", "senha"),
                "Nome Inválido"
        );
    }

    @Test
    void validaEmail() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new UsuarioEntity("nome", null, "senha"),
                "Email Inválido"
        );
    }

    @Test
    void validaSenha() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new UsuarioEntity("nome", "email", null),
                "Senha Inválida"
        );
    }
}
