package br.com.fiap.reservas.controller;

import br.com.fiap.reservas.controller.dto.ReservaDto;
import br.com.fiap.reservas.entities.MesaEntity;
import br.com.fiap.reservas.entities.ReservaEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.infra.repository.reserva.ReservaVMesa;
import br.com.fiap.reservas.interfaces.IMesaGateway;
import br.com.fiap.reservas.interfaces.IReservaGateway;
import br.com.fiap.reservas.interfaces.IReservaVMesaGateway;
import br.com.fiap.reservas.interfaces.IRestauranteGateway;
import br.com.fiap.reservas.usecases.CadastrarReservaUseCase;

import java.util.List;

public class CadastrarReservaController {

    private final IReservaGateway reservaGateway;
    private final IRestauranteGateway restauranteGateway;
    private final IMesaGateway mesasGateway;
    private final IReservaVMesaGateway reservaVMesaGateway;

    public CadastrarReservaController(
            IReservaGateway reservaGateway,
            IRestauranteGateway restauranteGateway,
            IMesaGateway mesasGateway, IReservaVMesaGateway reservaVMesaGateway) {
        this.reservaGateway = reservaGateway;
        this.restauranteGateway = restauranteGateway;
        this.mesasGateway = mesasGateway;
        this.reservaVMesaGateway = reservaVMesaGateway;
    }

    public ReservaDto cadastrarReserva(ReservaDto reservaDto) throws Exception {
        RestauranteEntity restauranteEntity = restauranteGateway.findById(reservaDto.restauranteId());
        restauranteEntity.setId(reservaDto.restauranteId());
        List<MesaEntity> mesasLivres = mesasGateway.buscarMesasLivresPorRestaurante(reservaDto.restauranteId());

        ReservaEntity reservaEntity = CadastrarReservaUseCase.cadastrarReserva(
                restauranteEntity, reservaDto.usuario(), reservaDto.qtdPessoas(), mesasLivres);

        List<ReservaVMesa> mesasParaReservar = reservaEntity.getMesaList();
        mesasParaReservar.forEach(reservaVMesa -> {
            reservaVMesaGateway.cadastrarReservaVMesa(reservaVMesa);
            reservaGateway.cadastrarReserva(restauranteEntity, reservaEntity.getNomeUsuario(), mesasParaReservar);
        });

        return reservaDto;
    }

}
