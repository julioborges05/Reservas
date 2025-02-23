package br.com.fiap.reservas.entities;

import java.util.List;

public class ReservaEntity {
    private final RestauranteEntity restaurante;
    private final String nomeUsuario;
    private int qtdPessoas;
    private List<MesaEntity> mesaList;

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

    public ReservaEntity(RestauranteEntity restaurante, String usuario, int qtdPessoas) {
        if (restaurante == null) {
            throw new IllegalArgumentException("Restaurante deve ser informado");
        }
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário deve ser informado");
        }
        if (qtdPessoas < 1) {
            throw new IllegalArgumentException("Quantidade de pessoas deve ser informado");
        }

        this.restaurante = restaurante;
        this.nomeUsuario = usuario;
        this.qtdPessoas = qtdPessoas;
    }

    public RestauranteEntity getRestaurante() {
        return restaurante;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public int getQtdPessoas() {
        return qtdPessoas;
    }

    public List<MesaEntity> getMesaList() {
        return mesaList;
    }
}
