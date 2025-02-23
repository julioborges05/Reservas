package br.com.fiap.reservas.infra.repository.mesa;

import br.com.fiap.reservas.enums.StatusMesa;
import br.com.fiap.reservas.infra.repository.reserva.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {

}
