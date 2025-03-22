package br.com.fiap.reservas.interfaces;

import br.com.fiap.reservas.entities.ReservaVMesaEntity;
import br.com.fiap.reservas.infra.repository.reserva.ReservaVMesa;

public interface IReservaVMesaGateway {

    ReservaVMesaEntity cadastrarReservaVMesa(ReservaVMesaEntity reservaVMesa);

}
