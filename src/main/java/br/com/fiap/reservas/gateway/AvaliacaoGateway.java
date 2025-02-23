package br.com.fiap.reservas.gateway;

import br.com.fiap.reservas.controller.dto.AvaliacaoDto;
import br.com.fiap.reservas.entities.AvaliacaoEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.entities.UsuarioEntity;
import br.com.fiap.reservas.interfaces.IAvaliacaoGateway;

public class AvaliacaoGateway implements IAvaliacaoGateway {

    private final IAvaliacaoGateway avaliacaoDatabaseGateway;

    public AvaliacaoGateway(IAvaliacaoGateway avaliacaoDatabaseGateway){
        this.avaliacaoDatabaseGateway = avaliacaoDatabaseGateway;
    }

    @Override
    public AvaliacaoEntity avaliarRestaurante(AvaliacaoDto avaliacaoDto) {
        return avaliacaoDatabaseGateway.avaliarRestaurante(avaliacaoDto);
    }

    @Override
    public AvaliacaoEntity buscarAvaliacaoPorRestaurante(Long id) {
        return avaliacaoDatabaseGateway.buscarAvaliacaoPorRestaurante(id);
    }
}
