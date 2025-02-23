package br.com.fiap.reservas.entities;

import br.com.fiap.reservas.enums.StatusMesa;

public class MesaEntity {

    private Integer id;
    private final Integer numero;
    private StatusMesa statusMesa;

    public MesaEntity(Integer numero, StatusMesa statusMesa) {
        validarNumero(numero);
        validarStatusMesa(statusMesa);

        this.numero = numero;
        this.statusMesa = statusMesa;
    }

    public MesaEntity(Integer id, Integer numero, StatusMesa statusMesa) {
        validarNumero(numero);
        validarStatusMesa(statusMesa);

        this.id = id;
        this.numero = numero;
        this.statusMesa = statusMesa;
    }

    private static void validarNumero(Integer numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("Numero de mesa inválido");
        }
    }

    private static void validarStatusMesa(StatusMesa statusMesa) {
        if (statusMesa == null) {
            throw new IllegalArgumentException("Status inválido");
        }
    }

    public void setStatusMesa(StatusMesa statusMesa) {
        this.statusMesa = statusMesa;
    }

    public StatusMesa getStatusMesa() {
        return statusMesa;
    }

    public Integer getNumero() {
        return numero;
    }

    public Integer getId() {
        return id;
    }
}
