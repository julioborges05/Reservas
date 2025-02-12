package br.com.fiap.reservas.infra.repository;

import br.com.fiap.reservas.entities.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    @Query(value = """
            select * from restaurante
            where nome = :nome and endereco = :endereco and tipo = :tipo
            """, nativeQuery = true)
    Restaurante findByNomeAndEnderecoAndTipo(String nome, String endereco, String tipo);
}
