package br.com.fiap.reservas.usecases;

import br.com.fiap.reservas.entities.MesaEntity;
import br.com.fiap.reservas.entities.ReservaEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.enums.StatusReserva;
import br.com.fiap.reservas.infra.repository.reserva.Reserva;
import br.com.fiap.reservas.infra.repository.reserva.ReservaVMesa;
import br.com.fiap.reservas.infra.repository.restaurante.Restaurante;

import java.util.ArrayList;
import java.util.List;

public class GerenciaReservaUseCase {

    public static ReservaEntity buscarReservaPorRestaurante(RestauranteEntity restauranteEntity, String nomeUsuario,
                                                     List<ReservaVMesa> reservaVMesaList) {
        return new ReservaEntity(restauranteEntity, "", reservaVMesaList);
    }

    public static ReservaEntity atualizarQtdPessoasReserva(ReservaEntity reservaEntity, Integer qtdPessoas,
                                                           List<MesaEntity> mesasLivres, RestauranteEntity restaurante) {
        if (reservaEntity.getRestaurante().getCapacidade() < qtdPessoas) {
            throw new RuntimeException("Capacidade insuficiente");
        }

        int numeroMesas = 1;
        if (qtdPessoas > 4) {
            numeroMesas = (int) (double) (qtdPessoas / 4);
        }

        if (mesasLivres.size() < numeroMesas) {
            throw new RuntimeException("Número de mesas indisponível");
        }

        reservaEntity.getMesaList().clear();

        List<ReservaVMesa> mesasParaReservar = new ArrayList<>();
        for (int i = 0; i < numeroMesas; i++) {
            ReservaVMesa reservaVMesa = new ReservaVMesa(reservaEntity.getId(), StatusReserva.RESERVADA);
            mesasParaReservar.add(reservaVMesa);
        }

        return new ReservaEntity(restaurante, reservaEntity.getNomeUsuario(), mesasParaReservar);
    }
}
