package br.com.fiap.reservas.usecases;

import br.com.fiap.reservas.entities.AvaliacaoEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.entities.UsuarioEntity;

public class AvaliaRestauranteUseCase {

    public static AvaliacaoEntity avaliarRestaurante(int nota, String comentario, UsuarioEntity usuario, RestauranteEntity restaurante) {
        if (nota < 0 || nota > 5) {
            throw new IllegalArgumentException("Nota inválida");
        }
        if (comentario == null || comentario.isEmpty()) {
            throw new IllegalArgumentException("Comentário inválido");
        }
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário inválido");
        }
        if (restaurante == null) {
            throw new IllegalArgumentException("Restaurante inválido");
        }

        return new AvaliacaoEntity(nota, comentario, usuario, restaurante);
    }
}
