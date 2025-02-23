package br.com.fiap.reservas.controller;

import br.com.fiap.reservas.controller.dto.AvaliacaoDto;
import br.com.fiap.reservas.interfaces.IAvaliacaoGateway;

public class BuscarAvaliacaoController {

    private final IAvaliacaoGateway avaliacaoGateway;

    public BuscarAvaliacaoController(IAvaliacaoGateway avaliacaoGateway) {
        this.avaliacaoGateway = avaliacaoGateway;
    }

    public AvaliacaoDto buscarAvaliacaoPorRestaurante(Long id) {
        return new AvaliacaoDto(avaliacaoGateway.buscarAvaliacaoPorRestaurante(id));
    }

}
