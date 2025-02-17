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
    public RestauranteDto buscarRestaurantePorNomeEnderecoETipo() {
        return buscaRestauranteController.buscarRestaurantePorNomeEnderecoETipo("abc", "def", "ghi");
    }

    @PostMapping
    public RestauranteDto cadastrarRestaurante(@RequestBody RestauranteDto restauranteDto) {
        return cadastrarRestauranteController.cadastrarRestaurante(restauranteDto);
    }

}
