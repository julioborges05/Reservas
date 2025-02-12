package br.com.fiap.reservas.infra.gateway;

import br.com.fiap.reservas.entities.*;
import br.com.fiap.reservas.enums.StatusMesa;
import br.com.fiap.reservas.infra.repository.Reserva;
import br.com.fiap.reservas.infra.repository.ReservaRepository;
import br.com.fiap.reservas.infra.repository.Restaurante;
import br.com.fiap.reservas.infra.repository.RestauranteRepository;
import br.com.fiap.reservas.interfaces.IReservaGateway;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservaRepositorioJpa implements IReservaGateway {

    private final ReservaRepository reservaRepository;

    public ReservaRepositorioJpa(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Override
    public List<ReservaEntity> buscarReservas(StatusMesa statusMesa) {
        List<Reserva> reserva = reservaRepository.findByStatusMesa(statusMesa);
        List<ReservaEntity> reservaEntityList = new ArrayList<>();

        EnderecoEntity enderecoEntity = new EnderecoEntity("123", "456", "789", "101112",
                "123", "123");

        RestauranteEntity restauranteEntity = new RestauranteEntity("teste", enderecoEntity, "teste", LocalDateTime.now(),
                LocalDateTime.now(), 10, new ArrayList<>());

        reserva.forEach(res -> {
            //passar os valores da busca no repository
            ReservaEntity reservaEntity = new ReservaEntity(restauranteEntity, new UsuarioEntity("Lucas"),
                    new MesaEntity(1, StatusMesa.RESERVADA));

            reservaEntityList.add(reservaEntity);
        });

        return reservaEntityList;
    }
}
