package br.com.fiap.reservas.controller.dto;

import br.com.fiap.reservas.entities.RestauranteEntity;

import java.time.LocalTime;

public record RestauranteDto(String nome, EnderecoDto endereco , String tipoCozinha, LocalTime horarioAbertura,
                             LocalTime horarioFechamento, int capacidade) {

    public RestauranteDto(RestauranteEntity restauranteEntity) {
        this(
                restauranteEntity.getNome(),
                new EnderecoDto(restauranteEntity.getEndereco()),
                restauranteEntity.getTipoCozinha(),
                restauranteEntity.getHorarioAbertura(),
                restauranteEntity.getHorarioFechamento(),
                restauranteEntity.getCapacidade()
        );
    }
}