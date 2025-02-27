package br.com.fiap.reservas.infra.repository.mesa;

import br.com.fiap.reservas.enums.StatusMesa;
import br.com.fiap.reservas.enums.StatusReserva;
import br.com.fiap.reservas.infra.repository.reserva.ReservaVMesa;
import br.com.fiap.reservas.infra.repository.restaurante.Restaurante;
import jakarta.persistence.*;

@Entity
public class Mesa {

    @EmbeddedId
    private MesaPK id;

    @ManyToOne
    @JoinColumn(name = "id_restaurante", referencedColumnName = "id", insertable = false, updatable = false)
    private Restaurante restaurante;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_mesa")
    private StatusMesa statusMesa;

    @OneToOne
    @JoinColumn(name = "id_reserva_vm")
    private ReservaVMesa reservaVMesa;

    public Mesa() {
    }

    public Mesa(MesaPK id) {
        this.id = id;
    }

    public Mesa(MesaPK id, Restaurante restaurante) {
        this.id = id;
        this.restaurante = restaurante;
    }

    public Mesa(MesaPK id, Restaurante restaurante, StatusMesa statusMesa) {
        this.id = id;
        this.restaurante = restaurante;
        this.statusMesa = statusMesa;
    }

    public MesaPK getId() {
        return id;
    }

    public Integer getNumero() {
        return id.getNumeroMesa();
    }

    public void setNumero(Integer numero) {
        this.getId().setNumeroMesa(numero);
    }

    public void setId(MesaPK id) {
        this.id = id;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public ReservaVMesa getReservaVMesa() {
        return reservaVMesa;
    }

    public void setReservaVMesa(ReservaVMesa reservaVMesa) {
        this.reservaVMesa = reservaVMesa;
    }

    public StatusMesa getStatusMesa() {
        return statusMesa;
    }

    public void setStatusMesa(StatusMesa statusMesa) {
        this.statusMesa = statusMesa;
    }
}
