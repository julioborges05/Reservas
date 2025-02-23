package br.com.fiap.reservas.infra.repository.mesa;

import br.com.fiap.reservas.enums.StatusMesa;
import jakarta.persistence.*;

@Entity
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numero;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusMesa statusMesa;

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public StatusMesa getStatusMesa() {
        return statusMesa;
    }

    public void setStatusMesa(StatusMesa statusMesa) {
        this.statusMesa = statusMesa;
    }
}
