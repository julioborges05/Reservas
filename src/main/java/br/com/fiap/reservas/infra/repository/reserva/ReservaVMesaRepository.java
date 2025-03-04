package br.com.fiap.reservas.infra.repository.reserva;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaVMesaRepository extends JpaRepository<ReservaVMesa, Long> {

}
