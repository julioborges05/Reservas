package br.com.fiap.reservas.infra.springController;

import br.com.fiap.reservas.controller.BuscaRestauranteController;
import br.com.fiap.reservas.controller.dto.RestauranteDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurante")
public class RestauranteSpringController {

    private final BuscaRestauranteController buscaRestauranteController;

    public RestauranteSpringController(BuscaRestauranteController buscaRestauranteController) {
        this.buscaRestauranteController = buscaRestauranteController;
    }

    @GetMapping
    public RestauranteDto buscarRestaurantePorNomeEnderecoETipo() {
        return buscaRestauranteController.buscarRestaurantePorNomeEnderecoETipo("abc", "def", "ghi");
    }

}
