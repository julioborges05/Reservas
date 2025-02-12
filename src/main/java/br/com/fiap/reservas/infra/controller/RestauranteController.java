package br.com.fiap.reservas.infra.controller;

import br.com.fiap.reservas.controller.BuscaRestauranteController;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.usecases.BuscaRestauranteUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurante")
public class RestauranteController {

    private final BuscaRestauranteController buscaRestauranteController;

    public RestauranteController(BuscaRestauranteController buscaRestauranteController) {
        this.buscaRestauranteController = buscaRestauranteController;
    }

    @GetMapping
    public RestauranteEntity buscarRestaurante() {
        return buscaRestauranteController.buscarRestaurantePorNomeELocalizacaoETipoCozinha("abc", "abc", "abc");
    }

}
