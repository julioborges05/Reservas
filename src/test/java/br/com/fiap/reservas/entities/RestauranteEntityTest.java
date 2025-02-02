package br.com.fiap.reservas.entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RestauranteEntityTest {

    private final EnderecoEntity enderecoEntity = new EnderecoEntity("1318000", "logradouro", "bairro", "cidade", "numero", "complemento");
    private final LocalDateTime horarioAbertura = LocalDateTime.of(2025, 02, 02, 10, 37);
    private final LocalDateTime horarioFechamento = LocalDateTime.of(2025, 02, 02, 17, 37);

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
}
