package br.com.fiap.reservas.infra.gateway;

import br.com.fiap.reservas.entities.MesaEntity;
import br.com.fiap.reservas.enums.StatusMesa;
import br.com.fiap.reservas.infra.repository.mesa.Mesa;
import br.com.fiap.reservas.infra.repository.mesa.MesaPK;
import br.com.fiap.reservas.infra.repository.mesa.MesaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MesaRepositorioJpaTest {

//    private MesaRepositorioJpa mesaRepositorioJpa;
//
//    @Mock
//    private MesaRepository mesaRepository;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mesaRepositorioJpa = new MesaRepositorioJpa(mesaRepository);
//    }
//
//    @Test
//    void deveBuscarMesaPeloId() {
//        MesaPK mesaPK = new MesaPK(1L, 1);
//        Mesa mesaMock = new Mesa(mesaPK, StatusMesa.LIVRE);
//        when(mesaRepository.findById(any(MesaPK.class))).thenReturn(Optional.of(mesaMock));
//
//        MesaEntity resultado = mesaRepositorioJpa.buscarMesasLivresPorRestaurante();
//
//        assertNotNull(resultado);
//        assertEquals(StatusMesa.LIVRE, resultado.getStatusMesa());
//        verify(mesaRepository).findById(any(MesaPK.class));
//    }
//
//    @Test
//    void deveLancarExcecaoQuandoMesaNaoEncontrada() {
//        MesaPK mesaPK = new MesaPK(1L, 1);
//        when(mesaRepository.findById(any(MesaPK.class))).thenReturn(Optional.empty());
//
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> mesaRepositorioJpa.buscarMesaPeloId(mesaPK));
//
//        assertEquals("Mesa não encontrada", exception.getMessage());
//    }
}