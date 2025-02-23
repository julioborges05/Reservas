package br.com.fiap.reservas.interfaces;

import br.com.fiap.reservas.entities.MesaEntity;
import br.com.fiap.reservas.entities.ReservaEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.enums.StatusMesa;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface IReservaGateway {

    public List<ReservaEntity> buscarReservasPorRestaurante(Long restauranteId);

    ReservaEntity cadastrarReserva(RestauranteEntity restauranteId, String nomeUsuario, MesaEntity mesaEntity);

}
