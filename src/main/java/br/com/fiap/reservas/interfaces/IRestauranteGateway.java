package br.com.fiap.reservas.interfaces;

import br.com.fiap.reservas.entities.RestauranteEntity;

public interface IRestauranteGateway {

    public RestauranteEntity buscarRestaurantePorNomeELocalizacaoETipo(String nome, String endereco, String tipo);

}
