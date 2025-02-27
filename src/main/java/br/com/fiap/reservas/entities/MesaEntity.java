package br.com.fiap.reservas.entities;

import br.com.fiap.reservas.enums.StatusMesa;
import br.com.fiap.reservas.infra.repository.mesa.MesaPK;

public class MesaEntity {

    private MesaPK id;
    private final Integer numero;
    private StatusMesa statusMesa;

    public MesaEntity(Integer numero, StatusMesa statusMesa) {
        validarNumero(numero);
        validarStatusMesa(statusMesa);

        this.numero = numero;
        this.statusMesa = statusMesa;
    }

    public MesaEntity(MesaPK id, Integer numero, StatusMesa statusMesa) {
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

    public Integer getNumero() {
        return numero;
    }

    public MesaPK getId() {
        return id;
    }
}
