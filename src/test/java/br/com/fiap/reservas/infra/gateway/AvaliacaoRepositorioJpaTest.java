package br.com.fiap.reservas.infra.gateway;

import br.com.fiap.reservas.entities.*;
import br.com.fiap.reservas.infra.repository.avaliacao.Avaliacao;
import br.com.fiap.reservas.infra.repository.avaliacao.AvaliacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AvaliacaoRepositorioJpaTest {

    private AvaliacaoRepositorioJpa avaliacaoRepositorioJpa;

    @Mock
    private AvaliacaoRepository avaliacaoRepository;

    @Mock
    private RestauranteRepositorioJpa restauranteRepositorioJpa;

    @Mock
    private UsuarioRepositorioJpa usuarioRepositorioJpa;

    @Mock
    private EnderecoRepositorioJpa enderecoRepositorioJpa;

    private final EnderecoEntity enderecoEntityMock = new EnderecoEntity("13181701", "logradouro",
            "bairro", "cidade", "numero", "complemento");

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        avaliacaoRepositorioJpa = new AvaliacaoRepositorioJpa(avaliacaoRepository, restauranteRepositorioJpa, usuarioRepositorioJpa);
    }

    @Test
    void deveAvaliarRestaurante() {

        RestauranteEntity restauranteEntity = new RestauranteEntity(1L, "restaurante", enderecoEntityMock, "tipo",
                LocalTime.now(), LocalTime.now(), 50, new ArrayList<>());
        UsuarioEntity usuarioEntity = new UsuarioEntity(1L, "nome", "email", "senha");
        AvaliacaoEntity avaliacaoEntity = new AvaliacaoEntity(5, "Ótimo restaurante", usuarioEntity, restauranteEntity);
        Avaliacao avaliacaoMock = new Avaliacao(avaliacaoEntity);

        when(avaliacaoRepository.save(any(Avaliacao.class))).thenReturn(avaliacaoMock);

        AvaliacaoEntity resultado = avaliacaoRepositorioJpa.avaliarRestaurante(avaliacaoEntity);

        assertNotNull(resultado);
        assertEquals(5, resultado.getNota());
        assertEquals("Ótimo restaurante", resultado.getComentario());
        verify(avaliacaoRepository).save(any(Avaliacao.class));
    }

    @Test
    void deveBuscarAvaliacaoPorRestaurante() {
        Avaliacao avaliacaoMock = new Avaliacao(1L, 5, "Ótimo restaurante", 1L);
        RestauranteEntity restauranteEntity = new RestauranteEntity(1L, "restaurante", enderecoEntityMock, "tipo",
                LocalTime.now(), LocalTime.now(), 50, new ArrayList<>());
        UsuarioEntity usuarioEntity = new UsuarioEntity(1L, "nome", "email", "senha");

        when(avaliacaoRepository.findAvaliacaoByRestaurante(anyLong())).thenReturn(avaliacaoMock);
        when(restauranteRepositorioJpa.buscarRestaurantePorId(anyLong())).thenReturn(restauranteEntity);
        when(usuarioRepositorioJpa.buscarUsuarioPorId(anyLong())).thenReturn(usuarioEntity);

        AvaliacaoEntity resultado = avaliacaoRepositorioJpa.buscarAvaliacaoPorRestaurante(1L);

        assertNotNull(resultado);
        assertEquals(5, resultado.getNota());
        assertEquals("Ótimo restaurante", resultado.getComentario());
        assertEquals(restauranteEntity, resultado.getRestaurante());
        assertEquals(usuarioEntity, resultado.getUsuario());
        verify(avaliacaoRepository).findAvaliacaoByRestaurante(anyLong());
    }

    @Test
    void deveLancarExcecaoQuandoAvaliacaoNaoEncontrada() {
        when(avaliacaoRepository.findAvaliacaoByRestaurante(anyLong())).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> avaliacaoRepositorioJpa.buscarAvaliacaoPorRestaurante(1L));

        assertEquals("Avaliação não encontrada", exception.getMessage());
    }
}