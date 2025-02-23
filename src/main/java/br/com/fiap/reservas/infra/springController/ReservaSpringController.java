package br.com.fiap.reservas.infra.springController;

import br.com.fiap.reservas.controller.CadastrarReservaController;
import br.com.fiap.reservas.controller.dto.ReservaDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservas")
public class ReservaSpringController {

    private final CadastrarReservaController cadastrarReservaController;

    public ReservaSpringController(CadastrarReservaController cadastrarReservaController) {
        this.cadastrarReservaController = cadastrarReservaController;
    }

    @PostMapping
    public ReservaDto cadastrarReserva(@RequestBody ReservaDto reservaDto) throws Exception {
        return cadastrarReservaController.cadastrarReserva(reservaDto);
    }

}
