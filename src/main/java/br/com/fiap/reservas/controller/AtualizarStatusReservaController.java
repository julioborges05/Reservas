package br.com.fiap.reservas.controller;

import br.com.fiap.reservas.interfaces.IReservaGateway;

import java.time.LocalDateTime;

public class AtualizarStatusReservaController {

    private final IReservaGateway reservaGateway;

    public AtualizarStatusReservaController(
            IReservaGateway reservaGateway) {
        this.reservaGateway = reservaGateway;
    }

    public void atualizarStatusReserva(String nomeUsuario, String horaChegada) {
        reservaGateway.atualizarStatusReserva(nomeUsuario, horaChegada);
    }

}
