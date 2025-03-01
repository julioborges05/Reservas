package br.com.fiap.reservas.usecases;

import br.com.fiap.reservas.entities.MesaEntity;
import br.com.fiap.reservas.entities.ReservaEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.enums.StatusReserva;
import br.com.fiap.reservas.infra.repository.mesa.MesaPK;
import br.com.fiap.reservas.infra.repository.reserva.ReservaVMesa;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GerenciaReservaUseCase {

    public static ReservaEntity buscarReservaPorRestaurante(RestauranteEntity restauranteEntity, String nomeUsuario,
                                                            List<ReservaVMesa> reservaVMesaList, LocalDateTime horaChegada) {
        return new ReservaEntity(restauranteEntity, nomeUsuario, reservaVMesaList, horaChegada);
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

        reservaEntity.getReservaVMesaList().clear();

        List<ReservaVMesa> mesasParaReservar = new ArrayList<>();
        mesasLivres.stream()
                .limit(numeroMesas)
                .forEach(mesa -> {
                    ReservaVMesa reservaVMesa = new ReservaVMesa(StatusReserva.RESERVADA);
                    reservaVMesa.setIdReserva(reservaEntity.getId());
                    reservaVMesa.setIdMesa(new MesaPK(mesa.getRestauranteId(), mesa.getNumero()));
                    mesasParaReservar.add(reservaVMesa);
                });

        return new ReservaEntity(reservaEntity.getId(), restaurante, reservaEntity.getNomeUsuario(), mesasParaReservar);
    }
}
