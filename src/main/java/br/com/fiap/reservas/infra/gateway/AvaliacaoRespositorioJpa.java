package br.com.fiap.reservas.infra.gateway;

import br.com.fiap.reservas.entities.AvaliacaoEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.entities.UsuarioEntity;
import br.com.fiap.reservas.infra.repository.avaliacao.AvaliacaoRepository;
import br.com.fiap.reservas.interfaces.IAvaliacaoGateway;

public class AvaliacaoRespositorioJpa implements IAvaliacaoGateway {

    private final AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoRespositorioJpa(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    @Override
    public AvaliacaoEntity avaliarRestaurante(RestauranteEntity restaurante, int nota, String comentario, UsuarioEntity usuario) {
        return null;
    }

    @Override
    public AvaliacaoEntity buscarAvaliacaoPorRestaurante(String nome) {
        return avaliacaoRepository.findAvaliacaoByRestaurante(nome);
    }
}
