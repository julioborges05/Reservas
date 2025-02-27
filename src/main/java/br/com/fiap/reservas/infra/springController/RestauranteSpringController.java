package br.com.fiap.reservas.infra.springController;

import br.com.fiap.reservas.controller.BuscaRestauranteController;
import br.com.fiap.reservas.controller.CadastrarRestauranteController;
import br.com.fiap.reservas.controller.dto.RestauranteDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurante")
public class RestauranteSpringController {

    private final BuscaRestauranteController buscaRestauranteController;
    private final CadastrarRestauranteController cadastrarRestauranteController;

    public RestauranteSpringController(BuscaRestauranteController buscaRestauranteController,
                                       CadastrarRestauranteController cadastrarRestauranteController) {
        this.buscaRestauranteController = buscaRestauranteController;
        this.cadastrarRestauranteController = cadastrarRestauranteController;
    }

    @GetMapping
    public RestauranteDto buscarRestaurantePorNome() {
        return buscaRestauranteController.buscarRestaurantePorNome("nome");
    }

    @GetMapping
    public RestauranteDto buscarRestaurantePorLocalizacao() {
        return buscaRestauranteController.buscarRestaurantePorLocalizacao("localizacao");
    }

    @GetMapping
    public RestauranteDto buscarRestaurantePorTipoCozinha() {
        return buscaRestauranteController.buscarRestaurantePorTipoCozinha("tipoCozinha");
    }

    @GetMapping
    public RestauranteDto buscarRestaurantePorNomeLocalizacaoETipoCozinha() {
        return buscaRestauranteController.buscarRestaurantePorNomeLocalizacaoETipoCozinha("nome", "localizacao", "tipoCozinha");
    }

    @PostMapping
    public RestauranteDto cadastrarRestaurante(@RequestBody RestauranteDto restauranteDto) {
        return cadastrarRestauranteController.cadastrarRestaurante(restauranteDto);
    }

}
