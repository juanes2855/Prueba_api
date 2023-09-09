#language: es

Característica: API de Autenticación

  Antecedentes:
    Dado que la URL base es "http://localhost:8080"

  @ServiciosRest
  Esquema del escenario: Crear un nuevo usuario exitosamente
    Cuando envío una solicitud POST a "crear" con JSON:
      | user_id   | username   | password   | email   |
      | <user_id> | <username> | <password> | <email> |

    Entonces el código de estado de respuesta debe ser 201
    Y la respuesta debe ser un objeto JSON
    Y el objeto JSON debe tener las propiedades:
      | mensaje                         |
      | Usuario registrado exitosamente |

    Ejemplos:
      | user_id | username | password | email               |
      | 1       | johndoe  | P@ssw0rd | johndoe@example.com |


