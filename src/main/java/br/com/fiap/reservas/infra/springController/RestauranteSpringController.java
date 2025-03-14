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

    @GetMapping("/buscar-nome")
    public RestauranteDto buscarRestaurantePorNome(@PathVariable String nome) {
        return buscaRestauranteController.buscarRestaurantePorNome(nome);
    }

    @GetMapping("/buscar-localizacao")
    public RestauranteDto buscarRestaurantePorLocalizacao(@PathVariable String localizacao) {
        return buscaRestauranteController.buscarRestaurantePorLocalizacao(localizacao);
    }

    @GetMapping("/buscar-tipoCozinha")
    public RestauranteDto buscarRestaurantePorTipoCozinha(@PathVariable String tipoCozinha) {
        return buscaRestauranteController.buscarRestaurantePorTipoCozinha(tipoCozinha);
    }

    @GetMapping("/buscar-nome-localizacao-tipoCozinha")
    public RestauranteDto buscarRestaurantePorNomeLocalizacaoETipoCozinha(@PathVariable String nome, @PathVariable String localizacao, @RequestParam String tipoCozinha) {
        return buscaRestauranteController.buscarRestaurantePorNomeLocalizacaoETipoCozinha(nome, localizacao, tipoCozinha);
    }

    @PostMapping
    public RestauranteDto cadastrarRestaurante(@RequestBody RestauranteDto restauranteDto) {
        return cadastrarRestauranteController.cadastrarRestaurante(restauranteDto);
    }
}