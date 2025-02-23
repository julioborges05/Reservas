package br.com.fiap.reservas.usecases;

import br.com.fiap.reservas.entities.MesaEntity;
import br.com.fiap.reservas.entities.ReservaEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.entities.UsuarioEntity;
import br.com.fiap.reservas.enums.StatusMesa;

public class ReservaMesaUseCase {

    public ReservaEntity registrarReserva(RestauranteEntity restaurante, UsuarioEntity usuario, int qtdPessoas) {
        if (restaurante.getCapacidade() < qtdPessoas) {
            throw new RuntimeException("Capacidade insuficiente");
        }

        restaurante.realizaReserva(qtdPessoas);
        return new ReservaEntity(restaurante, "Ana", new MesaEntity(1, StatusMesa.RESERVADA));
    }
}
