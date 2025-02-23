package br.com.fiap.reservas.infra.repository.avaliacao;

import br.com.fiap.reservas.entities.RestauranteEntity;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "restaurante_id")
    private Long restauranteId;

    private int nota;

    private String comentario;

    @Column(name = "usuario_id")
    private Long usuarioId;

    public Avaliacao() {
    }

    public Avaliacao(Long restauranteId, int nota, String comentario, Long usuarioId) {
        this.restauranteId = restauranteId;
        this.nota = nota;
        this.comentario = comentario;
        this.usuarioId = usuarioId;
    }

    public Long getId() {
        return id;
    }

    public Long getRestauranteId() {
        return restauranteId;
    }

    public int getNota() {
        return nota;
    }

    public String getComentario() {
        return comentario;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }
}
