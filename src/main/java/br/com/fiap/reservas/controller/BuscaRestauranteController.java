package br.com.fiap.reservas.controller;

import br.com.fiap.reservas.controller.dto.RestauranteDto;
import br.com.fiap.reservas.interfaces.IRestauranteGateway;

public class BuscaRestauranteController {

    private final IRestauranteGateway restauranteGateway;

    public BuscaRestauranteController(IRestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    public RestauranteDto buscarRestaurantePorNome(String nome) {
        return new RestauranteDto(restauranteGateway.buscarRestaurantePorNome(nome));
    }

    public RestauranteDto buscarRestaurantePorLocalizacao(String localizacao) {
        return new RestauranteDto(restauranteGateway.buscarRestaurantePorLocalizacao(localizacao));
    }

    public RestauranteDto buscarRestaurantePorTipoCozinha(String tipoCozinha) {
        return new RestauranteDto(restauranteGateway.buscarRestaurantePorTipoCozinha(tipoCozinha));
    }

    public RestauranteDto buscarRestaurantePorNomeLocalizacaoETipoCozinha(String nome, String localizacao, String tipoCozinha) {
        return new RestauranteDto(restauranteGateway.buscarRestaurantePorNomeLocalizacaoETipoCozinha(nome, localizacao, tipoCozinha));
    }
}