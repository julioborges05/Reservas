package br.com.fiap.reservas.infra.repository.mesa;

import br.com.fiap.reservas.enums.StatusMesa;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {

    List<Mesa> findAllByRestaurante_IdAndStatusMesaEquals(Long restauranteId, StatusMesa statusMesa);

    @Transactional
    @Modifying
    @Query("UPDATE Mesa e SET e.statusMesa = :statusMesa WHERE e.id = :id")
    void atualizarStatus(@Param("id") Integer id, @Param("statusMesa") StatusMesa statusMesa);
}
