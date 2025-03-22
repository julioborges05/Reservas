package br.com.fiap.reservas.infra.gateway;

import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.entities.ReservaEntity;
import br.com.fiap.reservas.entities.ReservaVMesaEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.enums.StatusReserva;
import br.com.fiap.reservas.infra.repository.mesa.MesaPK;
import br.com.fiap.reservas.infra.repository.reserva.Reserva;
import br.com.fiap.reservas.infra.repository.reserva.ReservaRepository;
import br.com.fiap.reservas.infra.repository.reserva.ReservaVMesa;
import br.com.fiap.reservas.infra.repository.restaurante.Restaurante;
import br.com.fiap.reservas.interfaces.IReservaGateway;
import br.com.fiap.reservas.utils.DateFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReservaRepositorioJpa implements IReservaGateway {

    private final ReservaRepository reservaRepository;
    private final EnderecoRepositorioJpa enderecoRepositorioJpa;
    private final RestauranteRepositorioJpa restauranteRepositorioJpa;

    public ReservaRepositorioJpa(ReservaRepository reservaRepository, EnderecoRepositorioJpa enderecoRepositorioJpa,
                                 RestauranteRepositorioJpa restauranteRepositorioJpa) {
        this.reservaRepository = reservaRepository;
        this.enderecoRepositorioJpa = enderecoRepositorioJpa;
        this.restauranteRepositorioJpa = restauranteRepositorioJpa;
    }

    @Override
    public List<ReservaEntity> buscarReservasPorRestaurante(Long restauranteId) {
        return reservaRepository.findByRestauranteId(restauranteId, StatusReserva.RESERVADA)
                .stream()
                .map(this::convertToReservaEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ReservaEntity cadastrarReserva(RestauranteEntity restauranteEntity, String nomeUsuario,
                                          List<ReservaVMesaEntity> reservaVMesaList, LocalDateTime horaChegada) {
        List<ReservaVMesa> reservaVMesas = convertToReservaVMesaList(reservaVMesaList);
        Reserva reservaSalva = reservaRepository.save(createReserva(restauranteEntity.getId(), nomeUsuario, reservaVMesas, horaChegada));
        return createReservaEntity(restauranteEntity, reservaSalva, reservaVMesaList, horaChegada);
    }

    @Override
    public ReservaEntity atualizarStatusReserva(String nomeUsuario, String horaChegada) {
        Reserva reserva = findReservaByNomeUsuarioAndHoraChegada(nomeUsuario, horaChegada);
        reserva.setReservaVMesaList(updateReservaVMesaStatus(reserva.getReservaVMesaList(), StatusReserva.CANCELADA));
        Reserva reservaSalva = reservaRepository.save(reserva);
        return convertToReservaEntity(reservaSalva);
    }

    @Override
    public void atualizarQtdPessoasReserva(ReservaEntity reservaEntity) {
        Reserva reserva = reservaRepository.findById(reservaEntity.getId())
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));
        reserva.setReservaVMesaList(convertToReservaVMesaList(reservaEntity.getReservaVMesaList()));
        reservaRepository.save(reserva);
    }

    @Override
    public ReservaEntity buscaReservaPeloId(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));
        return convertToReservaEntity(reserva);
    }

    private Reserva findReservaByNomeUsuarioAndHoraChegada(String nomeUsuario, String horaChegada) {
        return reservaRepository.findByNomeUsuario(nomeUsuario, DateFormat.convertFromStringToLocalDateTime(horaChegada))
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));
    }

    private List<ReservaVMesa> convertToReservaVMesaList(List<ReservaVMesaEntity> reservaVMesaEntities) {
        return reservaVMesaEntities.stream()
                .map(entity -> new ReservaVMesa(entity.getId(), entity.getIdReserva(), entity.getIdMesa(), entity.getStatus()))
                .collect(Collectors.toList());
    }

    private List<ReservaVMesa> updateReservaVMesaStatus(List<ReservaVMesa> reservaVMesas, StatusReserva status) {
        return reservaVMesas.stream()
                .map(reservaVMesa -> new ReservaVMesa(reservaVMesa.getId(), reservaVMesa.getIdReserva(), reservaVMesa.getIdMesa(), status))
                .collect(Collectors.toList());
    }

    private Reserva createReserva(Long restauranteId, String nomeUsuario, List<ReservaVMesa> reservaVMesaList, LocalDateTime horaChegada) {
        RestauranteEntity restauranteEntity = restauranteRepositorioJpa.buscarRestaurantePorId(restauranteId);
        Restaurante restaurante = new Restaurante(restauranteEntity);
        return new Reserva(restaurante, nomeUsuario, reservaVMesaList, horaChegada);
    }

    private ReservaEntity createReservaEntity(RestauranteEntity restauranteEntity, Reserva reservaSalva, List<ReservaVMesaEntity> reservaVMesaList, LocalDateTime horaChegada) {
        return new ReservaEntity(
                restauranteEntity,
                reservaSalva.getNomeUsuario(),
                reservaVMesaList,
                horaChegada
        );
    }

    private ReservaEntity convertToReservaEntity(Reserva reserva) {
        Restaurante restaurante = reserva.getRestaurante();
        EnderecoEntity enderecoEntity = enderecoRepositorioJpa.buscarEnderecoPeloId(restaurante.getIdEndereco());
        RestauranteEntity restauranteEntity = new RestauranteEntity(restaurante.getNome(), enderecoEntity, restaurante.getTipo(), restaurante.getHorarioAbertura(), restaurante.getHorarioFechamento(), restaurante.getCapacidade());
        return new ReservaEntity(reserva.getId(), restauranteEntity, reserva.getNomeUsuario(), convertToReservaVMesaEntityList(reserva.getReservaVMesaList()));
    }

    private List<ReservaVMesaEntity> convertToReservaVMesaEntityList(List<ReservaVMesa> reservaVMesas) {
        return reservaVMesas.stream()
                .map(reservaVMesa -> new ReservaVMesaEntity(reservaVMesa.getId(), reservaVMesa.getIdReserva(), reservaVMesa.getIdMesa(), reservaVMesa.getStatus()))
                .collect(Collectors.toList());
    }
}