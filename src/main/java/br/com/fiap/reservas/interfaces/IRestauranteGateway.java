package br.com.fiap.reservas.interfaces;

import br.com.fiap.reservas.entities.RestauranteEntity;

public interface IRestauranteGateway {

    RestauranteEntity buscarRestaurantePorNomeELocalizacaoETipo(String nome, String endereco, String tipo);

    RestauranteEntity cadastrarRestaurante(RestauranteEntity restauranteEntity);

}
