package br.com.fiap.reservas.controller;

import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.interfaces.IRestauranteGateway;

public class BuscaRestauranteController implements IRestauranteGateway {

    private IRestauranteGateway restauranteGateway;

    public BuscaRestauranteController(IRestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    @Override
    public RestauranteEntity buscarRestaurantePorNomeELocalizacaoETipoCozinha(String nome, EnderecoEntity endereco, String tipoCozinha) {
        return restauranteGateway.buscarRestaurantePorNomeELocalizacaoETipoCozinha(nome, endereco, tipoCozinha);
    }
}
