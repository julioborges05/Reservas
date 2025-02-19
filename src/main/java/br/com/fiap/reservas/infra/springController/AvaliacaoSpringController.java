package br.com.fiap.reservas.infra.springController;

import br.com.fiap.reservas.controller.BuscarAvaliacaoController;
import br.com.fiap.reservas.controller.dto.AvaliacaoDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/avaliacao")
public class AvaliacaoSpringController {

    private final BuscarAvaliacaoController buscarAvaliacaoController;

    public AvaliacaoSpringController(BuscarAvaliacaoController buscarAvaliacaoController) {
        this.buscarAvaliacaoController = buscarAvaliacaoController;
    }


    @GetMapping
    public AvaliacaoDto buscarAvaliacaoPorRestaurante(@RequestBody String nome) {
        return buscarAvaliacaoController.buscarAvaliacaoPorRestaurante(nome);
    }
}
