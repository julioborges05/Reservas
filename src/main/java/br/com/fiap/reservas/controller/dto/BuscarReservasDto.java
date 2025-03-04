package br.com.fiap.reservas.controller.dto;

import br.com.fiap.reservas.entities.ReservaEntity;
import br.com.fiap.reservas.infra.repository.reserva.ReservaVMesa;

import java.time.LocalDateTime;
import java.util.List;

public record BuscarReservasDto(Long restauranteId, String nomeUsuario, List<ReservaVMesa> mesasReservadas,
                                LocalDateTime horaChegada) {

    public BuscarReservasDto(ReservaEntity reservaEntity) {
        this(
                reservaEntity.getRestaurante().getId(),
                reservaEntity.getNomeUsuario(),
                reservaEntity.getReservaVMesaList(),
                reservaEntity.getHoraChegada()
        );
    }
}
