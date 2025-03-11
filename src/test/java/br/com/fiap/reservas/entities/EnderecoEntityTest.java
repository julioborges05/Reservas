package br.com.fiap.reservas.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    void entidadeCriadaComSucesso_SemId() {
        EnderecoEntity endereco = new EnderecoEntity("13180000", "logradouro", "bairro", "cidade",
                "numero", "complemento");

        assertEquals("13180000", endereco.getCep());
        assertEquals("logradouro", endereco.getLogradouro());
        assertEquals("bairro", endereco.getBairro());
        assertEquals("cidade", endereco.getCidade());
        assertEquals("numero", endereco.getNumero());
        assertEquals("complemento", endereco.getComplemento());
    }

    @Test
    void entidadeCriadaComSucesso_ComId() {
        EnderecoEntity endereco = new EnderecoEntity(1L, "13180000", "logradouro", "bairro", "cidade",
                "numero", "complemento");

        assertEquals(1L, endereco.getId());
        assertEquals("13180000", endereco.getCep());
        assertEquals("logradouro", endereco.getLogradouro());
        assertEquals("bairro", endereco.getBairro());
        assertEquals("cidade", endereco.getCidade());
        assertEquals("numero", endereco.getNumero());
        assertEquals("complemento", endereco.getComplemento());
    }
}
