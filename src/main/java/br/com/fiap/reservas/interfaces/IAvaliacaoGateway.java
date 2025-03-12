package br.com.fiap.reservas.interfaces;

import br.com.fiap.reservas.entities.AvaliacaoEntity;

public interface IAvaliacaoGateway {

    AvaliacaoEntity avaliarRestaurante(AvaliacaoEntity avaliacaoEntity);

    AvaliacaoEntity buscarAvaliacaoPorRestaurante(Long id);

}
