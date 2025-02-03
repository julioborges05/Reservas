package br.com.fiap.reservas.entities;

public class AvaliacaoEntity {
    private final int nota;
    private final String comentario;

    public AvaliacaoEntity(int nota, String comentario) {
        validarNota(nota);
        validarComentario(comentario);

        this.nota = nota;
        this.comentario = comentario;
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
}
