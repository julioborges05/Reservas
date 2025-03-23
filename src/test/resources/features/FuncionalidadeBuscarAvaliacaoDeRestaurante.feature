# language: pt
Funcionalidade: Buscar avaliacao

  Cenário: Buscar avaliacao
    Dado que existe um restaurante cadastrado
    Dado que existe um usuario cadastrado
    Dado que existe uma avaliacao cadastrada
    Quando submeter a busca de uma avaliacao por id
    Então a avaliacao deve ser encontrada com sucesso
    E deve retornar a avaliacao encontrada