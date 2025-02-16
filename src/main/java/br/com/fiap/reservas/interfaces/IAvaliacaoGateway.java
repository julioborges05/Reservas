package br.com.fiap.reservas.interfaces;

import br.com.fiap.reservas.entities.AvaliacaoEntity;

public interface IAvaliacaoGateway {

    public AvaliacaoEntity avaliarRestaurante(long idRestaurante, int nota, String comentario);

}
