package br.com.fiap.reservas.entities;

import br.com.fiap.reservas.enums.StatusReserva;
import br.com.fiap.reservas.infra.repository.mesa.MesaPK;
import br.com.fiap.reservas.infra.repository.reserva.Reserva;

public class ReservaVMesaEntity {
    private Long id;

    private Long idReserva;

    private MesaPK idMesa;

    private StatusReserva status;

    public ReservaVMesaEntity() {

    }

    public ReservaVMesaEntity(Long id, Long idReserva, MesaPK idMesa, StatusReserva status) {
        this.id = id;
        this.idReserva = idReserva;
        this.idMesa = idMesa;
        this.status = status;
    }

    public ReservaVMesaEntity(StatusReserva status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
