#language: es

Característica: API de Autenticación

  Antecedentes:
    Dado que la URL base es "http://localhost:8080"

  Esquema del escenario: Crear un nuevo usuario exitosamente
    Cuando envío una solicitud POST a "crear" con JSON:
      | user_id  | <user_id>  |
      | username | <username> |
      | password | <password> |
      | email    | <email>    |
    Entonces el código de estado de respuesta debe ser 201
    Y la respuesta debe ser un objeto JSON
    Y el objeto JSON debe tener las propiedades:
      | mensaje                         |
      | Usuario registrado exitosamente |

    Ejemplos:
      | user_id | username | password | email               |
      | 1       | johndoe  | P@ssw0rd | johndoe@example.com |


  Esquema del escenario: Ver Usuarios exitosamente
    Cuando envío una solicitud GET a "/users?page=1&per_page=10"
    Entonces el código de estado de respuesta debe ser 200
    Y la respuesta debe ser un arreglo JSON
    Y el arreglo JSON debe tener al menos 1 elemento
    Y cada elemento en el arreglo JSON debe tener las propiedades:
      | user_id  | <user_id>  |
      | username | <username> |
      | email    | <email>    |

    Ejemplos:
      | user_id | username | email               |
      | 1       | johndoe  | johndoe@example.com |


  Esquema del escenario: Iniciar sesión a un usuario exitosamente
    Cuando envío una solicitud POST a "/tokens" con JSON:
"""
      {
        "username": "johndoe",
        "password": "P@ssw0rd"
      }
      """
    Entonces el código de estado de respuesta debe ser 200
    Y la respuesta debe ser un objeto JSON
    Y el objeto JSON debe tener las propiedades:
      | token |

  Esquema del escenario: Obtener los detalles de un usuario exitosamente
    Cuando envío una solicitud GET a "/users/1"
    Entonces el código de estado de respuesta debe ser 200
    Y la respuesta debe ser un objeto JSON
    Y el objeto JSON debe tener las propiedades:
      | user_id | username | email               |
      | 1       | johndoe  | johndoe@example.com |

  Esquema del escenario: Actualizar Datos del Usuario exitosamente
    Cuando envío una solicitud PUT a "/users/1" con JSON:
"""
      {
        "username": "nuevonombredeusuario",
        "password": "nuevacontraseña",
        "email": "nuevoemail@example.com"
      }
      """
    Entonces el código de estado de respuesta debe ser 200
    Y la respuesta debe ser un objeto JSON
    Y el objeto JSON debe tener las propiedades:
      | mensaje                          |
      | Usuario actualizado exitosamente |

  Esquema del escenario: Cambiar Contraseña exitosamente
    Cuando envío una solicitud PATCH a "/users/1" con JSON:
"""
      {
        "new_password": "nuevacontraseña"
      }
      """
    Entonces el código de estado de respuesta debe ser 200
    Y la respuesta debe ser un objeto JSON
    Y el objeto JSON debe tener las propiedades:
      | mensaje                             |
      | Contraseña actualizada exitosamente |

  Esquema del escenario: Eliminar Usuario exitosamente
    Cuando envío una solicitud DELETE a "/users/1"
    Entonces el código de estado de respuesta debe ser 200
    Y la respuesta debe ser un objeto JSON
    Y el objeto JSON debe tener las propiedades:
      | mensaje                        |
      | Usuario eliminado exitosamente |

  Esquema del escenario: Solicitud de recuperación de contraseña exitosamente
    Cuando envío una solicitud POST a "/emails" con JSON:
"""
      {
        "username": "johndoe"
      }
      """
    Entonces el código de estado de respuesta debe ser 200
    Y la respuesta debe ser un objeto JSON
    Y el objeto JSON debe tener las propiedades:
      | mensaje                                         |
      | Solicitud de recuperación de contraseña exitosa |
