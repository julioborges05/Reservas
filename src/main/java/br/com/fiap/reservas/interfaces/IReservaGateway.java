package br.com.fiap.reservas.interfaces;

import br.com.fiap.reservas.entities.ReservaEntity;
import br.com.fiap.reservas.enums.StatusMesa;

import java.util.List;

public interface IReservaGateway {

    public List<ReservaEntity> buscarReservas(StatusMesa statusMesa);

}
