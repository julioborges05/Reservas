package br.com.fiap.reservas.entities;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AvaliacaoEntityTest {

    private final EnderecoEntity enderecoEntity = new EnderecoEntity("1318000", "logradouro", "bairro", "cidade", "numero", "complemento");
    private final LocalTime horarioAbertura = LocalTime.of(10, 37);
    private final LocalTime horarioFechamento = LocalTime.of(17, 37);

    private final UsuarioEntity usuario = new UsuarioEntity("usuario", "senha", "email");
    private final RestauranteEntity restaurante = new RestauranteEntity("nome", enderecoEntity, "tipoCozinha",
            horarioAbertura, horarioFechamento, 10);

    @Test
    void retornaErroComNotaMenorQueZero() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new AvaliacaoEntity(-1, "comentario", usuario, restaurante),
                "Nota inválida"
        );
    }

    @Test
    void retornaErroComNotaMaiorQueCinco() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new AvaliacaoEntity(6, "comentario", usuario, restaurante),
                "Nota inválida"
        );
    }

    @Test
    void retornaErroComComentarioNulo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new AvaliacaoEntity(5, null, usuario, restaurante),
                "Comentário inválido"
        );
    }

    @Test
    void retornaErroComComentarioVazio() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new AvaliacaoEntity(5, "", usuario, restaurante),
                "Comentário inválido"
        );
    }

    @Test
    void retornaErroComUsuarioNulo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new AvaliacaoEntity(5, "comentario", null, restaurante),
                "Usuário inválido"
        );
    }

    @Test
    void retornaErroComRestauranteNulo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new AvaliacaoEntity(5, "comentario", usuario, null),
                "Restaurante inválido"
        );
    }

    @Test
    void criaAvaliacaoComSucesso() {
        AvaliacaoEntity avaliacao = new AvaliacaoEntity(5, "comentario", usuario, restaurante);

        assertEquals(5, avaliacao.getNota());
        assertEquals("comentario", avaliacao.getComentario());
        assertEquals(usuario, avaliacao.getUsuario());
        assertEquals(restaurante, avaliacao.getRestaurante());
    }

}
