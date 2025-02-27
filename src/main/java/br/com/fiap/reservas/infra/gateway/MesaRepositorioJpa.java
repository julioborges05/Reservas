package br.com.fiap.reservas.infra.gateway;

import br.com.fiap.reservas.entities.MesaEntity;
import br.com.fiap.reservas.enums.StatusMesa;
import br.com.fiap.reservas.enums.StatusReserva;
import br.com.fiap.reservas.infra.repository.mesa.Mesa;
import br.com.fiap.reservas.infra.repository.mesa.MesaRepository;
import br.com.fiap.reservas.interfaces.IMesaGateway;

import java.util.ArrayList;
import java.util.List;

public class MesaRepositorioJpa implements IMesaGateway {

    private final MesaRepository mesaRepository;

    public MesaRepositorioJpa(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }

    @Override
    public List<MesaEntity> buscarMesasLivresPorRestaurante(Long restauranteId) {
        List<MesaEntity> mesasEntityList = new ArrayList<>();
        List<Mesa> mesas = mesaRepository.findMesasByRestauranteAndStatus(
                restauranteId, StatusMesa.LIVRE);

        mesas.forEach(mesa -> {
            MesaEntity mesaEntityList = new MesaEntity(mesa.getId(), mesa.getNumero(), mesa.getStatusMesa());

            mesasEntityList.add(mesaEntityList);
        });
        return mesasEntityList;
    }

    @Override
    public void atualizarReservaMesa(Mesa mesa) {
        this.mesaRepository.atualizarStatus(mesa.getId(), mesa.getStatusMesa());
    }

    @Override
    public void save(Mesa mesa) {
        this.mesaRepository.save(mesa);
    }
}
