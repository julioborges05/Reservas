package br.com.fiap.reservas.controller;

import br.com.fiap.reservas.interfaces.IRestauranteGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BuscaRestauranteControllerTest {

    @Mock
    private IRestauranteGateway restauranteGateway;

    private BuscaRestauranteController buscaRestauranteController;

    @BeforeEach
    void setUp() {
        try (AutoCloseable ignored = MockitoAnnotations.openMocks(this)) {
            buscaRestauranteController = new BuscaRestauranteController(restauranteGateway);
        } catch (Exception e) {
            e.printStackTrace();
        }
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