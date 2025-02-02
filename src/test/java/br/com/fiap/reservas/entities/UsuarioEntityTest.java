package br.com.fiap.reservas.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UsuarioEntityTest {

    @Test
    void validaNome() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new UsuarioEntity(null),
                "Nome Inválido"
        );
    }
}
