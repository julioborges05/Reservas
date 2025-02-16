package br.com.fiap.reservas.gateway;

import br.com.fiap.reservas.entities.AvaliacaoEntity;
import br.com.fiap.reservas.interfaces.IAvaliacaoGateway;

public class AvaliacaoGateway implements IAvaliacaoGateway {

    private final IAvaliacaoGateway avaliacaoDatabaseGateway;

    public AvaliacaoGateway(IAvaliacaoGateway avaliacaoDatabaseGateway){
        this.avaliacaoDatabaseGateway = avaliacaoDatabaseGateway;
    }

    @Override
    public AvaliacaoEntity avaliarRestaurante(long idRestaurante, int nota, String comentario) {
        return avaliacaoDatabaseGateway.avaliarRestaurante(idRestaurante, nota, comentario);
    }
}
