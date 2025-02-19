package br.com.fiap.reservas.usecases;

import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class BuscaRestauranteUseCase {

    public RestauranteEntity buscarRestaurantePorNomeELocalizacaoETipo(String nome, EnderecoEntity enderecoEntity, String tipo) {
        return new RestauranteEntity(nome, enderecoEntity, tipo, LocalTime.now(), LocalTime.now(), 10, new ArrayList<>());
    }

}
