# language: pt
Funcionalidade: Cadastro de restaurante

  Cenário: Cadastrar um restaurante
    Quando submeter o registro de um restaurante
    Então o restaurante deve ser cadastrado com sucesso
    E deve retornar o restaurante criado