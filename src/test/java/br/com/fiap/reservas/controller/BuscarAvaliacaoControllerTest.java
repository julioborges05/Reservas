package br.com.fiap.reservas.controller;

import br.com.fiap.reservas.controller.dto.AvaliacaoDto;
import br.com.fiap.reservas.entities.AvaliacaoEntity;
import br.com.fiap.reservas.interfaces.IAvaliacaoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class BuscarAvaliacaoControllerTest {

    @Mock
    private IAvaliacaoGateway avaliacaoGateway;

    private BuscarAvaliacaoController controller;

    @BeforeEach
    void setUp() {
       try(AutoCloseable ignored = MockitoAnnotations.openMocks(this)) {
           controller = new BuscarAvaliacaoController(avaliacaoGateway);
       } catch (Exception e) {
           throw new RuntimeException(e.getMessage());
       }
    }

    @Test
    public void validaBuscarAlvaliacaoComRestauranteNaoEncontrado() throws Exception {
        when(avaliacaoGateway.buscarAvaliacaoPorRestaurante(anyLong())).thenThrow(new RuntimeException("Restaurante não encontrado"));

        assertThrows(RuntimeException.class,
                () -> controller.buscarAvaliacaoPorRestaurante(1L),"Restaurante não encontrado");
    }

    @Test
    public void validaBuscarAvaliacaoComSucesso() throws Exception {
        AvaliacaoEntity avaliacao = new AvaliacaoEntity(5, "Ótimo restaurante", null, null);
        when(avaliacaoGateway.buscarAvaliacaoPorRestaurante(1L)).thenReturn(avaliacao);

        AvaliacaoDto result = controller.buscarAvaliacaoPorRestaurante(1L);

        assertNotNull(result);
        assertEquals(5, result.nota());
        assertEquals("Ótimo restaurante", result.comentario());
    }
}
