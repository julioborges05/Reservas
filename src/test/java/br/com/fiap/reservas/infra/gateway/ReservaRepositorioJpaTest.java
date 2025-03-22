package br.com.fiap.reservas.infra.gateway;

import br.com.fiap.reservas.entities.*;
import br.com.fiap.reservas.enums.StatusMesa;
import br.com.fiap.reservas.enums.StatusReserva;
import br.com.fiap.reservas.infra.repository.mesa.MesaPK;
import br.com.fiap.reservas.infra.repository.reserva.Reserva;
import br.com.fiap.reservas.infra.repository.reserva.ReservaRepository;
import br.com.fiap.reservas.infra.repository.reserva.ReservaVMesa;
import br.com.fiap.reservas.infra.repository.restaurante.Restaurante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReservaRepositorioJpaTest {

    private ReservaRepositorioJpa reservaRepositorioJpa;

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private EnderecoRepositorioJpa enderecoRepositorioJpa;

    @Mock
    private RestauranteRepositorioJpa restauranteRepositorioJpa;


    private final EnderecoEntity enderecoEntityMock = new EnderecoEntity("13181701", "logradouro",
            "bairro", "cidade", "numero", "complemento");

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reservaRepositorioJpa = new ReservaRepositorioJpa(reservaRepository, enderecoRepositorioJpa, restauranteRepositorioJpa);
    }

    @Test
    void deveBuscarReservasPorRestaurante() {
        Long restauranteId = 1L;
        Restaurante restauranteMock = new Restaurante(1L, "restaurante", 1L, "tipo", LocalTime.now(), LocalTime.now(), 50);
        Reserva reservaMock = new Reserva(1L, "nome", restauranteMock , List.of(), LocalDateTime.now());


        when(reservaRepository.findByRestauranteId(restauranteId, StatusReserva.RESERVADA)).thenReturn(List.of(reservaMock));
        when(enderecoRepositorioJpa.buscarEnderecoPeloId(restauranteMock.getIdEndereco())).thenReturn(enderecoEntityMock);

        List<ReservaEntity> resultado = reservaRepositorioJpa.buscarReservasPorRestaurante(restauranteId);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("nome", resultado.getFirst().getNomeUsuario());
        verify(reservaRepository).findByRestauranteId(restauranteId, StatusReserva.RESERVADA);
    }

    @Test
    void deveCadastrarReserva() {
        RestauranteEntity restauranteEntity = new RestauranteEntity(1L, "restaurante", enderecoEntityMock, "tipo",
                LocalTime.now(), LocalTime.now(), 50, new ArrayList<>());
        List<ReservaVMesa> reservaVMesas = List.of(new ReservaVMesa(1L, 1L, new MesaPK(1L, 2),
                StatusReserva.RESERVADA));
        LocalDateTime horaChegada = LocalDateTime.now();

        Reserva reservaMock = new Reserva(new Restaurante(restauranteEntity), "nome", reservaVMesas, horaChegada);

        List<ReservaVMesaEntity> reservaVMesaEntity = List.of(new ReservaVMesaEntity(1L, 1L,
                new MesaPK(1L, 2), StatusReserva.RESERVADA));

        when(restauranteRepositorioJpa.buscarRestaurantePorId(anyLong())).thenReturn(restauranteEntity);
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reservaMock);

        ReservaEntity resultado = reservaRepositorioJpa.cadastrarReserva(restauranteEntity, "nome", reservaVMesaEntity, horaChegada);

        assertNotNull(resultado);
        assertEquals("nome", resultado.getNomeUsuario());
        verify(reservaRepository).save(any(Reserva.class));
    }

    @Test
    void deveLancarExcecaoQuandoReservaNaoEncontradaAtualizarStatus() {
        when(reservaRepository.findByNomeUsuario(anyString(), any())).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> reservaRepositorioJpa.atualizarStatusReserva("nome", "01/01/2025 10:00"));

        assertEquals("Reserva não encontrada", exception.getMessage());
    }

    @Test
    void deveAtualizarQtdPessoasReserva() {
        RestauranteEntity restauranteEntity = new RestauranteEntity("restaurante", enderecoEntityMock, "tipo",
                LocalTime.now(), LocalTime.now(), 50);
        ReservaEntity reservaEntity = new ReservaEntity(1L, restauranteEntity, "Usuario", List.of(), LocalDateTime.now());
        Reserva reservaMock = new Reserva(new Restaurante(restauranteEntity), "Usuario", List.of(), LocalDateTime.now());

        when(reservaRepository.findById(anyLong())).thenReturn(Optional.of(reservaMock));

        assertDoesNotThrow(() -> reservaRepositorioJpa.atualizarQtdPessoasReserva(reservaEntity));

        verify(reservaRepository).save(any(Reserva.class));
    }

    @Test
    void deveLancarExcecaoQuandoReservaNaoEncontradaNaHoraDeTentarAtualizar() {
        List<MesaEntity> mesasLivres = List.of(new MesaEntity(1, StatusMesa.LIVRE));
        RestauranteEntity restauranteEntity = new RestauranteEntity("Restaurante", enderecoEntityMock,
                "tipoCozinha", LocalTime.now(), LocalTime.now(), 10, mesasLivres);

        ReservaEntity reservaEntity = new ReservaEntity(restauranteEntity, "Usuario", List.of(), LocalDateTime.now());

        when(reservaRepository.findById(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                reservaRepositorioJpa.atualizarQtdPessoasReserva(reservaEntity));

        assertEquals("Reserva não encontrada", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoReservaNaoEncontrada() {
        when(reservaRepository.findById(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> reservaRepositorioJpa.buscaReservaPeloId(1L));

        assertEquals("Reserva não encontrada", exception.getMessage());
    }
}
