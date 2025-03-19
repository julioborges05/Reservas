package br.com.fiap.reservas.infra.gateway;

import br.com.fiap.reservas.entities.AvaliacaoEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.entities.UsuarioEntity;
import br.com.fiap.reservas.infra.repository.avaliacao.Avaliacao;
import br.com.fiap.reservas.infra.repository.avaliacao.AvaliacaoRepository;
import br.com.fiap.reservas.interfaces.IAvaliacaoGateway;
import jakarta.transaction.Transactional;

public class AvaliacaoRepositorioJpa implements IAvaliacaoGateway {

    private final AvaliacaoRepository avaliacaoRepository;
    private final RestauranteRepositorioJpa restauranteRepositorioJpa;
    private final UsuarioRepositorioJpa usuarioRepositorioJpa;

    public AvaliacaoRepositorioJpa(AvaliacaoRepository avaliacaoRepository, RestauranteRepositorioJpa restauranteRepositorioJpa,
                                   UsuarioRepositorioJpa usuarioRepositorioJpa) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.restauranteRepositorioJpa = restauranteRepositorioJpa;
        this.usuarioRepositorioJpa = usuarioRepositorioJpa;
    }

    @Override
    @Transactional
    public AvaliacaoEntity avaliarRestaurante(AvaliacaoEntity avaliacaoEntity) {
        Avaliacao avaliacao = new Avaliacao(avaliacaoEntity);
        avaliacaoRepository.save(avaliacao);

        return new AvaliacaoEntity(avaliacao.getNota(), avaliacao.getComentario(), avaliacaoEntity.getUsuario(),
                avaliacaoEntity.getRestaurante());
    }

    @Override
    public AvaliacaoEntity buscarAvaliacaoPorRestaurante(Long id) {
        Avaliacao avaliacao = avaliacaoRepository.findAvaliacaoByRestaurante(id);
        if (avaliacao == null) {
            throw new RuntimeException("Avaliação não encontrada");
        }
        RestauranteEntity restauranteEntity = restauranteRepositorioJpa.buscarRestaurantePorId(avaliacao.getRestauranteId());
        UsuarioEntity usuarioEntity = usuarioRepositorioJpa.buscarUsuarioPorId(avaliacao.getUsuarioId());

        return new AvaliacaoEntity(avaliacao.getNota(), avaliacao.getComentario(), usuarioEntity, restauranteEntity);
    }
}
