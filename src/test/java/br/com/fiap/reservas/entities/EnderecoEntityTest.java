package br.com.fiap.reservas.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EnderecoEntityTest {

    @Test
    void retornaErroComCepNulo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new EnderecoEntity(null, "logradouro", "bairro", "cidade", "numero", "complemento"),
                "CEP inválido"
        );
    }

    @Test
    void retornaErroComLogradouroNulo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new EnderecoEntity("13180000", null, "bairro", "cidade", "numero", "complemento"),
                "Logradouro inválido"
        );
    }

    @Test
    void retornaErroComBairroNulo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new EnderecoEntity("13180000", "logradouro", null, "cidade", "numero", "complemento"),
                "Bairro inválido"
        );
    }

    @Test
    void retornaErroComCidadeNulo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new EnderecoEntity("13180000", "logradouro", "bairro", null, "numero", "complemento"),
                "Cidade inválido"
        );
    }

    @Test
    void retornaErroComNumeroNulo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new EnderecoEntity("13180000", "logradouro", "bairro", "cidade", null, "complemento"),
                "Número inválido"
        );
    }

    @Test
    void retornaErroComComplementoNulo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new EnderecoEntity("13180000", "logradouro", "bairro", "cidade", "numero", null),
                "Complemento inválido"
        );
    }

    @Test
    void entidadeCriadaComSucesso() {
        assertDoesNotThrow(
                () -> new EnderecoEntity("13180000", "logradouro", "bairro", "cidade", "numero", "complemento")
        );
    }
}
