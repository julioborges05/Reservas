package br.com.fiap.reservas.infra.repository.endereco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    Optional<Endereco> findByCepAndNumeroAndComplemento(String cep, String numero, String complemento);

    @Query(value = """
                        select e.*
                        from endereco e
                        where e.cep = :texto
                           or e.logradouro = :texto
                           or e.bairro = :texto
                           or e.cidade = :texto
                           or e.numero = :texto
                           or e.complemento = :texto
                        """, nativeQuery = true)
    Optional<Endereco> findByCepOrLogradouroOrBairroOrCidadeOrNumeroOrComplemento(String texto);
}
