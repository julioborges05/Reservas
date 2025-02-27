package br.com.fiap.reservas.infra.springController;

import br.com.fiap.reservas.controller.AtualizarStatusReservaController;
import br.com.fiap.reservas.controller.BuscarReservasController;
import br.com.fiap.reservas.controller.CadastrarReservaController;
import br.com.fiap.reservas.controller.dto.BuscarReservasDto;
import br.com.fiap.reservas.controller.dto.ReservaDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaSpringController {

    private final CadastrarReservaController cadastrarReservaController;

    private final BuscarReservasController buscarReservasController;

    private final AtualizarStatusReservaController atualizarStatusReservaController;

    public ReservaSpringController(CadastrarReservaController cadastrarReservaController,
                                   BuscarReservasController buscarReservasController,
                                   AtualizarStatusReservaController atualizarStatusReservaController) {
        this.cadastrarReservaController = cadastrarReservaController;
        this.buscarReservasController = buscarReservasController;
        this.atualizarStatusReservaController = atualizarStatusReservaController;
    }

    @PostMapping
    public ReservaDto cadastrarReserva(@RequestBody ReservaDto reservaDto) throws Exception {
        return cadastrarReservaController.cadastrarReserva(reservaDto);
    }

    @GetMapping(value = "/{restauranteId}")
    public List<BuscarReservasDto> buscarReservasPorRestauranteId(@PathVariable Long restauranteId) throws Exception {
        return buscarReservasController.buscarReservasPorRestaurante(restauranteId);
    }

    @PutMapping(value = "/atualiza-status/{nomeUsuario}")
    public void atualizarStatusReserva(@RequestParam String nomeUsuario) {
        atualizarStatusReservaController.atualizarStatusReserva(nomeUsuario);
    }

    @PutMapping(value = "atualiza-qtd-pessoas")
    public void atualizarQtdPessoasReserva(@RequestBody ReservaDto reservaDto) {

    }
}
