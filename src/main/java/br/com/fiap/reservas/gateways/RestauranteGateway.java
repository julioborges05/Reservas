package br.com.fiap.reservas.gateways;

import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.interfaces.IRestauranteGateway;

public class RestauranteGateway implements IRestauranteGateway {

    private final IRestauranteGateway databaseClient;

    public RestauranteGateway(IRestauranteGateway databaseClient) {
        this.databaseClient = databaseClient;
    }

    @Override
    public RestauranteEntity buscarRestaurantePorNomeELocalizacaoETipoCozinha(String nome, EnderecoEntity endereco, String tipoCozinha) {
        return this.databaseClient.buscarRestaurantePorNomeELocalizacaoETipoCozinha(nome, endereco, tipoCozinha);
    }
}
