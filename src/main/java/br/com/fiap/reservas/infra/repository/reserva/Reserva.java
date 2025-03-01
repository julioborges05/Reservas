package br.com.fiap.reservas.infra.repository.reserva;

import br.com.fiap.reservas.infra.repository.mesa.Mesa;
import br.com.fiap.reservas.infra.repository.restaurante.Restaurante;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_usuario")
    private String nomeUsuario;

    @ManyToOne
    @JoinColumn(name = "id_restaurante")
    private Restaurante restaurante;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_reserva")
    private List<ReservaVMesa> reservaVMesaList;

    @Column(name = "hora_chegada")
    private LocalDateTime horaChegada;

    public Reserva() {
    }

    public Reserva(Restaurante restaurante, String nomeUsuario, List<ReservaVMesa> reservaVMesaList, LocalDateTime horaChegada) {
        this.nomeUsuario = nomeUsuario;
        this.restaurante = restaurante;
        this.reservaVMesaList = reservaVMesaList;
        this.horaChegada = horaChegada;
    }

    public Reserva(Long id, String nomeUsuario, Restaurante restaurante, List<ReservaVMesa> reservaVMesaList, LocalDateTime horaChegada) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.restaurante = restaurante;
        this.reservaVMesaList = reservaVMesaList;
        this.horaChegada = horaChegada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public List<ReservaVMesa> getReservaVMesaList() {
        return reservaVMesaList;
    }

    public void setReservaVMesaList(List<ReservaVMesa> reservaVMesaList) {
        this.reservaVMesaList = reservaVMesaList;
    }

    public LocalDateTime getHoraChegada() {
        return horaChegada;
    }

    public void setHoraChegada(LocalDateTime horaChegada) {
        this.horaChegada = horaChegada;
    }
}
