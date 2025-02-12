package br.com.fiap.reservas.infra.repository;

import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    RestauranteEntity buscarRestaurantePorNomeELocalizacaoETipoCozinha(String nome, EnderecoEntity endereco, String tipoCozinha);
}
