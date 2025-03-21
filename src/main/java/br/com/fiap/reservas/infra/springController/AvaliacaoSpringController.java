package br.com.fiap.reservas.infra.springController;

import br.com.fiap.reservas.controller.BuscarAvaliacaoController;
import br.com.fiap.reservas.controller.RealizarAvaliacaoController;
import br.com.fiap.reservas.controller.dto.AvaliacaoDto;
import br.com.fiap.reservas.entities.AvaliacaoEntity;
import br.com.fiap.reservas.interfaces.IAvaliacaoGateway;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoSpringController {

    private final RealizarAvaliacaoController realizarAvaliacaoController;
    private final BuscarAvaliacaoController buscarAvaliacaoController;

    public AvaliacaoSpringController(BuscarAvaliacaoController buscarAvaliacaoController, RealizarAvaliacaoController realizarAvaliacaoController) {
        this.buscarAvaliacaoController = buscarAvaliacaoController;
        this.realizarAvaliacaoController = realizarAvaliacaoController;
    }

    @PostMapping
    public AvaliacaoEntity avaliarRestaurante(@RequestBody AvaliacaoDto avaliacaoDto) {
        return realizarAvaliacaoController.realizarAvaliacao(avaliacaoDto);
    }

    @GetMapping("/avaliacao-por-restaurante/{id}")
    public AvaliacaoDto buscarAvaliacaoPorRestaurante(@PathVariable Long id) {
        return buscarAvaliacaoController.buscarAvaliacaoPorRestaurante(id);
    }
}
