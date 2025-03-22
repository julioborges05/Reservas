package br.com.fiap.reservas.infra.gateway;

import br.com.fiap.reservas.entities.ReservaVMesaEntity;
import br.com.fiap.reservas.enums.StatusReserva;
import br.com.fiap.reservas.infra.repository.mesa.MesaPK;
import br.com.fiap.reservas.infra.repository.reserva.ReservaVMesa;
import br.com.fiap.reservas.infra.repository.reserva.ReservaVMesaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ReservaVMesaRepositorioJpaTest {

    private ReservaVMesaRepositorioJpa reservaVMesaRepositorioJpa;

    @Mock
    private ReservaVMesaRepository reservaVMesaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reservaVMesaRepositorioJpa = new ReservaVMesaRepositorioJpa(reservaVMesaRepository);
    }

    @Test
    void deveCadastrarReservaVMesa() {
        MesaPK mesaPK = new MesaPK(1L, 1);

        ReservaVMesaEntity reservaEntity = new ReservaVMesaEntity(1L, 123L, mesaPK, StatusReserva.RESERVADA);

        ReservaVMesa reservaMock = new ReservaVMesa(1L, 123L, mesaPK, StatusReserva.RESERVADA);
        when(reservaVMesaRepository.save(any(ReservaVMesa.class))).thenReturn(reservaMock);
        ReservaVMesaEntity resultado = reservaVMesaRepositorioJpa.cadastrarReservaVMesa(reservaEntity);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals(123L, resultado.getIdReserva());
        assertEquals(mesaPK, resultado.getIdMesa());
        assertEquals(StatusReserva.RESERVADA, resultado.getStatus());

        verify(reservaVMesaRepository, times(1)).save(any(ReservaVMesa.class));
    }
}