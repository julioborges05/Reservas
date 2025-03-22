package br.com.fiap.reservas.infra.repository.restaurante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    @Query(value = """
            select * from restaurante
            where nome = :nome
            """, nativeQuery = true)
    Optional<Restaurante> findByNome(String nome);

    @Query(value = """
            select r.* from restaurante r
            JOIN endereco e ON r.id_endereco = e.id where logradouro = :localizacao
            """, nativeQuery = true)
    Optional<Restaurante> findByLocalizacao(String localizacao);

    @Query(value = """
            select * from restaurante
            where tipo = :tipoCozinha
            """, nativeQuery = true)
    Optional<Restaurante> findByTipoCozinha(String tipoCozinha);

    @Query(value = """
            SELECT r.*
            FROM restaurante r
                    JOIN endereco e ON r.id_endereco = e.id
            WHERE r.nome= :nome
              AND r.tipo = :tipoCozinha
              AND (e.cep = :localizacao
                           or e.logradouro = :localizacao
                           or e.bairro = :localizacao
                           or e.cidade = :localizacao
                           or e.numero = :localizacao
                           or e.complemento = :localizacao)
            """, nativeQuery = true)
    Optional<Restaurante> findByNomeLocalizacaoETipoCozinha(String nome, String localizacao, String tipoCozinha);

    @Query(value = """
                    select r.*
                    from restaurante r
                    where r.id_endereco = :idEndereco
                    """, nativeQuery = true)
    Optional<Restaurante> findByEnderecoId(Long idEndereco);
}