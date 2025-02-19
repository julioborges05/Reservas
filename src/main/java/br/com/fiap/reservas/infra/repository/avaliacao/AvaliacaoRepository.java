package br.com.fiap.reservas.infra.repository.avaliacao;

import br.com.fiap.reservas.entities.AvaliacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    @Query(value = """
            select * from avaliacao
            where restaurante = :restaurante
            """, nativeQuery = true)
    AvaliacaoEntity findAvaliacaoByRestaurante(String restaurante);

}
