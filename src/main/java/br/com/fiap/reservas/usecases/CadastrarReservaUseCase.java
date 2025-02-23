package br.com.fiap.reservas.usecases;

import br.com.fiap.reservas.entities.MesaEntity;
import br.com.fiap.reservas.entities.ReservaEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.enums.StatusMesa;

import java.util.List;

public class CadastrarReservaUseCase {

    public static ReservaEntity cadastrarReserva(
            RestauranteEntity restaurante, String usuario, int qtdPessoas, List<MesaEntity> mesasLivres) {

        if (restaurante.getCapacidade() < qtdPessoas) {
            throw new RuntimeException("Capacidade insuficiente");
        }

        int numeroMesas = 1;
        if (qtdPessoas > 4) {
            numeroMesas = (int) (double) (qtdPessoas / 4);
        }
        if (mesasLivres.size() < numeroMesas) {
            throw new RuntimeException("Número de mesas indisponível");
        }

        List<MesaEntity> mesasParaReservar = mesasLivres.subList(0, numeroMesas);
        mesasParaReservar.forEach((mesa) -> {
            mesa.setStatusMesa(StatusMesa.RESERVADA);
        });

        return new ReservaEntity(restaurante, usuario, mesasParaReservar);
    }
}
