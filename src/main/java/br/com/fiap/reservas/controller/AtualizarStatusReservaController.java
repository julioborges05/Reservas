package br.com.fiap.reservas.controller;

import br.com.fiap.reservas.interfaces.IReservaGateway;

public class AtualizarStatusReservaController {

    private final IReservaGateway reservaGateway;

    public AtualizarStatusReservaController(
            IReservaGateway reservaGateway) {
        this.reservaGateway = reservaGateway;
    }

    public void atualizarStatusReserva(String nomeUsuario) {
        reservaGateway.atualizarStatusReserva(nomeUsuario);
    }

}
