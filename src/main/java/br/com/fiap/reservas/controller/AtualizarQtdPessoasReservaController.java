package br.com.fiap.reservas.controller;

import br.com.fiap.reservas.controller.dto.ReservaDto;
import br.com.fiap.reservas.entities.MesaEntity;
import br.com.fiap.reservas.entities.ReservaEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.interfaces.IMesaGateway;
import br.com.fiap.reservas.interfaces.IReservaGateway;
import br.com.fiap.reservas.interfaces.IRestauranteGateway;
import br.com.fiap.reservas.usecases.GerenciaReservaUseCase;

import java.util.List;

public class AtualizarQtdPessoasReservaController {

    private final IReservaGateway reservaGateway;

    private final IRestauranteGateway restauranteGateway;

    private final IMesaGateway mesasGateway;

    public AtualizarQtdPessoasReservaController(IReservaGateway reservaGateway,
                                                IRestauranteGateway restauranteGateway,
                                                IMesaGateway mesasGateway) {
        this.reservaGateway = reservaGateway;
        this.restauranteGateway = restauranteGateway;
        this.mesasGateway = mesasGateway;
    }

    public void atualizarQtdPessoasReserva(ReservaDto reservaDto) throws Exception {
        ReservaEntity reservaEntity = reservaGateway.findById(reservaDto.reservaId());
        RestauranteEntity restauranteEntity = restauranteGateway.findById(reservaDto.restauranteId());
        List<MesaEntity> mesasLivres = mesasGateway.buscarMesasLivresPorRestaurante(reservaDto.restauranteId());

        ReservaEntity reserva = GerenciaReservaUseCase.atualizarQtdPessoasReserva(reservaEntity, reservaDto.qtdPessoas(),
                mesasLivres, restauranteEntity);

        reservaGateway.atualizarQtdPessoasReserva(reservaEntity);
    }

}
