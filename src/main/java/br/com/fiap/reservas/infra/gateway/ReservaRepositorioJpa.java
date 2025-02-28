package br.com.fiap.reservas.infra.gateway;

import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.entities.ReservaEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.enums.StatusReserva;
import br.com.fiap.reservas.infra.repository.reserva.Reserva;
import br.com.fiap.reservas.infra.repository.reserva.ReservaRepository;
import br.com.fiap.reservas.infra.repository.reserva.ReservaVMesa;
import br.com.fiap.reservas.infra.repository.restaurante.Restaurante;
import br.com.fiap.reservas.interfaces.IReservaGateway;
import br.com.fiap.reservas.utils.DateFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<ReservaEntity> buscarReservasPorRestaurante(Long restauranteId) {
        List<Reserva> reserva = reservaRepository.findByRestauranteId(restauranteId, StatusReserva.RESERVADA);
        List<ReservaEntity> reservaEntityList = new ArrayList<>();

        reserva.forEach(res -> {
            Restaurante restaurante = res.getRestaurante();
            EnderecoEntity enderecoEntity = enderecoRepositorioJpa.buscarEnderecoPeloId(restaurante.getIdEndereco());
            RestauranteEntity restauranteEntity = new RestauranteEntity(restaurante.getNome(),
                    enderecoEntity, restaurante.getTipo(), restaurante.getHorarioAbertura(),
                    restaurante.getHorarioFechamento(), restaurante.getCapacidade());

            ReservaEntity reservaEntity = getReservaEntity(res, restauranteEntity, res.getHoraChegada());

            reservaEntityList.add(reservaEntity);
        });

        return reservaEntityList;
    }

    @Override
    public ReservaEntity cadastrarReserva(RestauranteEntity restauranteEntity, String nomeUsuario,
                                          List<ReservaVMesa> reservaVMesaList, LocalDateTime horaChegada) {
        Reserva reservaSalvo = reservaRepository.save(getReserva(restauranteEntity.getId(), nomeUsuario,
                reservaVMesaList, horaChegada));

        List<ReservaVMesa> mesaEntityList = new ArrayList<>();
        mesaEntityList.addAll(reservaVMesaList);

        return new ReservaEntity(
                restauranteEntity,
                reservaSalvo.getNomeUsuario(),
                mesaEntityList,
                horaChegada
        );
    }

    @Override
    public ReservaEntity atualizarStatusReserva(String nomeUsuario, String horaChegada) {
        Reserva reserva = reservaRepository.findByNomeUsuario(nomeUsuario,
                DateFormat.convertFromStringToLocalDateTime(horaChegada));
        List<ReservaVMesa> reservaVMesas = new ArrayList<>();

        if (reserva == null) {
            throw new RuntimeException("Reserva não encontrada");
        }

        reserva.getReservaVMesaList().forEach(reservaVMesa -> {
            ReservaVMesa reservaMesa = new ReservaVMesa(reservaVMesa.getId(), reserva.getId(),
                    reservaVMesa.getIdMesa(), StatusReserva.CANCELADA);
            reservaVMesas.add(reservaMesa);

        });
        reserva.setReservaVMesaList(reservaVMesas);
        reservaRepository.save(reserva);
        return null;
    }

    @Override
    public void atualizarQtdPessoasReserva(ReservaEntity reservaEntity) {
        RestauranteEntity restauranteEntity = reservaEntity.getRestaurante();

        Restaurante restaurante = new Restaurante(restauranteEntity.getId(), restauranteEntity.getNome(), restauranteEntity.getEndereco().getId(),
                restauranteEntity.getTipoCozinha(), restauranteEntity.getHorarioAbertura(),
                restauranteEntity.getHorarioFechamento(), restauranteEntity.getCapacidade());

        Optional<Reserva> reserva = reservaRepository.findById(reservaEntity.getId());

        if (reserva.isPresent()) {
            Reserva reservaSalva = reserva.get();
            reservaSalva.setReservaVMesaList(reservaEntity.getReservaVMesaList());

            reservaRepository.save(reservaSalva);
        } else {
            throw new RuntimeException("Reserva não encontrada");
        }


    }

    @Override
    public ReservaEntity buscaReservaPeloId(Long id) {
        Optional<Reserva> reservaOptional = reservaRepository.findById(id);

        if (reservaOptional.isPresent()) {
            Reserva reserva = reservaOptional.get();
            Restaurante restaurante = reserva.getRestaurante();
            EnderecoEntity enderecoEntity = enderecoRepositorioJpa.buscarEnderecoPeloId(restaurante.getIdEndereco());
            RestauranteEntity restauranteEntity = new RestauranteEntity(restaurante.getNome(),
                    enderecoEntity, restaurante.getTipo(), restaurante.getHorarioAbertura(),
                    restaurante.getHorarioFechamento(), restaurante.getCapacidade());

            return new ReservaEntity(reserva.getId(), restauranteEntity, reserva.getNomeUsuario(),
                    reserva.getReservaVMesaList(), reserva.getHoraChegada());
        } else {
            throw new RuntimeException("Reserva não encontrada");
        }
    }

    private static Reserva getReserva(Long restauranteId, String nomeUsuario, List<ReservaVMesa> reservaVMesaList,
                                      LocalDateTime horaChegada) {
        Restaurante restaurante = new Restaurante(restauranteId);
        return new Reserva(restaurante, nomeUsuario, reservaVMesaList, horaChegada);
    }

    private static ReservaEntity getReservaEntity(Reserva reserva, RestauranteEntity restauranteEntity,
                                                  LocalDateTime horaChegada) {
        List<ReservaVMesa> reservaVMesas = reserva.getReservaVMesaList();
        return new ReservaEntity(restauranteEntity, reserva.getNomeUsuario(),
                reservaVMesas, horaChegada);
    }
}
