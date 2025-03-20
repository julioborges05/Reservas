package br.com.fiap.reservas.infra.springController;

import br.com.fiap.reservas.controller.AtualizarQtdPessoasReservaController;
import br.com.fiap.reservas.controller.AtualizarStatusReservaController;
import br.com.fiap.reservas.controller.BuscarReservasController;
import br.com.fiap.reservas.controller.CadastrarReservaController;
import br.com.fiap.reservas.controller.dto.BuscarReservasDto;
import br.com.fiap.reservas.controller.dto.DataHoraDto;
import br.com.fiap.reservas.controller.dto.ReservaDto;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaSpringController {

    private final CadastrarReservaController cadastrarReservaController;

    private final BuscarReservasController buscarReservasController;

    private final AtualizarStatusReservaController atualizarStatusReservaController;

    private final AtualizarQtdPessoasReservaController atualizarQtdPessoasReservaController;

    public ReservaSpringController(CadastrarReservaController cadastrarReservaController,
                                   BuscarReservasController buscarReservasController,
                                   AtualizarStatusReservaController atualizarStatusReservaController,
                                   AtualizarQtdPessoasReservaController atualizarQtdPessoasReservaController) {
        this.cadastrarReservaController = cadastrarReservaController;
        this.buscarReservasController = buscarReservasController;
        this.atualizarStatusReservaController = atualizarStatusReservaController;
        this.atualizarQtdPessoasReservaController = atualizarQtdPessoasReservaController;
    }

    @PostMapping()
    public ReservaDto cadastrarReserva(@RequestBody ReservaDto reservaDto) throws Exception {
        return cadastrarReservaController.cadastrarReserva(reservaDto);
    }

    @GetMapping(value = "/{restauranteId}")
    public List<BuscarReservasDto> buscarReservasPorRestauranteId(@PathVariable Long restauranteId) {
        return buscarReservasController.buscarReservasPorRestaurante(restauranteId);
    }

    @PutMapping(value = "/atualiza-status/{nomeUsuario}")
    public void atualizarStatusReserva(@PathVariable String nomeUsuario, @RequestBody DataHoraDto dataHoraDto) {
        atualizarStatusReservaController.atualizarStatusReserva(nomeUsuario, dataHoraDto.horaChegada());
    }

    @PutMapping(value = "/atualiza-qtd-pessoas")
    public void atualizarQtdPessoasReserva(@RequestBody ReservaDto reservaDto) throws Exception {
        atualizarQtdPessoasReservaController.atualizarQtdPessoasReserva(reservaDto);
    }
}
