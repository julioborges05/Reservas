package br.com.fiap.reservas.controller;

import br.com.fiap.reservas.controller.dto.ReservaDto;
import br.com.fiap.reservas.entities.*;
import br.com.fiap.reservas.enums.StatusMesa;
import br.com.fiap.reservas.enums.StatusReserva;
import br.com.fiap.reservas.infra.repository.mesa.MesaPK;
import br.com.fiap.reservas.infra.repository.reserva.ReservaVMesa;
import br.com.fiap.reservas.interfaces.IMesaGateway;
import br.com.fiap.reservas.interfaces.IReservaGateway;
import br.com.fiap.reservas.interfaces.IReservaVMesaGateway;
import br.com.fiap.reservas.interfaces.IRestauranteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CadastrarReservaControllerTest {

    @Mock
    private IReservaGateway reservaGateway;

    @Mock
    private IRestauranteGateway restauranteGateway;

    @Mock
    private IMesaGateway mesasGateway;

    @Mock
    private IReservaVMesaGateway reservaVMesaGateway;

    private CadastrarReservaController cadastrarReservaController;

    private final EnderecoEntity enderecoEntity = new EnderecoEntity("1318000", "logradouro", "bairro", "cidade", "numero", "complemento");

    private final LocalDateTime horarioChegada = LocalDateTime.of(2025, 2, 2, 10, 37);
    private final ReservaVMesaEntity reservaVMesa = new ReservaVMesaEntity(1L, 1L,new MesaPK(1L, 1), StatusReserva.LIVRE);
    private final MesaEntity mesaEntity = new MesaEntity(1, StatusMesa.LIVRE);
    private final RestauranteEntity restaurante = new RestauranteEntity("nome", enderecoEntity, "tipoCozinha", LocalTime.now(), LocalTime.now(), 10, List.of(mesaEntity));


    @BeforeEach
    void setUp() {
        try (AutoCloseable ignored = MockitoAnnotations.openMocks(this)) {
            cadastrarReservaController = new CadastrarReservaController(
                    reservaGateway, restauranteGateway, mesasGateway, reservaVMesaGateway);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void validaCadastrarReservaComErro() throws Exception {
        ReservaDto dto = new ReservaDto(new ReservaEntity(1L, restaurante, "nome", List.of(reservaVMesa), horarioChegada));
        when(restauranteGateway.findById(dto.restauranteId())).thenThrow(new RuntimeException("Restaurante não encontrado"));

        assertThrows(RuntimeException.class,
                () -> cadastrarReservaController.cadastrarReserva(dto),
                "Restaurante não encontrado"
        );
    }

    @Test
    public void validaCadastrarReservaComSucesso() throws Exception {
        ReservaDto reservaDto = new ReservaDto(new ReservaEntity(1L, restaurante, "nome", List.of(reservaVMesa), horarioChegada));

        when(restauranteGateway.findById(reservaDto.restauranteId())).thenReturn(restaurante);

        List<MesaEntity> mesasLivres = List.of(mesaEntity);
        when(mesasGateway.buscarMesasLivresPorRestaurante(reservaDto.restauranteId())).thenReturn(mesasLivres);

        ReservaVMesaEntity reservaVMesaMock = new ReservaVMesaEntity(StatusReserva.RESERVADA);
        List<ReservaVMesaEntity> mesasParaReservar = List.of(reservaVMesaMock);
        ReservaEntity reservaEntityMock = mock(ReservaEntity.class);
        when(reservaEntityMock.getReservaVMesaList()).thenReturn(mesasParaReservar);

        when(reservaGateway.cadastrarReserva(any(), any(), any(), any())).thenReturn(reservaEntityMock);

        ReservaDto result = cadastrarReservaController.cadastrarReserva(reservaDto);

        assertEquals(reservaDto, result);
    }
}
