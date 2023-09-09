#language: es

Característica: API de Autenticación

  @ServiciosRest
  Esquema del escenario: Crear un nuevo usuario exitosamente

    Dado me conecto a la api

    Cuando envío una solicitud POST a "crear" con JSON:
      | user_id   | username   | password   | email   |
      | <user_id> | <username> | <password> | <email> |

    Entonces el código de estado de respuesta debe ser 201

    Y la respuesta debe ser un objeto JSON

    Y valida que el mensaje de error sea "Usuario registrado exitosamente"

    Ejemplos:
      | user_id | username | password | email               |
      | 21      | hectorL  | P@ssw0rd | hectorl@example.com |

  @ServiciosRest
  Esquema del escenario: Obtener los detalles de un usuario  por user_id exitosamente

    Dado me conecto a la api

    Cuando envío una solicitud GET a "consultar" <user_id>

    Entonces el código de estado de respuesta debe ser 200

    Y la respuesta debe ser un objeto JSON

    Y el objeto JSON debe tener las propiedades:
      | user_id   |  <user_id> |
      | username  | <username> |

    Ejemplos:
      | user_id | username |
      | 21      | hectorL  |
