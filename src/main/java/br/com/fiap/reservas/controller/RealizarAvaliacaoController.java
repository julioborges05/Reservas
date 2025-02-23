package br.com.fiap.reservas.controller;

import br.com.fiap.reservas.controller.dto.AvaliacaoDto;
import br.com.fiap.reservas.entities.AvaliacaoEntity;
import br.com.fiap.reservas.interfaces.IAvaliacaoGateway;

public class RealizarAvaliacaoController {

    private final IAvaliacaoGateway avaliacaoGateway;

    public RealizarAvaliacaoController(IAvaliacaoGateway avaliacaoGateway) {
        this.avaliacaoGateway = avaliacaoGateway;
    }

    public AvaliacaoEntity realizarAvaliacao(AvaliacaoDto avaliacao) {

        return avaliacaoGateway.avaliarRestaurante(avaliacao);
    }
}
