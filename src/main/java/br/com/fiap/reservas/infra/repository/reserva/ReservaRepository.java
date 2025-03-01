package br.com.fiap.reservas.infra.repository.reserva;

import br.com.fiap.reservas.enums.StatusReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("""
            select r from Reserva r
            join r.reservaVMesaList rv
            where r.restaurante.id = :restauranteId and rv.status = :statusReserva
            """)
    List<Reserva> findByRestauranteId(@Param("restauranteId") Long restauranteId,
                                      @Param("statusReserva") StatusReserva statusReserva);

    @Query(value = """
            select * from reserva
            where nome_usuario = :nomeUsuario and hora_chegada = :horaChegada
            """, nativeQuery = true)
    Reserva findByNomeUsuario(@Param("nomeUsuario") String nomeUsuario, @Param("horaChegada") LocalDateTime horaChegada);
}
