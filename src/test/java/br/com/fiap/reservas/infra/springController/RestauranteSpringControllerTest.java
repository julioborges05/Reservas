package br.com.fiap.reservas.infra.springController;

import br.com.fiap.reservas.controller.BuscaRestauranteController;
import br.com.fiap.reservas.controller.CadastrarRestauranteController;
import br.com.fiap.reservas.controller.dto.RestauranteDto;
import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RestauranteSpringControllerTest {

    private RestauranteSpringController restauranteSpringController;

    @Mock
    private CadastrarRestauranteController cadastrarRestauranteController;

    @Mock
    private BuscaRestauranteController buscaRestauranteController;

    private MockMvc mockMvc;
    private AutoCloseable mock;

    private final LocalTime horarioAbertura = LocalTime.of(10, 0);
    private final LocalTime horarioFechamento = LocalTime.of(22, 0);
    private final EnderecoEntity enderecoEntity = new EnderecoEntity("cep", "rua", "bairro", "cidade", "numero", "complemento");
    private final RestauranteEntity restauranteEntity = new RestauranteEntity("nome", enderecoEntity, "tipoCozinha",
            horarioAbertura, horarioFechamento, 0);

    @BeforeEach
    public void setup() {
        mock = MockitoAnnotations.openMocks(this);
        restauranteSpringController = new RestauranteSpringController(buscaRestauranteController, cadastrarRestauranteController);

        mockMvc = MockMvcBuilders
                .standaloneSetup(restauranteSpringController)
                .build();
    }

    @AfterEach
    public void close() throws Exception {
        mock.close();
    }

    @Test
    public void cadastrarRestaurante() throws Exception {
        RestauranteDto restaurante = new RestauranteDto(restauranteEntity);

        mockMvc.perform(post("/restaurante")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonFormatUtil.asJsonString(restaurante))
        ).andExpect(status().isOk());

        verify(cadastrarRestauranteController, times(1)).cadastrarRestaurante(restaurante);
    }

    @Test
    public void buscarRestaurantePorNome() throws Exception {
        String nome = "nome";
        RestauranteDto restaurante = new RestauranteDto(restauranteEntity);

        when(buscaRestauranteController.buscarRestaurantePorNome(nome)).thenReturn(restaurante);

        mockMvc.perform(
                get("/restaurante/buscar-nome")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("nome", nome)
                )
                .andExpect(status().isOk());

        verify(buscaRestauranteController, times(1)).buscarRestaurantePorNome(nome);
    }

    @Test
    public void buscarRestaurantePorLocalizacao() throws Exception {
        String localizacao = "cidade";
        RestauranteDto restaurante = new RestauranteDto(restauranteEntity);

        when(buscaRestauranteController.buscarRestaurantePorLocalizacao(localizacao)).thenReturn(restaurante);

        mockMvc.perform(
                get("/restaurante/buscar-localizacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("localizacao", localizacao)
                )
                .andExpect(status().isOk());

        verify(buscaRestauranteController, times(1)).buscarRestaurantePorLocalizacao(localizacao);
    }

    @Test
    public void buscarRestaurantePorTipoCozinha() throws Exception {
        String tipoCozinha = "tipoCozinha";
        RestauranteDto restaurante = new RestauranteDto(restauranteEntity);

        when(buscaRestauranteController.buscarRestaurantePorTipoCozinha(tipoCozinha))
                .thenReturn(restaurante);

        mockMvc.perform(
                get("/restaurante/buscar-tipoCozinha")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("tipoCozinha", tipoCozinha)
                )
                .andExpect(status().isOk());

        verify(buscaRestauranteController, times(1)).buscarRestaurantePorTipoCozinha(tipoCozinha);
    }

    @Test
    public void buscarRestaurantePorNomeLocalizacaoETipoCozinha() throws Exception {
        String nome = "nome";
        String localizacao = "cidade";
        String tipoCozinha = "tipoCozinha";
        RestauranteDto restaurante = new RestauranteDto(restauranteEntity);

        when(buscaRestauranteController.buscarRestaurantePorNomeLocalizacaoETipoCozinha(nome, localizacao, tipoCozinha)).thenReturn(restaurante);

        mockMvc.perform(get("/restaurante/buscar-nome-localizacao-tipoCozinha")
                        .param("nome", nome)
                        .param("localizacao", localizacao)
                        .param("tipoCozinha", tipoCozinha)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(buscaRestauranteController, times(1)).buscarRestaurantePorNomeLocalizacaoETipoCozinha(nome, localizacao, tipoCozinha);
    }

}