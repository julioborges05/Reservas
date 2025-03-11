package br.com.fiap.reservas.controller;

import br.com.fiap.reservas.controller.dto.BuscarReservasDto;
import br.com.fiap.reservas.entities.ReservaEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.interfaces.IReservaGateway;
import br.com.fiap.reservas.interfaces.IRestauranteGateway;
import br.com.fiap.reservas.usecases.GerenciaReservaUseCase;

import java.util.ArrayList;
import java.util.List;

public class BuscarReservasController {

    private final IReservaGateway reservaGateway;
    private final IRestauranteGateway restauranteGateway;

    public BuscarReservasController(IReservaGateway reservaGateway, IRestauranteGateway restauranteGateway) {
        this.reservaGateway = reservaGateway;
        this.restauranteGateway = restauranteGateway;
    }

    public List<BuscarReservasDto> buscarReservasPorRestaurante(Long restauranteId) {
        List<BuscarReservasDto> reservasEncontradas = new ArrayList<>();
        RestauranteEntity restauranteEntity = restauranteGateway.findById(restauranteId);
        restauranteEntity.setId(restauranteId);

        List<ReservaEntity> listaReservas = reservaGateway.buscarReservasPorRestaurante(restauranteId);

        listaReservas.forEach(reserva -> {
            ReservaEntity reservaEntity = GerenciaReservaUseCase.buscarReservaPorRestaurante(
                    restauranteEntity, reserva.getNomeUsuario(), reserva.getReservaVMesaList(), reserva.getHoraChegada());
            reservasEncontradas.add(new BuscarReservasDto(reservaEntity));
        });

        return reservasEncontradas;
    }

}
