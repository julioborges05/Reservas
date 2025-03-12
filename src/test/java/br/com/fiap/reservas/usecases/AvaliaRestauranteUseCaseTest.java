package br.com.fiap.reservas.usecases;

import br.com.fiap.reservas.entities.AvaliacaoEntity;
import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.entities.UsuarioEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AvaliaRestauranteUseCaseTest {


    private final EnderecoEntity enderecoEntity = new EnderecoEntity("1318000", "logradouro", "bairro", "cidade", "numero", "complemento");
    private final LocalTime horarioAbertura = LocalTime.of(10, 37);
    private final LocalTime horarioFechamento = LocalTime.of(17, 37);

    @Test
    void criarAvaliacaoDoRestauranteCriaAvaliacaoEntity() {
        UsuarioEntity usuario = new UsuarioEntity("Teste", "email", "senha");
        RestauranteEntity restauranteEntity = new RestauranteEntity("nome", enderecoEntity, "tipoCozinha",
                horarioAbertura, horarioFechamento, 10);

        AvaliacaoEntity avaliacao = AvaliaRestauranteUseCase.criarAvaliacaoDoRestaurante(5, "Muito bom",
                usuario, restauranteEntity);

        assertEquals(5, avaliacao.getNota());
        assertEquals("Muito bom", avaliacao.getComentario());
        assertEquals(usuario, avaliacao.getUsuario());
        assertEquals(restauranteEntity, avaliacao.getRestaurante());
    }

}
