package br.com.fiap.reservas.controller;

import br.com.fiap.reservas.controller.dto.RestauranteDto;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.interfaces.IRestauranteGateway;

public class BuscaRestauranteController {

    private final IRestauranteGateway restauranteGateway;

    public BuscaRestauranteController(IRestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    public RestauranteDto buscarRestaurantePorNomeEnderecoETipo(String nome, String endereco, String tipo) {
        return new RestauranteDto(restauranteGateway.buscarRestaurantePorNomeELocalizacaoETipo(nome, endereco, tipo));
    }

}
