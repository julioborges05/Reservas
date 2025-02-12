package br.com.fiap.reservas.interfaces;

import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;

public interface IRestauranteGateway {

    public RestauranteEntity buscarRestaurantePorNomeELocalizacaoETipoCozinha(String nome, EnderecoEntity endereco, String tipoCozinha);

}
