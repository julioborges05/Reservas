package br.com.fiap.reservas.gateway;

import br.com.fiap.reservas.entities.AvaliacaoEntity;
import br.com.fiap.reservas.interfaces.IAvaliacaoGateway;

public class AvaliacaoGateway implements IAvaliacaoGateway {

    private final IAvaliacaoGateway avaliacaoDatabaseGateway;

    public AvaliacaoGateway(IAvaliacaoGateway avaliacaoDatabaseGateway){
        this.avaliacaoDatabaseGateway = avaliacaoDatabaseGateway;
    }

    @Override
    public AvaliacaoEntity avaliarRestaurante(AvaliacaoEntity avaliacaoEntity) {
        return avaliacaoDatabaseGateway.avaliarRestaurante(avaliacaoEntity);
    }

    @Override
    public AvaliacaoEntity buscarAvaliacaoPorRestaurante(Long id) {
        return avaliacaoDatabaseGateway.buscarAvaliacaoPorRestaurante(id);
    }
}
