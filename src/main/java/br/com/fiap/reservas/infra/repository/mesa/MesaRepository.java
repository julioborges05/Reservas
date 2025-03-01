package br.com.fiap.reservas.infra.repository.mesa;

import br.com.fiap.reservas.enums.StatusMesa;
import br.com.fiap.reservas.enums.StatusReserva;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {

    @Query("SELECT m FROM Mesa m WHERE m.restaurante.id = :restauranteId AND m.statusMesa = :statusMesa")
    List<Mesa> findMesasByRestauranteAndStatus(@Param("restauranteId") Long restauranteId, @Param("statusMesa") StatusMesa statusMesa);

    @Transactional
    @Modifying
    @Query("UPDATE Mesa e SET e.statusMesa = :statusMesa WHERE e.id = :id")
    void atualizarStatus(@Param("id") MesaPK id, @Param("statusMesa") StatusMesa statusMesa);
}
