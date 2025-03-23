package br.com.fiap.reservas.performance;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class SimulacaoDePerformanceDaApi extends Simulation {

    private final HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080")
            .header("Content-Type", "application/json");

    {
        SimulacaoDePerformanceDaCriacaoDeRestaurante simulacaoDeCriacaoDeRestaurante;
        SimulacaoDePerformanceDaReserva simulacaoDeReserva;
        SimulacaoDePerformanceDaAvaliacao simulacaoDeAvaliacao;
        SimulacaoDePerformanceDaBuscaDeRestaurante simulacaoDeBuscaDeRestaurante;
        try {
            simulacaoDeCriacaoDeRestaurante = new SimulacaoDePerformanceDaCriacaoDeRestaurante();
            simulacaoDeReserva = new SimulacaoDePerformanceDaReserva();
            simulacaoDeBuscaDeRestaurante = new SimulacaoDePerformanceDaBuscaDeRestaurante();
            simulacaoDeAvaliacao = new SimulacaoDePerformanceDaAvaliacao();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        try {
            setUp(
                    simulacaoDeCriacaoDeRestaurante.cenarioDePerformanceParaCadastroDeRestaurante.injectOpen(
                                    rampUsersPerSec(1)
                                            .to(10)
                                            .during(Duration.ofSeconds(10)),
                                    constantUsersPerSec(10)
                                            .during(Duration.ofSeconds(60)),
                                    rampUsersPerSec(10)
                                            .to(1)
                                            .during(Duration.ofSeconds(10))),
                    simulacaoDeAvaliacao.criaCenarioDePerformanceParaCadastroDeAvaliacao().injectOpen(
                                    rampUsersPerSec(1)
                                            .to(10)
                                            .during(Duration.ofSeconds(10)),
                                    constantUsersPerSec(10)
                                            .during(Duration.ofSeconds(60)),
                                    rampUsersPerSec(10)
                                            .to(1)
                                            .during(Duration.ofSeconds(10))),
                    simulacaoDeReserva.criaCenarioDePerformanceParaCadastroDeReserva().injectOpen(
                                    rampUsersPerSec(1)
                                            .to(10)
                                            .during(Duration.ofSeconds(10)),
                                    constantUsersPerSec(10)
                                            .during(Duration.ofSeconds(60)),
                                    rampUsersPerSec(10)
                                            .to(1)
                                            .during(Duration.ofSeconds(10))),
                    simulacaoDeBuscaDeRestaurante.criaCenarioDePerformanceParaBuscaDeRestaurante().injectOpen(
                                    rampUsersPerSec(1)
                                            .to(10)
                                            .during(Duration.ofSeconds(10)),
                                    constantUsersPerSec(10)
                                            .during(Duration.ofSeconds(60)),
                                    rampUsersPerSec(10)
                                            .to(1)
                                            .during(Duration.ofSeconds(10))))
                            .protocols(httpProtocol)
                            .assertions(
                                    global().responseTime().max().lt(3000),
                                    global().failedRequests().count().is(0L)
                            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}