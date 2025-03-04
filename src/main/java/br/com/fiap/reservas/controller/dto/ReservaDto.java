package br.com.fiap.reservas.controller.dto;

import br.com.fiap.reservas.entities.ReservaEntity;
import br.com.fiap.reservas.utils.DateFormat;

public record ReservaDto(Long restauranteId, String usuario, int qtdPessoas, Long reservaId, String horaChegada) {

    public ReservaDto(ReservaEntity reservaEntity) {
        this(
                reservaEntity.getRestaurante().getId(),
                reservaEntity.getNomeUsuario(),
                reservaEntity.getQtdPessoas(),
                reservaEntity.getId(),
                DateFormat.convertFromLocalDateTimeToString(reservaEntity.getHoraChegada())
        );
    }
}
