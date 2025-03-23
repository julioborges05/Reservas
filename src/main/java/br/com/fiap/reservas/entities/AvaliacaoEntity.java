package br.com.fiap.reservas.entities;

public class AvaliacaoEntity {

    private int nota;
    private String comentario;
    private UsuarioEntity usuarioId;
    private RestauranteEntity restauranteId;

    public AvaliacaoEntity() {}

    public AvaliacaoEntity(int nota, String comentario, UsuarioEntity usuario, RestauranteEntity restaurante) {
        validarNota(nota);
        validarComentario(comentario);
        validarUsuario(usuario);
        validarRestaurante(restaurante);

        this.nota = nota;
        this.comentario = comentario;
        this.usuarioId = usuario;
        this.restauranteId = restaurante;
    }

    private static void validarNota(int nota) {
        if (nota < 0 || nota > 5) {
            throw new IllegalArgumentException("Nota inválida");
        }
    }

    private static void validarComentario(String comentario) {
        if (comentario == null || comentario.isEmpty()) {
            throw new IllegalArgumentException("Comentário inválido");
        }
    }

    private static void validarUsuario(UsuarioEntity usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário inválido");
        }
    }
    private static void validarRestaurante(RestauranteEntity restaurante) {
        if (restaurante == null) {
            throw new IllegalArgumentException("Restaurante inválido");
        }
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public UsuarioEntity getUsuario() {
        return usuarioId;
    }

    public void setUsuario(UsuarioEntity usuarioId) {
        this.usuarioId = usuarioId;
    }

    public RestauranteEntity getRestaurante() {
        return restauranteId;
    }

    public void setRestaurante(RestauranteEntity restauranteId) {
        this.restauranteId = restauranteId;
    }
}
