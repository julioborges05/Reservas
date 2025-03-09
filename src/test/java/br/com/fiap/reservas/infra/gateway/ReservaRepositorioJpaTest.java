package br.com.fiap.reservas.infra.gateway;

import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.entities.ReservaEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.enums.StatusReserva;
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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReservaRepositorioJpaTest {
    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private EnderecoRepositorioJpa enderecoRepositorioJpa;

    @Mock
    private RestauranteRepositorioJpa restauranteRepositorioJpa;

    @InjectMocks
    private ReservaRepositorioJpa reservaRepositorioJpa;

    private final EnderecoEntity enderecoEntityMock = new EnderecoEntity("13181701", "logradouro", "bairro", "cidade", "numero", "complemento");

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveBuscarReservasPorRestaurante() {
        Long restauranteId = 1L;
        Restaurante restauranteMock = new Restaurante(restauranteId);

        Reserva reservaMock = new Reserva(restauranteMock, "nome", List.of(), LocalDateTime.now());
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
        /*RestauranteEntity restauranteEntity = new RestauranteEntity("restaurante", enderecoEntityMock, "tipo", LocalTime.now(), LocalTime.now(), 50);
        List<ReservaVMesa> reservaVMesas = List.of(new ReservaVMesa(1L, 1L, 1L, StatusReserva.RESERVADA));
        LocalDateTime horaChegada = LocalDateTime.now();

        Reserva reservaMock = new Reserva(new Restaurante(1L), "nome", reservaVMesas, horaChegada);
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reservaMock);

        ReservaEntity resultado = reservaRepositorioJpa.cadastrarReserva(restauranteEntity, "nome", reservaVMesas, horaChegada);

        assertNotNull(resultado);
        assertEquals("nome", resultado.getNomeUsuario());
        verify(reservaRepository).save(any(Reserva.class));*/
    }

    @Test
    void deveLancarExcecaoQuandoReservaNaoEncontradaAtualizarStatus() {
        when(reservaRepository.findByNomeUsuario(anyString(), any())).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> reservaRepositorioJpa.atualizarStatusReserva("nome", "2025-01-01T10:00"));

        assertEquals("Reserva não encontrada", exception.getMessage());
    }

    @Test
    void deveAtualizarQtdPessoasReserva() {
        /*ReservaEntity reservaEntity = new ReservaEntity(new RestauranteEntity(), "Usuario", List.of(), LocalDateTime.now());
        Reserva reservaMock = new Reserva(new Restaurante(1L), "Usuario", List.of(), LocalDateTime.now());

        when(reservaRepository.findById(anyLong())).thenReturn(Optional.of(reservaMock));

        assertDoesNotThrow(() -> reservaRepositorioJpa.atualizarQtdPessoasReserva(reservaEntity));

        verify(reservaRepository).save(any(Reserva.class));*/
    }

    @Test
    void deveLancarExcecaoQuandoReservaNaoEncontrada() {
        when(reservaRepository.findById(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> reservaRepositorioJpa.buscaReservaPeloId(1L));

        assertEquals("Reserva não encontrada", exception.getMessage());
    }
}
