package br.com.fiap.reservas.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import br.com.fiap.reservas.controller.dto.AvaliacaoDto;
import br.com.fiap.reservas.entities.AvaliacaoEntity;
import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.entities.UsuarioEntity;
import br.com.fiap.reservas.interfaces.IAvaliacaoGateway;
import br.com.fiap.reservas.interfaces.IRestauranteGateway;
import br.com.fiap.reservas.interfaces.IUsuarioGateway;
import br.com.fiap.reservas.usecases.AvaliaRestauranteUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;

public class RealizarAvaliacaoControllerTest {

    @Mock
    private IAvaliacaoGateway avaliacaoGateway;

    @Mock
    private IUsuarioGateway usuarioGateway;

    @Mock
    private IRestauranteGateway restauranteGateway;

    @InjectMocks
    private RealizarAvaliacaoController realizarAvaliacaoController;

    private final LocalTime horarioAbertura = LocalTime.of(10, 0);
    private final LocalTime horarioFechamento = LocalTime.of(22, 0);
    private final UsuarioEntity usuarioEntity = new UsuarioEntity("nome", "email", "senha");
    private final EnderecoEntity enderecoEntity = new EnderecoEntity("cep", "logradouro", "bairro", "cidade", "numero", "complemento");
    private final RestauranteEntity restauranteEntity = new RestauranteEntity("nome", enderecoEntity, "tipoCozinha", horarioAbertura, horarioFechamento, 1);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRealizarAvaliacao() {
        AvaliacaoDto avaliacaoDto = new AvaliacaoDto(1L, 5, "comentario", 1L);
        AvaliacaoEntity avaliacaoEntity = new AvaliacaoEntity(5, "comentario", usuarioEntity, restauranteEntity);

        when(usuarioGateway.buscarUsuarioPorId(avaliacaoDto.usuarioId())).thenReturn(usuarioEntity);
        when(restauranteGateway.buscarRestaurantePorId(avaliacaoDto.restauranteId())).thenReturn(restauranteEntity);
        when(avaliacaoGateway.avaliarRestaurante(any(AvaliacaoEntity.class))).thenReturn(avaliacaoEntity);

        AvaliacaoEntity result = realizarAvaliacaoController.realizarAvaliacao(avaliacaoDto);

        assertNotNull(result);
        verify(usuarioGateway).buscarUsuarioPorId(avaliacaoDto.usuarioId());
        verify(restauranteGateway).buscarRestaurantePorId(avaliacaoDto.restauranteId());
        verify(avaliacaoGateway).avaliarRestaurante(any(AvaliacaoEntity.class));
    }
}