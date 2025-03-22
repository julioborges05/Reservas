package br.com.fiap.reservas.controller.dto;

import br.com.fiap.reservas.entities.ReservaEntity;
import br.com.fiap.reservas.infra.repository.mesa.MesaPK;
import br.com.fiap.reservas.infra.repository.reserva.ReservaVMesa;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

public record BuscarReservasDto(Long restauranteId, String nomeUsuario, List<ReservaVMesa> mesasReservadas,
                                LocalDateTime horaChegada) {

    public BuscarReservasDto(ReservaEntity reservaEntity) {
        this(
                reservaEntity.getRestaurante().getId(),
                reservaEntity.getNomeUsuario(),
                reservaEntity.getReservaVMesaList().stream().map(reservaVMesaEntity ->
                        new ReservaVMesa(reservaVMesaEntity.getId(), reservaVMesaEntity.getIdReserva(),
                                new MesaPK(reservaVMesaEntity.getIdMesa().getRestauranteId(), reservaVMesaEntity.getIdMesa().getNumeroMesa()),
                                reservaVMesaEntity.getStatus())).collect(toList()),
                reservaEntity.getHoraChegada()
        );
    }
}
