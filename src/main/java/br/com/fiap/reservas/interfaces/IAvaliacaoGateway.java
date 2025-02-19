package br.com.fiap.reservas.interfaces;

import br.com.fiap.reservas.entities.AvaliacaoEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.entities.UsuarioEntity;

public interface IAvaliacaoGateway {

    AvaliacaoEntity avaliarRestaurante(RestauranteEntity restaurante, int nota, String comentario, UsuarioEntity usuario);

    AvaliacaoEntity buscarAvaliacaoPorRestaurante(String nome);

}
