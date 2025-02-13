package br.com.fiap.reservas.usecases;

import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class BuscaRestauranteUseCase {

    public RestauranteEntity buscarRestaurantePorNomeELocalizacaoETipo(String nome, EnderecoEntity enderecoEntity, String tipo) {
        return new RestauranteEntity(nome, enderecoEntity, tipo, LocalDateTime.now(), LocalDateTime.now(), 10, new ArrayList<>());
    }

}
