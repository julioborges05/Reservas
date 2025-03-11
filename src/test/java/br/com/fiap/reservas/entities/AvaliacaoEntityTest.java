package br.com.fiap.reservas.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AvaliacaoEntityTest {

    @Test
    void retornaErroComNotaMenorQueZero() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new AvaliacaoEntity(-1, "comentario", 1L, 2L),
                "Nota inválida"
        );
    }

    @Test
    void retornaErroComNotaMaiorQueCinco() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new AvaliacaoEntity(6, "comentario", 1L, 2L),
                "Nota inválida"
        );
    }

    @Test
    void retornaErroComComentarioNulo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new AvaliacaoEntity(5, null, 1L, 2L),
                "Comentário inválido"
        );
    }

    @Test
    void retornaErroComComentarioVazio() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new AvaliacaoEntity(5, "", 1L, 2L),
                "Comentário inválido"
        );
    }

    @Test
    void retornaErroComUsuarioNulo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new AvaliacaoEntity(5, "comentario", null, 2L),
                "Usuário inválido"
        );
    }

    @Test
    void retornaErroComRestauranteNulo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new AvaliacaoEntity(5, "comentario", 1L, null),
                "Restaurante inválido"
        );
    }

    @Test
    void criaAvaliacaoComSucesso() {
        AvaliacaoEntity avaliacao = new AvaliacaoEntity(5, "comentario", 1L, 2L);

        assertEquals(5, avaliacao.getNota());
        assertEquals("comentario", avaliacao.getComentario());
        assertEquals(1L, avaliacao.getUsuarioId());
        assertEquals(2L, avaliacao.getRestaurante());
    }

}
