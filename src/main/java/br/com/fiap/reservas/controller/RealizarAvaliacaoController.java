package br.com.fiap.reservas.controller;

import br.com.fiap.reservas.controller.dto.AvaliacaoDto;
import br.com.fiap.reservas.entities.AvaliacaoEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.entities.UsuarioEntity;
import br.com.fiap.reservas.interfaces.IAvaliacaoGateway;
import br.com.fiap.reservas.interfaces.IRestauranteGateway;
import br.com.fiap.reservas.interfaces.IUsuarioGateway;
import br.com.fiap.reservas.usecases.AvaliaRestauranteUseCase;

public class RealizarAvaliacaoController {

    private final IAvaliacaoGateway avaliacaoGateway;
    private final IUsuarioGateway usuarioGateway;
    private final IRestauranteGateway restauranteGateway;

    public RealizarAvaliacaoController(IAvaliacaoGateway avaliacaoGateway, IUsuarioGateway usuarioGateway,
            IRestauranteGateway restauranteGateway) {
        this.avaliacaoGateway = avaliacaoGateway;
        this.usuarioGateway = usuarioGateway;
        this.restauranteGateway = restauranteGateway;
    }

    public AvaliacaoEntity realizarAvaliacao(AvaliacaoDto avaliacao) {
        UsuarioEntity usuarioEntity = usuarioGateway.buscarUsuarioPorId(avaliacao.usuarioId());
        RestauranteEntity restauranteEntity = restauranteGateway.buscarRestaurantePorId(avaliacao.restauranteId());

        AvaliacaoEntity avaliacaoEntity = AvaliaRestauranteUseCase.criarAvaliacaoDoRestaurante(avaliacao.nota(), avaliacao.comentario(),
                usuarioEntity, restauranteEntity);

        return avaliacaoGateway.avaliarRestaurante(avaliacaoEntity);
    }
}
