package br.com.fiap.reservas.infra.springController;

import br.com.fiap.reservas.controller.BuscarAvaliacaoController;
import br.com.fiap.reservas.controller.RealizarAvaliacaoController;
import br.com.fiap.reservas.controller.dto.AvaliacaoDto;
import br.com.fiap.reservas.entities.AvaliacaoEntity;
import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.entities.UsuarioEntity;
import br.com.fiap.reservas.utils.JsonFormatUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AvaliacaoSpringControllerTest {

    private AvaliacaoSpringController avaliacaoSpringController;

    @Mock
    private RealizarAvaliacaoController realizarAvaliacaoController;

    @Mock
    private BuscarAvaliacaoController buscarAvaliacaoController;

    private MockMvc mockMvc;
    private AutoCloseable mock;

    @BeforeEach
    public void setup() {
        mock = MockitoAnnotations.openMocks(this);
        avaliacaoSpringController = new AvaliacaoSpringController(buscarAvaliacaoController, realizarAvaliacaoController);

        mockMvc = MockMvcBuilders
                .standaloneSetup(avaliacaoSpringController)
                .build();
    }

    @AfterEach
    public void close() throws Exception {
        mock.close();
    }

    @Test
    public void avaliarRestaurante() throws Exception {
        EnderecoEntity enderecoEntity = new EnderecoEntity("cep", "rua", "bairro", "cidade", "numero", "complemento");
        UsuarioEntity usuarioEntity = new UsuarioEntity(1L, "nome", "email", "senha");
        RestauranteEntity restauranteEntity = new RestauranteEntity(1L, "nome", enderecoEntity,
                "tipoCozinha", LocalTime.of(10, 0), LocalTime.of(22, 0),
                0, List.of());

        AvaliacaoDto avaliacaoDto = new AvaliacaoDto(1L, 5, "comentario", 1L);
        AvaliacaoEntity avaliacaoEntity = new AvaliacaoEntity(5, "comentario", usuarioEntity, restauranteEntity);

        when(realizarAvaliacaoController.realizarAvaliacao(avaliacaoDto)).thenReturn(avaliacaoEntity);

        mockMvc.perform(post("/avaliacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonFormatUtil.asJsonString(avaliacaoDto))
        ).andExpect(status().isOk());

        verify(realizarAvaliacaoController, times(1)).realizarAvaliacao(avaliacaoDto);
    }

    @Test
    public void buscarAvaliacaoPorRestaurante() throws Exception {
        Long id = 1L;
        AvaliacaoDto avaliacaoDto = new AvaliacaoDto(1L, 5, "comentario", 1L);

        when(buscarAvaliacaoController.buscarAvaliacaoPorRestaurante(id)).thenReturn(avaliacaoDto);

        mockMvc.perform(get("/avaliacao/avaliacao-por-restaurante/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        verify(buscarAvaliacaoController, times(1)).buscarAvaliacaoPorRestaurante(id);
    }
}