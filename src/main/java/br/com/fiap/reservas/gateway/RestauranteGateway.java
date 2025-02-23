package br.com.fiap.reservas.gateway;

import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.interfaces.IRestauranteGateway;

import java.util.List;

public class RestauranteGateway implements IRestauranteGateway {

    private final IRestauranteGateway restauranteDatabaseGateway;

    public RestauranteGateway(IRestauranteGateway restauranteDatabaseGateway) {
        this.restauranteDatabaseGateway = restauranteDatabaseGateway;
    }

    @Override
    public RestauranteEntity buscarRestaurantePorNomeELocalizacaoETipo(String nome, String endereco, String tipo) {
        return restauranteDatabaseGateway.buscarRestaurantePorNomeELocalizacaoETipo(nome, endereco, tipo);
    }

    @Override
    public RestauranteEntity cadastrarRestaurante(RestauranteEntity restauranteEntity) {
        return restauranteDatabaseGateway.cadastrarRestaurante(restauranteEntity);
    }

    @Override
    public List<RestauranteEntity> buscarRestaurantePorNome(String nome) {
        return restauranteDatabaseGateway.buscarRestaurantePorNome(nome);
    }
}
