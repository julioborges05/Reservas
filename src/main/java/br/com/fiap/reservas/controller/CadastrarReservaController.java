package br.com.fiap.reservas.controller;

import br.com.fiap.reservas.controller.dto.ReservaDto;
import br.com.fiap.reservas.entities.MesaEntity;
import br.com.fiap.reservas.entities.ReservaEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.infra.repository.mesa.Mesa;
import br.com.fiap.reservas.interfaces.IMesaGateway;
import br.com.fiap.reservas.interfaces.IReservaGateway;
import br.com.fiap.reservas.interfaces.IRestauranteGateway;
import br.com.fiap.reservas.usecases.CadastrarReservaUseCase;

import java.util.ArrayList;
import java.util.List;

public class CadastrarReservaController {

    private final IReservaGateway reservaGateway;
    private final IRestauranteGateway restauranteGateway;
    private final IMesaGateway mesasGateway;

    public CadastrarReservaController(
            IReservaGateway reservaGateway, IRestauranteGateway restauranteGateway, IMesaGateway mesasGateway
    ) {
        this.reservaGateway = reservaGateway;
        this.restauranteGateway = restauranteGateway;
        this.mesasGateway = mesasGateway;
    }

    public ReservaDto cadastrarReserva(ReservaDto reservaDto) throws Exception {
        RestauranteEntity restauranteEntity = restauranteGateway.findById(reservaDto.restauranteId());
        restauranteEntity.setId(reservaDto.restauranteId());
        List<MesaEntity> mesasLivres = mesasGateway.buscarMesasLivresPorRestaurante(reservaDto.restauranteId());

        ReservaEntity reservaEntity = CadastrarReservaUseCase.cadastrarReserva(
                restauranteEntity, reservaDto.usuario(), reservaDto.qtdPessoas(), mesasLivres);

        List<MesaEntity> mesasParaReservar = reservaEntity.getMesaList();
        mesasParaReservar.forEach(mesaEntity -> {
            mesasGateway.atualizarReservaMesa(new Mesa(mesaEntity.getId(), mesaEntity.getStatusMesa()));
            reservaGateway.cadastrarReserva(restauranteEntity, reservaEntity.getNomeUsuario(), mesaEntity);
        });

        return reservaDto;
    }

}
