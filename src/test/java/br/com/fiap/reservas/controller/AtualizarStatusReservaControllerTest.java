package br.com.fiap.reservas.controller;

import br.com.fiap.reservas.interfaces.IReservaGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class AtualizarStatusReservaControllerTest {

    @Mock
    private IReservaGateway reservaGateway;

    private AtualizarStatusReservaController atualizarStatusReservaController;

    @BeforeEach
    void setUp() {
        try (AutoCloseable ignored = MockitoAnnotations.openMocks(this)) {
            atualizarStatusReservaController = new AtualizarStatusReservaController(
                    reservaGateway);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void validaAtualizarQtdPessoasComSucesso() {
        atualizarStatusReservaController.atualizarStatusReserva("Usuario", "28/02/2025 10:25");

        verify(reservaGateway, times(1)).atualizarStatusReserva(any(), any());
    }
}
