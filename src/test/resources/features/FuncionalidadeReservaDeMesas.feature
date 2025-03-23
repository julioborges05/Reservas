# language: pt
Funcionalidade: Reserva de Mesas

  Cenário: Reservar uma mesa
    Quando submeter o registro de uma reserva
    Então a mesa deve ser reservada com sucesso
    E deve retornar a reserva