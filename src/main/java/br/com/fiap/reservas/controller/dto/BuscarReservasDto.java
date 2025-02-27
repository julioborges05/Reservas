package br.com.fiap.reservas.controller.dto;

import br.com.fiap.reservas.entities.MesaEntity;
import br.com.fiap.reservas.entities.ReservaEntity;
import br.com.fiap.reservas.infra.repository.reserva.ReservaVMesa;

import java.util.List;

public record BuscarReservasDto(Long restauranteId, String usuario, List<ReservaVMesa> mesasReservadas) {

    public BuscarReservasDto(ReservaEntity reservaEntity) {
        this(
                reservaEntity.getRestaurante().getId(),
                reservaEntity.getNomeUsuario(),
                reservaEntity.getMesaList()
        );
    }
}
