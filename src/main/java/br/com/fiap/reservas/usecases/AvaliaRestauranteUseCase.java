package br.com.fiap.reservas.usecases;

import br.com.fiap.reservas.entities.AvaliacaoEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.entities.UsuarioEntity;

public class AvaliaRestauranteUseCase {

    public static AvaliacaoEntity avaliarRestaurante(int nota, String comentario, UsuarioEntity usuario, RestauranteEntity restaurante) {

        return new AvaliacaoEntity(nota, comentario, usuario, restaurante);
    }
}
