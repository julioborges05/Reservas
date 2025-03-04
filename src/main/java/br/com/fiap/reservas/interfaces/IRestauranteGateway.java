package br.com.fiap.reservas.interfaces;

import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.infra.repository.restaurante.Restaurante;

public interface IRestauranteGateway {

    RestauranteEntity buscarRestaurantePorNome(String nome);

    RestauranteEntity buscarRestaurantePorLocalizacao(String localizacao);

    RestauranteEntity buscarRestaurantePorTipoCozinha(String tipoCozinha);

    RestauranteEntity buscarRestaurantePorNomeLocalizacaoETipoCozinha(String nome, String localizacao, String tipoCozinha);

    RestauranteEntity cadastrarRestaurante(RestauranteEntity restauranteEntity);

    RestauranteEntity findById(Long id) throws Exception;
}