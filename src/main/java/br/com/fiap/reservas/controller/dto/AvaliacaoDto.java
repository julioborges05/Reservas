package br.com.fiap.reservas.controller.dto;

import br.com.fiap.reservas.entities.AvaliacaoEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;

public record AvaliacaoDto (RestauranteEntity restaurante, int nota, String comentario) {

    public AvaliacaoDto(AvaliacaoEntity avaliacaoEntity) {
        this(
                avaliacaoEntity.getRestaurante(),
                avaliacaoEntity.getNota(),
                avaliacaoEntity.getComentario()
        );
    }

}
