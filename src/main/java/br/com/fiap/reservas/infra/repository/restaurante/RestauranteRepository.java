package br.com.fiap.reservas.infra.repository.restaurante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    @Query(value = """
            select * from restaurante
            where nome = :nome
            """, nativeQuery = true)
    Restaurante findByNome(String nome);

    @Query(value = """
            select * from restaurante
            where endereco = :localizacao
            """, nativeQuery = true)
    Restaurante findByLocalizacao(String localizacao);

    @Query(value = """
            select * from restaurante
            where tipo = :tipoCozinha
            """, nativeQuery = true)
    Restaurante findByTipoCozinha(String tipoCozinha);

    @Query(value = """
            select * from restaurante
            where nome = :nome and endereco = :localizacao and tipo = :tipoCozinha
            """, nativeQuery = true)
    Restaurante findByNomeLocalizacaoETipoCozinha(String nome, String localizacao, String tipoCozinha);
}