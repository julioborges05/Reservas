package br.com.fiap.reservas.controller;

import br.com.fiap.reservas.controller.dto.AvaliacaoDto;

import br.com.fiap.reservas.entities.AvaliacaoEntity;
import br.com.fiap.reservas.interfaces.IAvaliacaoGateway;

public class BuscarAvaliacaoController {

    private final IAvaliacaoGateway avaliacaoGateway;

    public BuscarAvaliacaoController(IAvaliacaoGateway avaliacaoGateway) {
        this.avaliacaoGateway = avaliacaoGateway;
    }

    public AvaliacaoDto buscarAvaliacaoPorRestaurante(String nome) {

        return new AvaliacaoDto(avaliacaoGateway.buscarAvaliacaoPorRestaurante(nome));
    }


}
