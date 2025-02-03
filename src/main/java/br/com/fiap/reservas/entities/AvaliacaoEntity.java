package br.com.fiap.reservas.entities;

public class AvaliacaoEntity {
    private final int nota;
    private final String comentario;
    private final UsuarioEntity usuario;

    public AvaliacaoEntity(int nota, String comentario, UsuarioEntity usuario) {
        validarNota(nota);
        validarComentario(comentario);
        validarUsuario(usuario);

        this.nota = nota;
        this.comentario = comentario;
        this.usuario = usuario;
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
    
}
