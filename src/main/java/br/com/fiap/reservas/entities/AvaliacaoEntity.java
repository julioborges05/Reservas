package br.com.fiap.reservas.entities;

public class AvaliacaoEntity {
    private final int nota;
    private final String comentario;
    private final UsuarioEntity usuarioId;
    private final RestauranteEntity restauranteId;

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

    public RestauranteEntity getRestaurante() {
        return restauranteId;
    }

    public int getNota() {
        return nota;
    }

    public String getComentario() {
        return comentario;
    }

    public UsuarioEntity getUsuario(){
        return usuarioId;
    }
}
