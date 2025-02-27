package br.com.fiap.reservas.interfaces;

import br.com.fiap.reservas.entities.ReservaEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.infra.repository.reserva.ReservaVMesa;

import java.util.List;

public interface IReservaGateway {

    public List<ReservaEntity> buscarReservasPorRestaurante(Long restauranteId);

    ReservaEntity cadastrarReserva(RestauranteEntity restauranteId, String nomeUsuario,
                                   List<ReservaVMesa> reservaVMesaList);

    ReservaEntity atualizarStatusReserva(String nomeUsuario);

    void atualizarQtdPessoasReserva(ReservaEntity reservaEntity);

    ReservaEntity findById(Long id);

}
