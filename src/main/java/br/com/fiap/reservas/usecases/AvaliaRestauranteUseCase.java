package br.com.fiap.reservas.usecases;

import br.com.fiap.reservas.entities.AvaliacaoEntity;

public class AvaliaRestauranteUseCase {

    public static AvaliacaoEntity avaliarRestaurante(int nota, String comentario, Long usuarioId, Long restauranteId) {
        return new AvaliacaoEntity(nota, comentario, usuarioId, restauranteId);
    }

}
