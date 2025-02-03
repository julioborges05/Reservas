package br.com.fiap.reservas.entities;

import br.com.fiap.reservas.usecases.AvaliaRestauranteUseCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AvaliacaoEntityTest {

    @Test
    void retornaErroComNotaMenorQueZero() {
        assertThrows(
                IllegalArgumentException.class,
                () -> AvaliaRestauranteUseCase.avaliarRestaurante(-1, "comentario", new UsuarioEntity("usuario")),
                "Nota inválida"
        );
    }
    @Test
    void retornaErroComNotaMaiorQueCinco() {
        assertThrows(
                IllegalArgumentException.class,
                () -> AvaliaRestauranteUseCase.avaliarRestaurante(6, "comentario", new UsuarioEntity("usuario")),
                "Nota inválida"
        );
    }
    @Test
    void retornaErroComComentarioNulo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> AvaliaRestauranteUseCase.avaliarRestaurante(5, null, new UsuarioEntity("usuario")),
                "Comentário inválido"
        );
    }
    @Test
    void retornaErroComComentarioVazio() {
        assertThrows(
                IllegalArgumentException.class,
                () -> AvaliaRestauranteUseCase.avaliarRestaurante(5, "", new UsuarioEntity("usuario")),
                "Comentário inválido"
        );
    }
    @Test
    void retornaErroComUsuarioNulo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> AvaliaRestauranteUseCase.avaliarRestaurante(5, "comentario", null),
                "Usuário inválido"
        );
    }

}
