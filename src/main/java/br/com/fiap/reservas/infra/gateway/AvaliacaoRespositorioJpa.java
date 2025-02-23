package br.com.fiap.reservas.infra.gateway;

import br.com.fiap.reservas.controller.dto.AvaliacaoDto;
import br.com.fiap.reservas.entities.AvaliacaoEntity;
import br.com.fiap.reservas.infra.repository.avaliacao.Avaliacao;
import br.com.fiap.reservas.infra.repository.avaliacao.AvaliacaoRepository;
import br.com.fiap.reservas.interfaces.IAvaliacaoGateway;

public class AvaliacaoRespositorioJpa implements IAvaliacaoGateway {

    private final AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoRespositorioJpa(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    @Override
    public AvaliacaoEntity avaliarRestaurante(AvaliacaoDto avaliacaoDto) {
        Avaliacao avaliacao = new Avaliacao(avaliacaoDto.restauranteId(), avaliacaoDto.nota(), avaliacaoDto.comentario(), avaliacaoDto.usuarioId());

        avaliacaoRepository.save(avaliacao);

        return new AvaliacaoEntity(avaliacao.getNota(), avaliacao.getComentario(), avaliacao.getUsuarioId(), avaliacao.getRestauranteId());
    }

    @Override
    public AvaliacaoEntity buscarAvaliacaoPorRestaurante(Long id) {
        Avaliacao avaliacao = avaliacaoRepository.findAvaliacaoByRestaurante(id);

        return new AvaliacaoEntity(avaliacao.getNota(), avaliacao.getComentario(), avaliacao.getUsuarioId(), avaliacao.getRestauranteId());
    }
}
