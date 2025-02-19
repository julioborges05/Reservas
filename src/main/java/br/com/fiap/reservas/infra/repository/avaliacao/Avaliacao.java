package br.com.fiap.reservas.infra.repository.avaliacao;

import br.com.fiap.reservas.entities.RestauranteEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Restaurante;

    private int nota;

    private String comentario;


    private String usuario;

    public Avaliacao() {
    }

    public Avaliacao(String Restaurante, int nota, String comentario, String usuario) {
        this.Restaurante = Restaurante;
        this.nota = nota;
        this.comentario = comentario;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public String getRestaurante() {
        return Restaurante;
    }

    public int getNota() {
        return nota;
    }

    public String getComentario() {
        return comentario;
    }

    public String getUsuario() {
        return usuario;
    }
}
