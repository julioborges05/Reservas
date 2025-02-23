package br.com.fiap.reservas.controller;

import br.com.fiap.reservas.interfaces.IMesaGateway;

public class CadastrarMesaController {

    private final IMesaGateway mesasGateway;

    public CadastrarMesaController(IMesaGateway mesasGateway) {
        this.mesasGateway = mesasGateway;
    }

}
