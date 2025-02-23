package br.com.fiap.reservas.infra.gateway;

import br.com.fiap.reservas.entities.*;
import br.com.fiap.reservas.enums.StatusMesa;
import br.com.fiap.reservas.infra.repository.mesa.Mesa;
import br.com.fiap.reservas.infra.repository.reserva.Reserva;
import br.com.fiap.reservas.infra.repository.reserva.ReservaRepository;
import br.com.fiap.reservas.infra.repository.restaurante.Restaurante;
import br.com.fiap.reservas.interfaces.IReservaGateway;

import java.util.ArrayList;
import java.util.List;

public class ReservaRepositorioJpa implements IReservaGateway {

    private final ReservaRepository reservaRepository;

    public ReservaRepositorioJpa(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public List<ReservaEntity> buscarReservasPorRestaurante(Long restauranteId) {
        List<Reserva> reserva = reservaRepository.findByRestauranteId(restauranteId, StatusMesa.RESERVADA);
        List<ReservaEntity> reservaEntityList = new ArrayList<>();

        EnderecoEntity enderecoEntity = new EnderecoEntity("123", "456", "789", "101112",
                "123", "123");

        reserva.forEach(res -> {
            Restaurante restaurante = res.getRestaurante();
            RestauranteEntity restauranteEntity = new RestauranteEntity(restaurante.getNome(),
                    enderecoEntity, restaurante.getTipo(), restaurante.getHorarioAbertura(),
                    restaurante.getHorarioFechamento(), restaurante.getCapacidade());

            List<MesaEntity> mesaEntityList = new ArrayList<>();
            List<Mesa> mesaList = res.getMesaList();

            mesaList.forEach(mesa -> {
                MesaEntity mesaEntity = new MesaEntity(mesa.getNumero(), mesa.getStatusMesa());
                mesaEntityList.add(mesaEntity);
            });

            ReservaEntity reservaEntity = new ReservaEntity(restauranteEntity, res.getNomeUsuario(),
                    mesaEntityList);

            reservaEntityList.add(reservaEntity);
        });

        return reservaEntityList;
    }
}
