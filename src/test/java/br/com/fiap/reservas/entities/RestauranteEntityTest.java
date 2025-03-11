package br.com.fiap.reservas.entities;

import br.com.fiap.reservas.enums.StatusMesa;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RestauranteEntityTest {

    private final EnderecoEntity enderecoEntity = new EnderecoEntity("1318000", "logradouro", "bairro", "cidade", "numero", "complemento");
    private final LocalTime horarioAbertura = LocalTime.of(10, 37);
    private final LocalTime horarioFechamento = LocalTime.of(17, 37);

    @Test
    void validaNome() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new RestauranteEntity(null, enderecoEntity, "tipoCozinha", horarioAbertura, horarioFechamento, 10),
                "Nome Inválido"
        );
    }

    @Test
    void validaEndereco() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new RestauranteEntity("nome", null, "tipoCozinha", horarioAbertura, horarioFechamento, 10),
                "Endereço Inválido"
        );
    }

    @Test
    void validaTipoCozinha() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new RestauranteEntity("nome", enderecoEntity, null, horarioAbertura, horarioFechamento, 10),
                "Tipo de Cozinha Inválida"
        );
    }

    @Test
    void validaHorarioAbertura() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new RestauranteEntity("nome", enderecoEntity, "tipoCozinha", null, horarioFechamento, 10),
                "Horário Inválido"
        );
    }

    @Test
    void validaHorarioFechamento() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new RestauranteEntity("nome", enderecoEntity, "tipoCozinha", horarioAbertura, null, 10),
                "Horário Inválido"
        );
    }

    @Test
    void validaCapacidade() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new RestauranteEntity("nome", enderecoEntity, "tipoCozinha", horarioAbertura, horarioFechamento, -1),
                "Capacidade Inválida"
        );
    }

    @Test
    void criaRestauranteEntityComSucesso() {
        MesaEntity mesaEntity = new MesaEntity(1, StatusMesa.LIVRE);

        RestauranteEntity restauranteEntity = new RestauranteEntity("nome", enderecoEntity, "tipoCozinha",
                horarioAbertura, horarioFechamento, 10, List.of(mesaEntity));

        assertEquals("nome", restauranteEntity.getNome());
        assertEquals(enderecoEntity, restauranteEntity.getEndereco());
        assertEquals("tipoCozinha", restauranteEntity.getTipoCozinha());
        assertEquals(horarioAbertura, restauranteEntity.getHorarioAbertura());
        assertEquals(horarioFechamento, restauranteEntity.getHorarioFechamento());
        assertEquals(10, restauranteEntity.getCapacidade());
        assertEquals(List.of(mesaEntity), restauranteEntity.getListaMesa());
    }
}
