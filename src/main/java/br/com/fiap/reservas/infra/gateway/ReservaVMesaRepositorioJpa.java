package br.com.fiap.reservas.infra.gateway;

import br.com.fiap.reservas.entities.ReservaVMesaEntity;
import br.com.fiap.reservas.infra.repository.reserva.ReservaVMesa;
import br.com.fiap.reservas.infra.repository.reserva.ReservaVMesaRepository;
import br.com.fiap.reservas.interfaces.IReservaVMesaGateway;

public class ReservaVMesaRepositorioJpa implements IReservaVMesaGateway {

    private final ReservaVMesaRepository reservaVMesaRepository;

    public ReservaVMesaRepositorioJpa(ReservaVMesaRepository reservaVMesaRepository) {
        this.reservaVMesaRepository = reservaVMesaRepository;
    }

    @Override
    public ReservaVMesaEntity cadastrarReservaVMesa(ReservaVMesaEntity reservaVMesaEntity) {

        ReservaVMesa reservaVMesa = new ReservaVMesa(reservaVMesaEntity.getId(), reservaVMesaEntity.getIdReserva(), reservaVMesaEntity.getIdMesa(),reservaVMesaEntity.getStatus());

        ReservaVMesa reservaVMesaSalvo = reservaVMesaRepository.save(reservaVMesa);

        return new ReservaVMesaEntity(reservaVMesaSalvo.getId(), reservaVMesaSalvo.getIdReserva(), reservaVMesaSalvo.getIdMesa(), reservaVMesaSalvo.getStatus());
    }
}
