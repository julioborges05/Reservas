package br.com.fiap.reservas.infra.gateway;

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
//
//    private ReservaVMesaRepositorioJpa reservaVMesaRepositorioJpa;
//
//    @Mock
//    private ReservaVMesaRepository reservaVMesaRepository;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        reservaVMesaRepositorioJpa = new ReservaVMesaRepositorioJpa(reservaVMesaRepository);
//    }
//
//    @Test
//    void deveBuscarReservaVMesaPeloId() {
//        ReservaVMesa reservaMock = new ReservaVMesa(1L, "2023-10-10", "12:00", "14:00");
//        when(reservaVMesaRepository.findById(anyLong())).thenReturn(Optional.of(reservaMock));
//
//        ReservaVMesa resultado = reservaVMesaRepositorioJpa.buscarReservaVMesaPeloId(1L);
//
//        assertNotNull(resultado);
//        assertEquals("2023-10-10", resultado.getData());
//        verify(reservaVMesaRepository).findById(anyLong());
//    }
//
//    @Test
//    void deveLancarExcecaoQuandoReservaVMesaNaoEncontrada() {
//        when(reservaVMesaRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> reservaVMesaRepositorioJpa.buscarReservaVMesaPeloId(1L));
//
//        assertEquals("ReservaVMesa não encontrada", exception.getMessage());
//    }
}