package br.com.fiap.reservas.infra.repository.avaliacao;

import br.com.fiap.reservas.entities.AvaliacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    @Query(value = """
            select * from avaliacao
            where restaurante_id = :id
            """, nativeQuery = true)
    Avaliacao findAvaliacaoByRestaurante(Long id);

}
