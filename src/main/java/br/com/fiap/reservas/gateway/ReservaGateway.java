package br.com.fiap.reservas.gateway;

import br.com.fiap.reservas.entities.ReservaEntity;
import br.com.fiap.reservas.enums.StatusMesa;
import br.com.fiap.reservas.interfaces.IReservaGateway;

import java.util.List;

public class ReservaGateway implements IReservaGateway {

    private final IReservaGateway reservaDatabaseGateway;

    public ReservaGateway(IReservaGateway reservaDatabaseGateway) {
        this.reservaDatabaseGateway = reservaDatabaseGateway;
    }

    @Override
    public List<ReservaEntity> buscarReservas(StatusMesa statusMesa) {
        return reservaDatabaseGateway.buscarReservas(statusMesa);
    }
}
