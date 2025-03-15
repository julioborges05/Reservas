package br.com.fiap.reservas.controller;

import br.com.fiap.reservas.controller.dto.BuscarReservasDto;
import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.entities.MesaEntity;
import br.com.fiap.reservas.entities.ReservaEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.enums.StatusMesa;
import br.com.fiap.reservas.infra.gateway.MesaRepositorioJpa;
import br.com.fiap.reservas.infra.gateway.ReservaRepositorioJpa;
import br.com.fiap.reservas.infra.gateway.RestauranteRepositorioJpa;
import br.com.fiap.reservas.interfaces.IMesaGateway;
import br.com.fiap.reservas.interfaces.IReservaGateway;
import br.com.fiap.reservas.interfaces.IRestauranteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BuscarReservasControllerTest {

    @Mock
    private ReservaRepositorioJpa reservaGateway;

    @Mock
    private RestauranteRepositorioJpa restauranteGateway;

    private BuscarReservasController buscarReservasController;

    private final EnderecoEntity enderecoEntity = new EnderecoEntity("1318000", "logradouro", "bairro", "cidade", "numero", "complemento");

    private final MesaEntity mesaEntity = new MesaEntity(1, StatusMesa.LIVRE);
    private final RestauranteEntity restaurante = new RestauranteEntity("nome", enderecoEntity,
            "tipoCozinha", LocalTime.now(), LocalTime.now(), 10, List.of(mesaEntity));

    private final ReservaEntity reserva = new ReservaEntity(1L, restaurante, "Usuario", new ArrayList<>());

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        buscarReservasController = new BuscarReservasController(reservaGateway, restauranteGateway);
    }

    @Test
    public void validaBuscarReservasComRestauranteNaoEncontrado() {
        when(restauranteGateway.findById(any())).thenThrow(new RuntimeException("Restaurante não encontrado"));

        assertThrows(RuntimeException.class,
                () -> buscarReservasController.buscarReservasPorRestaurante(1L),
                "Restaurante não encontrado"
        );
    }

    @Test
    public void validaBuscarReservasComSucesso() {
        when(restauranteGateway.findById(anyLong())).thenReturn(restaurante);
        when(reservaGateway.buscarReservasPorRestaurante(anyLong())).thenReturn(List.of(reserva));

        List<BuscarReservasDto> buscarReservas = buscarReservasController.buscarReservasPorRestaurante(1L);

        assertNotNull(buscarReservas);
        assertEquals(buscarReservas.get(0).nomeUsuario(), reserva.getNomeUsuario());
        assertEquals(buscarReservas.get(0).restauranteId(), 1L);
    }
}
