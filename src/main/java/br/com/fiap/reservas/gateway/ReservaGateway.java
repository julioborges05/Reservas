package br.com.fiap.reservas.gateway;

import br.com.fiap.reservas.entities.ReservaEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.infra.repository.reserva.ReservaVMesa;
import br.com.fiap.reservas.interfaces.IReservaGateway;

import java.util.List;

public class ReservaGateway implements IReservaGateway {

    private final IReservaGateway reservaDatabaseGateway;

    public ReservaGateway(IReservaGateway reservaDatabaseGateway) {
        this.reservaDatabaseGateway = reservaDatabaseGateway;
    }

    @Override
    public List<ReservaEntity> buscarReservasPorRestaurante(Long restauranteId) {
        return reservaDatabaseGateway.buscarReservasPorRestaurante(restauranteId);
    }

    public ReservaEntity cadastrarReserva(RestauranteEntity restauranteId, String nomeUsuario,
                                          List<ReservaVMesa> reservaVMesaList) {
        return reservaDatabaseGateway.cadastrarReserva(restauranteId, nomeUsuario, reservaVMesaList);
    }

    @Override
    public ReservaEntity atualizarStatusReserva(String nomeUsuario) {
        return reservaDatabaseGateway.atualizarStatusReserva(nomeUsuario);
    }

    @Override
    public void atualizarQtdPessoasReserva(ReservaEntity reservaEntity) {
        reservaDatabaseGateway.atualizarQtdPessoasReserva(reservaEntity);
    }

    @Override
    public ReservaEntity findById(Long id) {
        return reservaDatabaseGateway.findById(id);
    }
}
