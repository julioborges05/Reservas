package br.com.fiap.reservas.infra.repository.reserva;

import br.com.fiap.reservas.infra.repository.mesa.Mesa;
import br.com.fiap.reservas.infra.repository.restaurante.Restaurante;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //OU NOME USUARIO
    @Column(name = "nome_usuario")
    private String nomeUsuario;

    @ManyToOne
    @JoinColumn(name = "id_restaurante")
    private Restaurante restaurante;

    @JoinColumn(name = "id_mesa")
    private Integer id_mesa;

    public Reserva() {
    }

    public Reserva(Restaurante restaurante, String nomeUsuario, Mesa mesa) {
        this.nomeUsuario = nomeUsuario;
        this.restaurante = restaurante;
        this.id_mesa = mesa.getId();
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

    public List<Mesa> getMesaList() {
        return List.of();
    }

}
