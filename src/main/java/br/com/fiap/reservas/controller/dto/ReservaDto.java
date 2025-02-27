package br.com.fiap.reservas.controller.dto;

import br.com.fiap.reservas.entities.ReservaEntity;

public record ReservaDto(Long restauranteId, String usuario, int qtdPessoas, Long reservaId) {

    public ReservaDto(ReservaEntity reservaEntity) {
        this(
                reservaEntity.getRestaurante().getId(),
                reservaEntity.getNomeUsuario(),
                reservaEntity.getQtdPessoas(),
                reservaEntity.getId()
        );
    }
}
