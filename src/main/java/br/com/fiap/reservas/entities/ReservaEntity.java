package br.com.fiap.reservas.entities;

import br.com.fiap.reservas.infra.repository.reserva.ReservaVMesa;

import java.time.LocalDateTime;
import java.util.List;

public class ReservaEntity {

    private Long id;
    private final RestauranteEntity restaurante;
    private final String nomeUsuario;
    private int qtdPessoas;
    private List<ReservaVMesaEntity> reservaVMesaList;

    private LocalDateTime horaChegada;

    public ReservaEntity(RestauranteEntity restaurante, String nomeUsuario, List<ReservaVMesaEntity> reservaVMesaList,
                         LocalDateTime horaChegada) {
        if (restaurante == null) {
            throw new IllegalArgumentException("Restaurante deve ser informado");
        }
        if (nomeUsuario == null) {
            throw new IllegalArgumentException("Nome do usuário deve ser informado");
        }

        this.restaurante = restaurante;
        this.nomeUsuario = nomeUsuario;
        this.reservaVMesaList = reservaVMesaList;
        this.horaChegada = horaChegada;
    }

    public ReservaEntity(Long id, RestauranteEntity restaurante, String nomeUsuario, List<ReservaVMesaEntity> reservaVMesaList,
                         LocalDateTime horaChegada) {
        if (restaurante == null) {
            throw new IllegalArgumentException("Restaurante deve ser informado");
        }
        if (nomeUsuario == null) {
            throw new IllegalArgumentException("Nome do usuário deve ser informado");
        }
        if (id == null) {
            throw new IllegalArgumentException("Id deve ser informado");
        }

        this.id = id;
        this.restaurante = restaurante;
        this.nomeUsuario = nomeUsuario;
        this.reservaVMesaList = reservaVMesaList;
        this.horaChegada = horaChegada;
    }

    public ReservaEntity(Long id, RestauranteEntity restaurante, String nomeUsuario, List<ReservaVMesaEntity> reservaVMesaList) {
        if (restaurante == null) {
            throw new IllegalArgumentException("Restaurante deve ser informado");
        }
        if (nomeUsuario == null) {
            throw new IllegalArgumentException("Nome do usuário deve ser informado");
        }
        if (id == null) {
            throw new IllegalArgumentException("Id deve ser informado");
        }

        this.id = id;
        this.restaurante = restaurante;
        this.nomeUsuario = nomeUsuario;
        this.reservaVMesaList = reservaVMesaList;
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

    public List<ReservaVMesaEntity> getReservaVMesaList() {
        return reservaVMesaList;
    }

    public LocalDateTime getHoraChegada() {
        return horaChegada;
    }

    public void setHoraChegada(LocalDateTime horaChegada) {
        this.horaChegada = horaChegada;
    }
}
