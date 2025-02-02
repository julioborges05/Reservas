package br.com.fiap.reservas.entities;

public class ReservaEntity {
    private final RestauranteEntity restaurante;
    private final UsuarioEntity usuario;

    public ReservaEntity(RestauranteEntity restaurante, UsuarioEntity usuario) {
        if (restaurante == null) {
            throw new IllegalArgumentException("Restaurante deve ser informado");
        }
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário deve ser informado");
        }
        this.restaurante = restaurante;
        this.usuario = usuario;
    }
}
