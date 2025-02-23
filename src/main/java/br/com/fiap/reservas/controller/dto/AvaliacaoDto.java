package br.com.fiap.reservas.controller.dto;

import br.com.fiap.reservas.entities.AvaliacaoEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;

public record AvaliacaoDto (Long restauranteId, int nota, String comentario, long usuarioId) {

    public AvaliacaoDto(AvaliacaoEntity avaliacaoEntity) {
        this(
                avaliacaoEntity.getRestaurante(),
                avaliacaoEntity.getNota(),
                avaliacaoEntity.getComentario(),
                avaliacaoEntity.getUsuarioId()
        );
    }

}
