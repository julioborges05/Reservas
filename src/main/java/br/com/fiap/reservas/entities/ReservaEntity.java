package br.com.fiap.reservas.entities;

import br.com.fiap.reservas.enums.StatusMesa;

import java.util.List;

public class ReservaEntity {
    private final RestauranteEntity restaurante;
    private final String nomeUsuario;
    private final List<MesaEntity> mesaList;

    public ReservaEntity(RestauranteEntity restaurante, String nomeUsuario, List<MesaEntity> mesaList) {
        if (restaurante == null) {
            throw new IllegalArgumentException("Restaurante deve ser informado");
        }
        if (nomeUsuario == null) {
            throw new IllegalArgumentException("Nome do usuário deve ser informado");
        }

        this.restaurante = restaurante;
        this.nomeUsuario = nomeUsuario;
        this.mesaList = mesaList;
    }
}
