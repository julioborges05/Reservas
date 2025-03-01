package br.com.fiap.reservas.gateway;

import br.com.fiap.reservas.infra.repository.reserva.ReservaVMesa;
import br.com.fiap.reservas.interfaces.IReservaVMesaGateway;

public class ReservaVMesaGateway implements IReservaVMesaGateway {

    private final IReservaVMesaGateway reservaVMesaGateway;

    public ReservaVMesaGateway(IReservaVMesaGateway reservaVMesaGateway) {
        this.reservaVMesaGateway = reservaVMesaGateway;
    }

    @Override
    public ReservaVMesa cadastrarReservaVMesa(ReservaVMesa reservaVMesa) {
        return reservaVMesaGateway.cadastrarReservaVMesa(reservaVMesa);
    }
}
