package br.com.fiap.reservas.controller.dto;

import br.com.fiap.reservas.entities.RestauranteEntity;

public record RestauranteDto(String nome, String tipo) {

    public RestauranteDto(RestauranteEntity restauranteEntity) {
        this(restauranteEntity.getNome(), restauranteEntity.getTipoCozinha());
    }

}
