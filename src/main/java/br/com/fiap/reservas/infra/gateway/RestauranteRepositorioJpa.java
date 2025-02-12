package br.com.fiap.reservas.infra.gateway;

import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.infra.repository.RestauranteRepository;
import br.com.fiap.reservas.interfaces.IRestauranteGateway;

public class RestauranteRepositorioJpa implements IRestauranteGateway {

    private final RestauranteRepository restauranteRepository;

    public RestauranteRepositorioJpa(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    @Override
    public RestauranteEntity buscarRestaurantePorNomeELocalizacaoETipoCozinha(String nome, EnderecoEntity endereco, String tipoCozinha) {
        return restauranteRepository.buscarRestaurantePorNomeELocalizacaoETipoCozinha(nome, endereco, tipoCozinha);
    }
}
