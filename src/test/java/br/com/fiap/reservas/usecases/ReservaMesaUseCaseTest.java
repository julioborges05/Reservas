package br.com.fiap.reservas.usecases;

import br.com.fiap.reservas.entities.EnderecoEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReservaMesaUseCaseTest {

    private final EnderecoEntity enderecoEntity = new EnderecoEntity("1318000", "logradouro", "bairro", "cidade", "numero", "complemento");
    private final LocalDateTime horarioAbertura = LocalDateTime.of(2025, 02, 02, 10, 37);
    private final LocalDateTime horarioFechamento = LocalDateTime.of(2025, 02, 02, 17, 37);

    private CadastrarReservaUseCase reservaMesaUseCase = new CadastrarReservaUseCase();
//    private RestauranteEntity restauranteEntity = new RestauranteEntity("Nome", enderecoEntity, "tipoCozinha", horarioAbertura, horarioFechamento, 10, listaMesa);

//    @Test
//    void retornaErroPorCapacidadeInvalida() {
//        assertThrows(
//                RuntimeException.class,
//                () -> reservaMesaUseCase.registrarReserva(restauranteEntity, new UsuarioEntity("nome"), 15),
//                "Capacidade Inválida"
//        );
//    }
//
//    @Test
//    void reservaRealizadaComSucesso() {
//        reservaMesaUseCase.registrarReserva(restauranteEntity, new UsuarioEntity("nome"), 3);
//
//        assertEquals(7, restauranteEntity.getCapacidade());
//    }
}
