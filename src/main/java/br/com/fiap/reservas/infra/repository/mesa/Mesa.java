package br.com.fiap.reservas.infra.repository.mesa;

import br.com.fiap.reservas.enums.StatusMesa;
import br.com.fiap.reservas.infra.repository.restaurante.Restaurante;
import jakarta.persistence.*;

@Entity
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusMesa statusMesa;

    @ManyToOne
    @JoinColumn(name = "id_restaurante", nullable = false)
    private Restaurante restaurante;

    private Integer numero;

    public Mesa() {
    }

    public Mesa(Integer id, StatusMesa statusMesa) {
        this.id = id;
        this.statusMesa = statusMesa;
    }

    public Mesa(Integer id, StatusMesa statusMesa, Integer numero, Restaurante restaurante) {
        this.id = id;
        this.statusMesa = statusMesa;
        this.numero = numero;
        this.restaurante = restaurante;
    }

    public Integer getId() {
        return id;
    }

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
