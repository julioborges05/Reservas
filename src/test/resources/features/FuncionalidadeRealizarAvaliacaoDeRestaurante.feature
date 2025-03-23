# language: pt
Funcionalidade: Realizar avaliacao de um restaurante

  Cenário: Avaliar um restaurante
    Dado que existe restaurante cadastrado
    Dado que existe usuario cadastrado
    Quando submeter o formulario para avaliacao de um restaurante
    Então a avaliacao deve ser registrada com sucesso
    E deve retornar a avaliacao criada