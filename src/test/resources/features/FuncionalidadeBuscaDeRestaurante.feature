# language: pt

Funcionalidade: Busca de restaurante

  Cenário: Buscar um restaurante
    Dado que existem restaurantes cadastrados
    Quando buscar um restaurante
    Então o restaurante deve ser encontrado com sucesso
    E deve retornar o restaurante encontrado