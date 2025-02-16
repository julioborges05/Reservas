package br.com.fiap.reservas.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    @Query(value = """
            select * from avaliacao
            where restaurante = :restaurante
            """, nativeQuery = true)
     Avaliacao findAvaliacaoByRestaurante(String restaurante);

}
