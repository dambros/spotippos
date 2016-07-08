# VivaReal - Desafio backend

Implementação do desafio backend proposto [aqui](https://github.com/VivaReal/code-challenge/blob/master/backend.md).

### Requisitos

- Java 1.8
- Maven
- Git

### Instalação e build

Para rodar o projeto é simples, pois estou utilizando Spring Boot que já contém um Tomcat embedded:

1- Clone o repositório
```
$ git clone git@github.com:dambros/spotippos.git
```

2- Execute 
```
$ cd spotippos
$ mvn spring-boot:run
```

O servidor estará acessível em ```localhost:8080```. Caso deseje utilizar alguma porta diferente de ```8080```, acrescente ```-Dserver.port=XXXX``` ao comando anterior:

```
$ mvn spring-boot:run -Dserver.port=8090
```

### Endpoints

**1- Criação de imóveis**

[Regras de negócio](https://github.com/VivaReal/code-challenge/blob/master/backend.md#desafio)
```
POST /properties
```
Body 
```json
{
  "x": 700,
  "y": 400,
  "title": "Imóvel código 2, com 5 quartos e 4 banheiros",
  "price": 150000,
  "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
  "beds": 1,
  "baths": 1,
  "squareMeters": 201
}
```

Response
```json
{
  "id": 1,
  "x": 700,
  "y": 400,
  "beds": 1,
  "baths": 1,
  "squareMeters": 201,
  "price": 150000,
  "title": "Imóvel código 2, com 5 quartos e 4 banheiros",
  "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
  "provinces": [
    "Groola"
  ]
}
```

**2- Busca de imóvel específico**

```
 GET /properties/{id}
 ```
 
 Response
 ```json
 {
  "id": 1,
  "x": 700,
  "y": 400,
  "beds": 1,
  "baths": 1,
  "squareMeters": 201,
  "price": 150000,
  "title": "Imóvel código 2, com 5 quartos e 4 banheiros",
  "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
  "provinces": [
    "Groola"
  ]
}
 ```
 
 **3- Busca de imóveis por range**
 
 [Regras de negócio](https://github.com/VivaReal/code-challenge/blob/master/backend.md#3-busque-imóveis-em-spotippos-d)
 
 ```
  GET /properties?ax={integer}&ay={integer}&bx={integer}&by={integer}
  ```
 
 Response
  ```
  {
  "foundProperties": 1,
  "properties": [
    {
      "id": 1,
      "x": 700,
      "y": 400,
      "beds": 1,
      "baths": 1,
      "squareMeters": 201,
      "price": 150000,
      "title": "Imóvel código 2, com 5 quartos e 4 banheiros",
      "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
      "provinces": [
        "Groola"
      ]
    }
  ]
}
  ```

### Observações

O desafio referencia este [json](https://github.com/VivaReal/code-challenge/blob/master/properties.json) mas em nenhum lugar descreve se é necessário fazer algo com ele, até mesmo porque ele vai contra as regras de negócio propostas no ítem **1. Crie imóveis em Spotippos**, onde diz que todos os campos fornecidos são obrigatório e nesse json os objetos não contém todos os campos mencionados. Sendo assim, assumi que essa listagem era apenas um elemento adicional caso eu quisesse recriar o mapa de Spotippos com todos os 4 mil imóveis.
