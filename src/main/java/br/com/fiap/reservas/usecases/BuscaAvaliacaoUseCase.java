package br.com.fiap.reservas.usecases;

import br.com.fiap.reservas.entities.RestauranteEntity;

public class BuscaAvaliacaoUseCase {
    public static RestauranteEntity buscarAvaliacaoPorRestaurante(RestauranteEntity restaurante) {
        return new RestauranteEntity(restaurante.getNome(), restaurante.getEndereco(), restaurante.getTipoCozinha(), restaurante.getHorarioAbertura(), restaurante.getHorarioFechamento(), restaurante.getCapacidade(), restaurante.getListaMesa());
    }

}
