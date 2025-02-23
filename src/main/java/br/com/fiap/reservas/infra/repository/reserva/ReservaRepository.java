package br.com.fiap.reservas.infra.repository.reserva;

import br.com.fiap.reservas.enums.StatusMesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query(value = """
            select * from reserva
            where restaurante_id = :restauranteId AND
            mesa.statusMesa = :statusMesa
            """, nativeQuery = true)
    List<Reserva> findByRestauranteId(Long restauranteId, StatusMesa statusMesa);
}
