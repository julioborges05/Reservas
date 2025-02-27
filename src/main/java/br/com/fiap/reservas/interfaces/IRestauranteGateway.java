package br.com.fiap.reservas.interfaces;

import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.infra.repository.restaurante.Restaurante;

import java.util.Optional;

public interface IRestauranteGateway {

    RestauranteEntity buscarRestaurantePorNomeELocalizacaoETipo(String nome, String endereco, String tipo);

    RestauranteEntity cadastrarRestaurante(RestauranteEntity restauranteEntity);

    RestauranteEntity findById(Long id) throws Exception;

}
