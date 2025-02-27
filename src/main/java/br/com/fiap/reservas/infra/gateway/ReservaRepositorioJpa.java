package br.com.fiap.reservas.infra.gateway;

import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.entities.ReservaEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.enums.StatusReserva;
import br.com.fiap.reservas.infra.repository.mesa.Mesa;
import br.com.fiap.reservas.infra.repository.reserva.Reserva;
import br.com.fiap.reservas.infra.repository.reserva.ReservaRepository;
import br.com.fiap.reservas.infra.repository.reserva.ReservaVMesa;
import br.com.fiap.reservas.infra.repository.restaurante.Restaurante;
import br.com.fiap.reservas.interfaces.IReservaGateway;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservaRepositorioJpa implements IReservaGateway {

    private final ReservaRepository reservaRepository;

    private EnderecoRepositorioJpa enderecoRepositorioJpa;

    public ReservaRepositorioJpa(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public List<ReservaEntity> buscarReservasPorRestaurante(Long restauranteId) {
        List<Reserva> reserva = reservaRepository.findByRestauranteId(restauranteId, StatusReserva.RESERVADA);
        List<ReservaEntity> reservaEntityList = new ArrayList<>();

        reserva.forEach(res -> {
            Restaurante restaurante = res.getRestaurante();
            EnderecoEntity enderecoEntity = enderecoRepositorioJpa.buscarEnderecoPeloId(restaurante.getIdEndereco());
            RestauranteEntity restauranteEntity = new RestauranteEntity(restaurante.getNome(),
                    enderecoEntity, restaurante.getTipo(), restaurante.getHorarioAbertura(),
                    restaurante.getHorarioFechamento(), restaurante.getCapacidade());

            ReservaEntity reservaEntity = getReservaEntity(res, restauranteEntity);

            reservaEntityList.add(reservaEntity);
        });

        return reservaEntityList;
    }

    @Override
    public ReservaEntity cadastrarReserva(RestauranteEntity restauranteEntity, String nomeUsuario,
                                          List<ReservaVMesa> reservaVMesaList) {
        Reserva reservaSalvo = reservaRepository.save(getReserva(restauranteEntity.getId(), nomeUsuario, reservaVMesaList));

        List<ReservaVMesa> mesaEntityList = new ArrayList<>();
        mesaEntityList.addAll(reservaVMesaList);

        return new ReservaEntity(
                restauranteEntity,
                reservaSalvo.getNomeUsuario(),
                mesaEntityList
        );
    }

    @Override
    public ReservaEntity atualizarStatusReserva(String nomeUsuario) {
        Reserva reserva = reservaRepository.findByNomeUsuario(nomeUsuario);
        List<ReservaVMesa> reservaVMesas = new ArrayList<>();

        reserva.getReservaVMesaList().forEach(reservaVMesa -> {
            ReservaVMesa reservaMesa = new ReservaVMesa(reserva.getId(), reservaVMesa.getIdMesa(), StatusReserva.CANCELADA);
            reservaVMesas.add(reservaMesa);
        });
        reserva.setReservaVMesaList(reservaVMesas);
        reservaRepository.save(reserva);
        return null;
    }

    @Override
    public void atualizarQtdPessoasReserva(ReservaEntity reservaEntity) {
        RestauranteEntity restauranteEntity = reservaEntity.getRestaurante();

        Restaurante restaurante = new Restaurante(restauranteEntity.getNome(), restauranteEntity.getEndereco().getId(),
                restauranteEntity.getTipoCozinha(), restauranteEntity.getHorarioAbertura(),
                restauranteEntity.getHorarioFechamento(), restauranteEntity.getCapacidade());

        Reserva reserva = new Reserva(reservaEntity.getId(), reservaEntity.getNomeUsuario(), restaurante,
                reservaEntity.getMesaList(), reservaEntity.getHoraChegada());

        reservaRepository.save(reserva);
    }

    @Override
    public ReservaEntity findById(Long id) {
        Optional<Reserva> reservaOptional = reservaRepository.findById(id);

        if (reservaOptional.isPresent()) {
            Reserva reserva = reservaOptional.get();
            Restaurante restaurante = reserva.getRestaurante();
            EnderecoEntity enderecoEntity = enderecoRepositorioJpa.buscarEnderecoPeloId(restaurante.getIdEndereco());
            RestauranteEntity restauranteEntity = new RestauranteEntity(restaurante.getNome(),
                    enderecoEntity, restaurante.getTipo(), restaurante.getHorarioAbertura(),
                    restaurante.getHorarioFechamento(), restaurante.getCapacidade());

            return new ReservaEntity(restauranteEntity, reserva.getNomeUsuario(),
                    reserva.getReservaVMesaList());
        } else {
            throw new RuntimeException("Reserva não encontrada");
        }
    }

    private static Reserva getReserva(Long restauranteId, String nomeUsuario, List<ReservaVMesa> reservaVMesaList) {
        Restaurante restaurante = new Restaurante(restauranteId);
        return new Reserva(restaurante, nomeUsuario, reservaVMesaList);
    }

    private static ReservaEntity getReservaEntity(Reserva reserva, RestauranteEntity restauranteEntity) {
        List<ReservaVMesa> reservaVMesas = new ArrayList<>();
        List<Mesa> mesaList = reserva.getMesaList();

        mesaList.forEach(mesa -> {
            ReservaVMesa reservaVMesa = new ReservaVMesa(reserva.getId(), mesa.getId(),
                    mesa.getReservaVMesa().getStatus());
            reservaVMesas.add(reservaVMesa);
        });

        return new ReservaEntity(restauranteEntity, reserva.getNomeUsuario(),
                reservaVMesas);
    }
}
