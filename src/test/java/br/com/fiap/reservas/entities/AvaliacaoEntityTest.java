package br.com.fiap.reservas.entities;

import br.com.fiap.reservas.usecases.AvaliaRestauranteUseCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AvaliacaoEntityTest {

    @Test
    void retornaErroComNotaMenorQueZero() {
        assertThrows(
                IllegalArgumentException.class,
                () -> AvaliaRestauranteUseCase.avaliarRestaurante(-1, "comentario"),
                "Nota inválida"
        );
    }
    @Test
    void retornaErroComNotaMaiorQueCinco() {
        assertThrows(
                IllegalArgumentException.class,
                () -> AvaliaRestauranteUseCase.avaliarRestaurante(6, "comentario"),
                "Nota inválida"
        );
    }
    @Test
    void retornaErroComComentarioNulo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> AvaliaRestauranteUseCase.avaliarRestaurante(5, null),
                "Comentário inválido"
        );
    }
}
