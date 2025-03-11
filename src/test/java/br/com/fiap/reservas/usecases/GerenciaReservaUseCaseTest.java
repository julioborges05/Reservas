package br.com.fiap.reservas.usecases;

import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.entities.MesaEntity;
import br.com.fiap.reservas.entities.ReservaEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.enums.StatusMesa;
import br.com.fiap.reservas.enums.StatusReserva;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GerenciaReservaUseCaseTest {

    private final MesaEntity mesaEntity = new MesaEntity(1, StatusMesa.LIVRE);
    private final EnderecoEntity enderecoEntity = new EnderecoEntity("1318000", "logradouro", "bairro", "cidade", "numero", "complemento");
    private final LocalTime horarioAbertura = LocalTime.of(10, 0);
    private final LocalTime horarioFechamento = LocalTime.of(17, 0);
    private final LocalDateTime horarioChegada = LocalDateTime.of(2025, 2, 2, 11, 0);

    @Mock
    ReservaEntity reservaEntity;

    @Test
    void retornaErroPorCapacidadeInvalida() {
        RestauranteEntity restauranteEntity = new RestauranteEntity("Restaurante", enderecoEntity, "tipoCozinha", horarioAbertura, horarioFechamento, 2, List.of(mesaEntity));

        ReservaEntity reserva = new ReservaEntity(restauranteEntity, "Lucas", 16);

        assertThrows(
                RuntimeException.class,
                () -> GerenciaReservaUseCase.atualizarQtdPessoasReserva(reserva, 8, List.of(mesaEntity),
                        restauranteEntity),
                "Capacidade Inválida"
        );
    }

    @Test
    void retornaErroPorNumeroMesasIndisponivel() {
        List<MesaEntity> mesasLivres = List.of(mesaEntity);
        RestauranteEntity restauranteEntity = new RestauranteEntity("Restaurante", enderecoEntity,
                "tipoCozinha", horarioAbertura, horarioFechamento, 10, mesasLivres);

        ReservaEntity reserva = new ReservaEntity(restauranteEntity, "Lucas", 10);

        assertThrows(
                RuntimeException.class,
                () -> GerenciaReservaUseCase.atualizarQtdPessoasReserva(reserva, 8, mesasLivres, restauranteEntity),
                "Número de mesas indisponível"
        );
    }

    @Test
    void atualizaReservaComSucesso() {
        RestauranteEntity restauranteEntity = new RestauranteEntity("Restaurante", enderecoEntity, "tipoCozinha", horarioAbertura, horarioFechamento, 10, List.of(mesaEntity));

        List<MesaEntity> mesasLivres = List.of(new MesaEntity(1, StatusMesa.LIVRE));

        ReservaEntity reservaEntity = new ReservaEntity(1L, restauranteEntity, "Usuario", new ArrayList<>());

        ReservaEntity reserva = GerenciaReservaUseCase.atualizarQtdPessoasReserva(reservaEntity, 4, mesasLivres, restauranteEntity);

        assertNotNull(reserva);
        assertEquals(restauranteEntity, reserva.getRestaurante());
        assertEquals("Usuario", reserva.getNomeUsuario());
        assertEquals(1, reserva.getReservaVMesaList().size());
        assertEquals(StatusReserva.RESERVADA, reserva.getReservaVMesaList().getFirst().getStatus());
    }
}