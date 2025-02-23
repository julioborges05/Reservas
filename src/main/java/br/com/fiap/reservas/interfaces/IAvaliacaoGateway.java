package br.com.fiap.reservas.interfaces;

import br.com.fiap.reservas.controller.dto.AvaliacaoDto;
import br.com.fiap.reservas.entities.AvaliacaoEntity;

public interface IAvaliacaoGateway {

    AvaliacaoEntity avaliarRestaurante(AvaliacaoDto avaliacaoDto);

    AvaliacaoEntity buscarAvaliacaoPorRestaurante(Long id);

}
