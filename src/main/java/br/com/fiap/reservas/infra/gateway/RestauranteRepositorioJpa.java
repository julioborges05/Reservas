package br.com.fiap.reservas.infra.gateway;

import br.com.fiap.reservas.entities.EnderecoEntity;
import br.com.fiap.reservas.entities.MesaEntity;
import br.com.fiap.reservas.entities.RestauranteEntity;
import br.com.fiap.reservas.enums.StatusMesa;
import br.com.fiap.reservas.enums.StatusReserva;
import br.com.fiap.reservas.infra.repository.mesa.Mesa;
import br.com.fiap.reservas.infra.repository.mesa.MesaPK;
import br.com.fiap.reservas.infra.repository.mesa.MesaRepository;
import br.com.fiap.reservas.infra.repository.restaurante.Restaurante;
import br.com.fiap.reservas.infra.repository.restaurante.RestauranteRepository;
import br.com.fiap.reservas.interfaces.IRestauranteGateway;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RestauranteRepositorioJpa implements IRestauranteGateway {

    private final RestauranteRepository restauranteRepository;

    @Autowired
    private MesaRepository mesaRepository;

    public RestauranteRepositorioJpa(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    @Override
    public RestauranteEntity buscarRestaurantePorNomeELocalizacaoETipo(String nome, String endereco, String tipo) {
        Restaurante restaurante = restauranteRepository.findByNomeAndEnderecoAndTipo(nome, endereco, tipo);

        EnderecoEntity enderecoEntity = new EnderecoEntity("123", "456", "789", "101112",
                "123", "123");

        return new RestauranteEntity(restaurante.getNome(), enderecoEntity, restaurante.getTipo(), LocalTime.now(),
                LocalTime.now(), 10, new ArrayList<>());
    }

    @Override
    public RestauranteEntity cadastrarRestaurante(RestauranteEntity restauranteEntity) {
        List<Mesa> mesas = new ArrayList<>();

        Restaurante restaurante = new Restaurante(
                restauranteEntity.getNome(),
                restauranteEntity.getEndereco().getId(),
                restauranteEntity.getTipoCozinha(),
                restauranteEntity.getHorarioAbertura(),
                restauranteEntity.getHorarioFechamento(),
                restauranteEntity.getCapacidade());

        Restaurante restauranteSalvoSemMesas = restauranteRepository.save(restaurante);

        for (int i = 0; i < restauranteEntity.getListaMesa().size() ; i++) {
            Mesa mesaParaSalvar = new Mesa(new MesaPK(restauranteSalvoSemMesas.getId(), i+1),
                    restaurante, StatusMesa.LIVRE);
            mesas.add(mesaParaSalvar);
        }

        mesaRepository.saveAll(mesas);

        restaurante.setMesas(mesas);

        Restaurante restauranteSalvo = restauranteRepository.save(restaurante);

        List<MesaEntity> mesaEntityList = new ArrayList<>();
        restauranteSalvo.getMesas().forEach(mesa -> {
            MesaPK mesaPK = new MesaPK();
            mesaPK.setNumeroMesa(mesa.getNumero());
            mesaPK.setRestauranteId(restauranteSalvo.getId());
            MesaEntity mesaEntity = new MesaEntity(mesaPK, mesa.getNumero(),
                    StatusMesa.LIVRE);
            mesaEntityList.add(mesaEntity);
        });

        return new RestauranteEntity(
                restauranteSalvo.getId(),
                restauranteSalvo.getNome(),
                restauranteEntity.getEndereco(),
                restauranteSalvo.getTipo(),
                restauranteSalvo.getHorarioAbertura(),
                restauranteSalvo.getHorarioFechamento(),
                restauranteSalvo.getCapacidade(),
                mesaEntityList
        );
    }

    @Override
    public RestauranteEntity findById(Long id) throws Exception {
        Optional<Restaurante> restauranteOptional = restauranteRepository.findById(id);
        // Ainda falta mexer
        EnderecoEntity enderecoEntity = new EnderecoEntity("123", "456", "789", "101112",
                "123", "123");

        if (restauranteOptional.isPresent()) {
            Restaurante restauranteSalvo = restauranteOptional.get();
            RestauranteEntity restauranteEntity = new RestauranteEntity(
                    restauranteSalvo.getNome(),
                    enderecoEntity,
                    restauranteSalvo.getTipo(),
                    restauranteSalvo.getHorarioAbertura(),
                    restauranteSalvo.getHorarioFechamento(),
                    restauranteSalvo.getCapacidade(),
                    new ArrayList<>()
            );

            return restauranteEntity;
        } else {
            throw new Exception("Restaurante não encontrado");
        }

    }
}
