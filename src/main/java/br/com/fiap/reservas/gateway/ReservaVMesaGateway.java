package br.com.fiap.reservas.gateway;

import br.com.fiap.reservas.entities.ReservaVMesaEntity;
import br.com.fiap.reservas.infra.repository.reserva.ReservaVMesa;
import br.com.fiap.reservas.interfaces.IReservaVMesaGateway;

public class ReservaVMesaGateway implements IReservaVMesaGateway {

    private final IReservaVMesaGateway reservaVMesaGateway;

    public ReservaVMesaGateway(IReservaVMesaGateway reservaVMesaGateway) {
        this.reservaVMesaGateway = reservaVMesaGateway;
    }

    @Override
    public ReservaVMesaEntity cadastrarReservaVMesa(ReservaVMesaEntity reservaVMesa) {
        return reservaVMesaGateway.cadastrarReservaVMesa(reservaVMesa);
    }
}
