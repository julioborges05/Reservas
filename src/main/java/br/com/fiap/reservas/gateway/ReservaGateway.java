package br.com.fiap.reservas.gateway;

import br.com.fiap.reservas.entities.MesaEntity;
import br.com.fiap.reservas.entities.ReservaEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.interfaces.IReservaGateway;

import java.util.List;

public class ReservaGateway implements IReservaGateway {

    private final IReservaGateway reservaDatabaseGateway;

    public ReservaGateway(IReservaGateway reservaDatabaseGateway) {
        this.reservaDatabaseGateway = reservaDatabaseGateway;
    }

    @Override
    public List<ReservaEntity> buscarReservasPorRestaurante(Long restauranteId) {
        return reservaDatabaseGateway.buscarReservasPorRestaurante(restauranteId);
    }

    public ReservaEntity cadastrarReserva(RestauranteEntity restauranteId, String nomeUsuario, MesaEntity mesaEntityList) {
        return reservaDatabaseGateway.cadastrarReserva(restauranteId, nomeUsuario, mesaEntityList);
    }
}
