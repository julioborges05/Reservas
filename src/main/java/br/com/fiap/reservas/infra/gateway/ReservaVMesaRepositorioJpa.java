package br.com.fiap.reservas.infra.gateway;

import br.com.fiap.reservas.infra.repository.reserva.ReservaVMesa;
import br.com.fiap.reservas.infra.repository.reserva.ReservaVMesaRepository;
import br.com.fiap.reservas.interfaces.IReservaVMesaGateway;

public class ReservaVMesaRepositorioJpa implements IReservaVMesaGateway {

    private final ReservaVMesaRepository reservaVMesaRepository;

    public ReservaVMesaRepositorioJpa(ReservaVMesaRepository reservaVMesaRepository) {
        this.reservaVMesaRepository = reservaVMesaRepository;
    }

    @Override
    public ReservaVMesa cadastrarReservaVMesa(ReservaVMesa reservaVMesa) {
        return reservaVMesaRepository.save(reservaVMesa);
    }
}
