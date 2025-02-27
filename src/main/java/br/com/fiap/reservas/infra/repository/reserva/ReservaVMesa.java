package br.com.fiap.reservas.infra.repository.reserva;

import br.com.fiap.reservas.enums.StatusReserva;
import br.com.fiap.reservas.infra.repository.mesa.MesaPK;
import jakarta.persistence.*;

@Entity
@Table(name = "reserva_vm")
public class ReservaVMesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idReserva;

    @Embedded
    private MesaPK idMesa;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusReserva status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReservaVMesa() {
    }

    public ReservaVMesa(Long idReserva, MesaPK idMesa, StatusReserva status) {
        this.idReserva = idReserva;
        this.idMesa = idMesa;
        this.status = status;
    }

    public ReservaVMesa(Long idReserva, MesaPK idMesa) {
        this.idReserva = idReserva;
        this.idMesa = idMesa;
    }

    public ReservaVMesa(MesaPK idMesa, StatusReserva status) {
        this.idMesa = idMesa;
        this.status = status;
    }

    public ReservaVMesa(Long idReserva, StatusReserva status) {
        this.idReserva = idReserva;
        this.status = status;
    }

    public ReservaVMesa(StatusReserva status) {
        this.status = status;
    }

    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }

    public MesaPK getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(MesaPK idMesa) {
        this.idMesa = idMesa;
    }

    public StatusReserva getStatus() {
        return status;
    }

    public void setStatus(StatusReserva status) {
        this.status = status;
    }
}
