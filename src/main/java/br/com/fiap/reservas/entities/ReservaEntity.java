package br.com.fiap.reservas.entities;

import br.com.fiap.reservas.infra.repository.reserva.ReservaVMesa;

import java.time.LocalDateTime;
import java.util.List;

public class ReservaEntity {

    private Long id;
    private final RestauranteEntity restaurante;
    private final String nomeUsuario;
    private int qtdPessoas;
    private List<ReservaVMesa> mesaList;

    private LocalDateTime horaChegada;

    public ReservaEntity(RestauranteEntity restaurante, String nomeUsuario, List<ReservaVMesa> mesaList) {
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

    public Long getId() {
        return id;
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

    public List<ReservaVMesa> getMesaList() {
        return mesaList;
    }

    public LocalDateTime getHoraChegada() {
        return horaChegada;
    }

    public void setHoraChegada(LocalDateTime horaChegada) {
        this.horaChegada = horaChegada;
    }
}
