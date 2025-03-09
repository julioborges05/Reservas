package br.com.fiap.reservas.usecases;

import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.entities.MesaEntity;
import br.com.fiap.reservas.entities.ReservaEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.enums.StatusMesa;
import br.com.fiap.reservas.enums.StatusReserva;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CadastrarReservaUseCaseTest {

    private final MesaEntity mesaEntity = new MesaEntity(1, StatusMesa.LIVRE);
    private final EnderecoEntity enderecoEntity = new EnderecoEntity("1318000", "logradouro", "bairro", "cidade", "numero", "complemento");
    private final LocalTime horarioAbertura = LocalTime.of(10, 0);
    private final LocalTime horarioFechamento = LocalTime.of(17, 0);
    private final LocalDateTime horarioChegada = LocalDateTime.of(2025, 2, 2, 11, 0);

    @Test
    void retornaErroPorCapacidadeInvalida() {
        RestauranteEntity restauranteEntity = new RestauranteEntity("Restaurante", enderecoEntity, "tipoCozinha", horarioAbertura, horarioFechamento, 2, List.of(mesaEntity));

        assertThrows(
                RuntimeException.class,
                () -> CadastrarReservaUseCase.cadastrarReserva(restauranteEntity, "nome", 15, List.of(mesaEntity), horarioChegada),
                "Capacidade Inválida"
        );
    }

    @Test
    void retornaErroPorNumeroMesasIndisponivel() {
        RestauranteEntity restauranteEntity = new RestauranteEntity("Restaurante", enderecoEntity, "tipoCozinha", horarioAbertura, horarioFechamento, 10, List.of(mesaEntity));

        List<MesaEntity> mesasLivres = List.of(new MesaEntity(1, StatusMesa.LIVRE));

        assertThrows(
                RuntimeException.class,
                () -> CadastrarReservaUseCase.cadastrarReserva(restauranteEntity, "nome", 5, mesasLivres, LocalDateTime.now()),
                "Número de mesas indisponível"
        );
    }

    @Test
    void retornaReservaComSucesso() {
        RestauranteEntity restauranteEntity = new RestauranteEntity("Restaurante", enderecoEntity, "tipoCozinha", horarioAbertura, horarioFechamento, 10, List.of(mesaEntity));

        List<MesaEntity> mesasLivres = List.of(new MesaEntity(1, StatusMesa.LIVRE));

        ReservaEntity reserva = CadastrarReservaUseCase.cadastrarReserva(
                restauranteEntity, "nome", 2, mesasLivres, LocalDateTime.now()
        );

        assertNotNull(reserva);
        assertEquals(restauranteEntity, reserva.getRestaurante());
        assertEquals("nome", reserva.getNomeUsuario());
        assertEquals(1, reserva.getReservaVMesaList().size());
        assertEquals(StatusReserva.RESERVADA, reserva.getReservaVMesaList().getFirst().getStatus());
    }
}
