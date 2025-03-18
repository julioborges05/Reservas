package br.com.fiap.reservas.infra.gateway;

import br.com.fiap.reservas.entities.MesaEntity;
import br.com.fiap.reservas.enums.StatusMesa;
import br.com.fiap.reservas.infra.repository.endereco.Endereco;
import br.com.fiap.reservas.infra.repository.endereco.EnderecoRepository;
import br.com.fiap.reservas.infra.repository.mesa.Mesa;
import br.com.fiap.reservas.infra.repository.mesa.MesaPK;
import br.com.fiap.reservas.infra.repository.mesa.MesaRepository;
import br.com.fiap.reservas.infra.repository.restaurante.Restaurante;
import br.com.fiap.reservas.infra.repository.restaurante.RestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MesaRepositorioJpaTest {

    private MesaRepositorioJpa mesaRepositorioJpa;

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private RestauranteRepository restaurantRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    private Restaurante restauranteSalvo;

    @BeforeEach
    void setUp() {
        mesaRepositorioJpa = new MesaRepositorioJpa(mesaRepository);

        Endereco endereco = new Endereco("cep", "logradouro", "bairro", "cidade", "numero", "complemento");
        Endereco enderecoSalvo = enderecoRepository.save(endereco);

        Restaurante restaurante = new Restaurante("Nome", enderecoSalvo.getId(), "Tipo", null,
                null, 10);
        restauranteSalvo = restaurantRepository.save(restaurante);
    }

    @Test
    void buscarMesasLivresPorRestaurante() {
        MesaPK mesaPK = new MesaPK();
        mesaPK.setRestauranteId(restauranteSalvo.getId());
        mesaPK.setNumeroMesa(1);

        Mesa mesa = new Mesa();
        mesa.setStatusMesa(StatusMesa.LIVRE);
        mesa.setRestaurante(restauranteSalvo);
        mesa.setId(mesaPK);

        mesaRepository.save(mesa);

        List<MesaEntity> resultado = mesaRepositorioJpa.buscarMesasLivresPorRestaurante(restauranteSalvo.getId());

        assert resultado.size() == 1;
        MesaEntity mesaEntity = resultado.get(0);

        assertEquals(restauranteSalvo.getId(), mesaEntity.getRestauranteId());
        assertEquals(StatusMesa.LIVRE, mesaEntity.getStatusMesa());
    }

    @Test
    void atualizarReservaMesaComSucesso() {
        MesaPK mesaPK = new MesaPK();
        mesaPK.setRestauranteId(restauranteSalvo.getId());
        mesaPK.setNumeroMesa(2);

        Mesa mesa = new Mesa();
        mesa.setStatusMesa(StatusMesa.LIVRE);
        mesa.setRestaurante(restauranteSalvo);
        mesa.setId(mesaPK);

        mesaRepository.save(mesa);

        mesa.setStatusMesa(StatusMesa.OCUPADA);
        mesaRepositorioJpa.atualizarReservaMesa(mesa);

        List<MesaEntity> resultado = mesaRepositorioJpa.buscarMesasPorRestaurante(restauranteSalvo.getId());

        MesaEntity mesaParaConsulta = resultado.stream().filter(m -> m.getNumero() == 2).findFirst().orElseThrow();
        assertEquals(StatusMesa.OCUPADA, mesaParaConsulta.getStatusMesa());
    }

    @Test
    void atualizarReservaMesaComNumeroErrado() {
        MesaPK mesaPK = new MesaPK();
        mesaPK.setRestauranteId(restauranteSalvo.getId());
        mesaPK.setNumeroMesa(3);

        Mesa mesa = new Mesa();
        mesa.setStatusMesa(StatusMesa.LIVRE);
        mesa.setRestaurante(restauranteSalvo);
        mesa.setId(mesaPK);

        mesa.setStatusMesa(StatusMesa.OCUPADA);

        assertThrows(
                IllegalArgumentException.class,
                () -> mesaRepositorioJpa.atualizarReservaMesa(mesa),
                "Mesa não encontrada"
        );
    }

}