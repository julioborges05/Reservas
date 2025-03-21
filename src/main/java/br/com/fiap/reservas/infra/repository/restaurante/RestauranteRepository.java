package br.com.fiap.reservas.infra.repository.restaurante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    @Query(value = """
            select * from restaurante
            where nome = :nome
            """, nativeQuery = true)
    Restaurante findByNome(String nome);

    @Query(value = """
            select r.* from restaurante r
            JOIN endereco e ON r.id_endereco = e.id where logradouro = :localizacao
            """, nativeQuery = true)
    Restaurante findByLocalizacao(String localizacao);

    @Query(value = """
            select * from restaurante
            where tipo = :tipoCozinha
            """, nativeQuery = true)
    Restaurante findByTipoCozinha(String tipoCozinha);

    @Query(value = """
            SELECT r.* FROM restaurante r
            JOIN endereco e ON r.id_endereco = e.id WHERE r.nome= :nome AND e.logradouro = :localizacao
            AND r.tipo = :tipoCozinha ;""", nativeQuery = true)
    Restaurante findByNomeLocalizacaoETipoCozinha(String nome, String localizacao, String tipoCozinha);
}