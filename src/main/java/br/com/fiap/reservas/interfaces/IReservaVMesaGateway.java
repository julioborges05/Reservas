package br.com.fiap.reservas.interfaces;

import br.com.fiap.reservas.infra.repository.reserva.ReservaVMesa;

public interface IReservaVMesaGateway {

    ReservaVMesa cadastrarReservaVMesa(ReservaVMesa reservaVMesa);

}
