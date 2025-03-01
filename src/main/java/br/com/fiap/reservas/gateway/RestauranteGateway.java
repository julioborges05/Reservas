package br.com.fiap.reservas.gateway;

import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.interfaces.IRestauranteGateway;

public class RestauranteGateway implements IRestauranteGateway {

    private final IRestauranteGateway restauranteDatabaseGateway;

    public RestauranteGateway(IRestauranteGateway restauranteDatabaseGateway) {
        this.restauranteDatabaseGateway = restauranteDatabaseGateway;
    }

    @Override
    public RestauranteEntity buscarRestaurantePorNome(String nome) {
        return restauranteDatabaseGateway.buscarRestaurantePorNome(nome);
    }

    @Override
    public RestauranteEntity buscarRestaurantePorLocalizacao(String localizacao) {
        return restauranteDatabaseGateway.buscarRestaurantePorLocalizacao(localizacao);
    }

    @Override
    public RestauranteEntity buscarRestaurantePorTipoCozinha(String tipoCozinha) {
        return restauranteDatabaseGateway.buscarRestaurantePorTipoCozinha(tipoCozinha);
    }

    @Override
    public RestauranteEntity buscarRestaurantePorNomeLocalizacaoETipoCozinha(String nome, String localizacao, String tipoCozinha) {
        return restauranteDatabaseGateway.buscarRestaurantePorNomeLocalizacaoETipoCozinha(nome, localizacao, tipoCozinha);
    }

    @Override
    public RestauranteEntity cadastrarRestaurante(RestauranteEntity restauranteEntity) {
        return restauranteDatabaseGateway.cadastrarRestaurante(restauranteEntity);
    }

    @Override
    public RestauranteEntity findById(Long id) throws Exception {
        return restauranteDatabaseGateway.findById(id);
    }
}