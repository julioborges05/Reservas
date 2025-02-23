package br.com.fiap.reservas.interfaces;

import br.com.fiap.reservas.entities.MesaEntity;
import br.com.fiap.reservas.infra.repository.mesa.Mesa;

import java.util.List;

public interface IMesaGateway {

    List<MesaEntity> buscarMesasLivresPorRestaurante(Long restauranteId);

    void atualizarReservaMesa(Mesa mesa);

}
