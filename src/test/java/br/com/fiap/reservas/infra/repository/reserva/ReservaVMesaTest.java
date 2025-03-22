package br.com.fiap.reservas.infra.repository.reserva;

import br.com.fiap.reservas.enums.StatusReserva;
import br.com.fiap.reservas.infra.repository.endereco.Endereco;
import br.com.fiap.reservas.infra.repository.endereco.EnderecoRepository;
import br.com.fiap.reservas.infra.repository.mesa.MesaPK;
import br.com.fiap.reservas.infra.repository.restaurante.Restaurante;
import br.com.fiap.reservas.infra.repository.restaurante.RestauranteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReservaVMesaTest {

    @Autowired
    private ReservaVMesaRepository reservaVMesaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    private final LocalTime horarioAbertura = LocalTime.of(10, 0);
    private final LocalTime horarioFechamento = LocalTime.of(17, 0);

    @Test
    public void validarSalvarReservaComValores() {
        Endereco endereco = new Endereco("13181701", "logradouro", "bairro", "cidade", "numero", "complemento");
        enderecoRepository.save(endereco);

        Restaurante restaurante = new Restaurante("restaurante", endereco.getId(), "tipo", horarioAbertura, horarioFechamento, 12);
        restauranteRepository.save(restaurante);

        MesaPK mesaPK = new MesaPK();
        StatusReserva status = StatusReserva.RESERVADA;
        mesaPK.setNumeroMesa(1);
        mesaPK.setRestauranteId(restaurante.getId());

        ReservaVMesa reservaVMesa = new ReservaVMesa();
        reservaVMesa.setIdMesa(mesaPK);
        reservaVMesa.setStatus(status);
        ReservaVMesa savedReserva = reservaVMesaRepository.save(reservaVMesa);

        assertNotNull(savedReserva.getId());
        assertEquals(mesaPK, reservaVMesa.getIdMesa());
        assertEquals(1, mesaPK.getNumeroMesa());
        assertEquals(restaurante.getId(), mesaPK.getRestauranteId());
        assertEquals(mesaPK, savedReserva.getIdMesa());
        assertEquals(status, savedReserva.getStatus());
        assertEquals(reservaVMesa.getIdReserva(), savedReserva.getIdReserva());
    }

    @Test
    public void validarUpdateReservaVMesa() {
        Endereco endereco = new Endereco("13181701", "logradouro", "bairro", "cidade", "numero", "complemento");
        enderecoRepository.save(endereco);

        Restaurante restaurante = new Restaurante("restaurante", endereco.getId(), "tipo", horarioAbertura, horarioFechamento, 12);
        restauranteRepository.save(restaurante);

        StatusReserva status = StatusReserva.RESERVADA;
        MesaPK mesaPK = new MesaPK();
        mesaPK.setNumeroMesa(1);
        mesaPK.setRestauranteId(restaurante.getId());

        ReservaVMesa reservaVMesa = new ReservaVMesa(mesaPK, status);
        ReservaVMesa savedReservaVMesa = reservaVMesaRepository.save(reservaVMesa);

        StatusReserva statusUpdate = StatusReserva.CANCELADA;
        ReservaVMesa reservaVMesaToUpdate = new ReservaVMesa(savedReservaVMesa.getIdReserva(), statusUpdate);
        reservaVMesaToUpdate.setId(savedReservaVMesa.getId());
        reservaVMesaToUpdate.setIdMesa(savedReservaVMesa.getIdMesa());
        reservaVMesaToUpdate.setIdReserva(savedReservaVMesa.getIdReserva());

        ReservaVMesa updatedReservaVMesa = reservaVMesaRepository.save(reservaVMesaToUpdate);
        assertNotNull(updatedReservaVMesa.getId());
        assertEquals(reservaVMesaToUpdate.getId(), updatedReservaVMesa.getId());
        assertEquals(reservaVMesaToUpdate.getIdReserva(), updatedReservaVMesa.getIdReserva());
        assertEquals(mesaPK, updatedReservaVMesa.getIdMesa());
        assertEquals(statusUpdate, updatedReservaVMesa.getStatus());
    }
}
