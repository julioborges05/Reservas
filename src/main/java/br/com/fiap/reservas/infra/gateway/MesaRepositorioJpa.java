package br.com.fiap.reservas.infra.gateway;

import br.com.fiap.reservas.entities.MesaEntity;
import br.com.fiap.reservas.enums.StatusMesa;
import br.com.fiap.reservas.enums.StatusReserva;
import br.com.fiap.reservas.infra.repository.mesa.Mesa;
import br.com.fiap.reservas.infra.repository.mesa.MesaRepository;
import br.com.fiap.reservas.infra.repository.reserva.ReservaVMesa;
import br.com.fiap.reservas.interfaces.IMesaGateway;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MesaRepositorioJpa implements IMesaGateway {

    private final MesaRepository mesaRepository;

    public MesaRepositorioJpa(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }

    @Override
    public List<MesaEntity> buscarMesasLivresPorRestaurante(Long restauranteId) {
        List<Mesa> mesas = mesaRepository.findMesasByRestauranteAndStatus(
                restauranteId, StatusMesa.LIVRE);

        return converterMesaParaMesaEntity(mesas);
    }

    @Override
    public List<MesaEntity> buscarMesasPorRestaurante(Long restauranteId) {
        List<Mesa> mesas = mesaRepository.findMesasByRestaurante(
                restauranteId);

        return converterMesaParaMesaEntity(mesas);
    }

    private List<MesaEntity> converterMesaParaMesaEntity(List<Mesa> mesas) {
        List<MesaEntity> mesasEntityList = new ArrayList<>();
        mesas.forEach(mesa -> {
            MesaEntity mesaEntityList = new MesaEntity(mesa.getRestaurante().getId(), mesa.getNumero(), mesa.getStatusMesa());

            mesasEntityList.add(mesaEntityList);
        });
        return mesasEntityList;
    }

    @Override
    public void atualizarReservaMesa(Mesa mesa) {
        Optional<Mesa> mesaOptional = this.mesaRepository.findByRestauranteIdAndNumero(mesa.getId().getRestauranteId(),
                mesa.getId().getNumeroMesa());

        if (mesaOptional.isPresent()) {
            Mesa mesaSalva = mesaOptional.get();
            mesaSalva.setStatusMesa(mesa.getStatusMesa());
            this.mesaRepository.save(mesaSalva);
            return;
        }

        throw new IllegalArgumentException("Mesa não encontrada");
    }
}
