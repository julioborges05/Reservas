package br.com.fiap.reservas.controller;

import br.com.fiap.reservas.controller.dto.AvaliacaoDto;
import br.com.fiap.reservas.entities.AvaliacaoEntity;
import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.entities.UsuarioEntity;
import br.com.fiap.reservas.infra.gateway.AvaliacaoRespositorioJpa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class BuscarAvaliacaoControllerTest {

    private BuscarAvaliacaoController buscarAvaliacaoController;

    @Mock
    private AvaliacaoRespositorioJpa avaliacaoRespositorioJpa;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        buscarAvaliacaoController = new BuscarAvaliacaoController(avaliacaoRespositorioJpa);
    }

    @Test
    void testBuscarAvaliacaoPorRestaurante() {
        LocalTime horaAbertura = LocalTime.of(18, 30);
        LocalTime horaFechamento = LocalTime.of(23, 30);

        EnderecoEntity endereco = new EnderecoEntity("123", "Rua teste", "bairro", "São Paulo",
                "123", "complemento");
        UsuarioEntity usuario = new UsuarioEntity(1L, "teste", "teste@teste.com", "teste");
        RestauranteEntity restaurante = new RestauranteEntity(2L, "teste", endereco, "teste",
                horaAbertura, horaFechamento, 10, List.of());
        AvaliacaoEntity avaliacao = new AvaliacaoEntity(5, "Muito bom", usuario, restaurante);

        when(avaliacaoRespositorioJpa.buscarAvaliacaoPorRestaurante(anyLong())).thenReturn(avaliacao);

        AvaliacaoDto avaliacaoDto = buscarAvaliacaoController.buscarAvaliacaoPorRestaurante(1L);

        assertEquals(5, avaliacaoDto.nota());
        assertEquals("Muito bom", avaliacaoDto.comentario());
        assertEquals(1L, avaliacaoDto.usuarioId());
        assertEquals(2L, avaliacaoDto.restauranteId());
    }

}
