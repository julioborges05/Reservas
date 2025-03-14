package br.com.fiap.reservas.infra.springController;

import br.com.fiap.reservas.controller.BuscaRestauranteController;
import br.com.fiap.reservas.controller.CadastrarRestauranteController;
import br.com.fiap.reservas.controller.dto.RestauranteDto;
import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.interfaces.IEnderecoGateway;
import br.com.fiap.reservas.interfaces.IMesaGateway;
import br.com.fiap.reservas.interfaces.IRestauranteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RestauranteSpringControllerTest {

    @Mock
    private IRestauranteGateway restauranteGateway;
    @Mock
    private IEnderecoGateway enderecoGateway;
    @Mock
    private IMesaGateway mesaGateway;

    private CadastrarRestauranteController cadastrarRestauranteController;
    private BuscaRestauranteController buscaRestauranteController;

    @BeforeEach
    void setUp() {
        try (AutoCloseable ignored = MockitoAnnotations.openMocks(this)) {
            cadastrarRestauranteController = new CadastrarRestauranteController(restauranteGateway, enderecoGateway, mesaGateway);
            buscaRestauranteController = new BuscaRestauranteController(restauranteGateway);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void validaCadastrarRestauranteComErro(){
        fail("Teste não implementado");
    }

    @Test
    void validaCadastrarRestauranteComSucesso(){
        fail("Teste não implementado");
        /*
        EnderecoEntity enderecoEntity = new EnderecoEntity("89670000", "Logradouro", "Bairro", "Cidade", "Numero", "Complemento");
        RestauranteEntity restauranteEntity = new RestauranteEntity("nome", enderecoEntity, "tipoCozinha", LocalTime.of(18, 30), LocalTime.of(23, 30), 10);
        RestauranteDto dto = new RestauranteDto(restauranteEntity);

        when(cadastrarRestauranteController.cadastrarRestaurante(dto)).thenReturn(dto);

        RestauranteDto resultado = cadastrarRestauranteController.cadastrarRestaurante(dto);
        assertNotNull(resultado);
        assertEquals(dto, resultado);
        verify(cadastrarRestauranteController).cadastrarRestaurante(dto);
         */
    }

    @Test
    void validaBuscarRestaurantePorNome(){
        when(restauranteGateway.buscarRestaurantePorNome(anyString()))
                .thenThrow(new RuntimeException("Restaurante não encontrado"));

        assertThrows(RuntimeException.class,
                () -> buscaRestauranteController.buscarRestaurantePorNome("Estação Burger"), "Restaurante não encontrado"
        );

        verify(restauranteGateway).buscarRestaurantePorNome(anyString());
    }

    @Test
    void validaBuscarRestaurantePorLocalizacao(){
        when(restauranteGateway.buscarRestaurantePorLocalizacao(anyString()))
                .thenThrow(new RuntimeException("Restaurante não encontrado"));

        assertThrows(RuntimeException.class,
                () -> buscaRestauranteController.buscarRestaurantePorLocalizacao("Rua Santa Catarina, 1190 - Cidade Jardim, Catanduvas/SC"), "Restaurante não encontrado"
        );

        verify(restauranteGateway).buscarRestaurantePorLocalizacao(anyString());
    }

    @Test
    void validaBuscarRestaurantePorTipoCozinha(){
        when(restauranteGateway.buscarRestaurantePorTipoCozinha(anyString())).
                thenThrow(new RuntimeException("Restaurante não encontrado"));

        assertThrows(RuntimeException.class,
                () -> buscaRestauranteController.buscarRestaurantePorTipoCozinha("Cozinha Americana"), "Restaurante não encontrado"
        );

        verify(restauranteGateway).buscarRestaurantePorTipoCozinha(anyString());
    }

    @Test
    void validaBuscarRestaurantePorNomeLocalizacaoETipoCozinha(){
        when(restauranteGateway.buscarRestaurantePorNomeLocalizacaoETipoCozinha(anyString(), anyString(), anyString()))
                .thenThrow(new RuntimeException("Restaurante não encontrado"));

        assertThrows(RuntimeException.class,
                () -> buscaRestauranteController.buscarRestaurantePorNomeLocalizacaoETipoCozinha("Estação Burger", "Rua Santa Catarina, 1190 - Cidade Jardim, Catanduvas/SC",
                        "Cozinha Americana"), "Restaurante não encontrado"
        );

        verify(restauranteGateway).buscarRestaurantePorNomeLocalizacaoETipoCozinha(anyString(), anyString(), anyString());
    }
}