# language: pt
Funcionalidade: Gerenciamento de reservas

    Cenário: Buscar uma reserva
        Dado que existem reservas cadastradas
        Quando buscar uma reserva
        Então a reserva deve ser encontrada com sucesso
        E deve retornar a reserva encontrada

    Cenário: Atualizar o status da reserva
        Dado que existe reserva para o usuário
        Quando atualizar a reserva do usuário