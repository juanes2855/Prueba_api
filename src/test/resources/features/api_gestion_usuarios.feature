#language: es

Característica: API de Autenticación
  Yo como estudiante de Microservicios
  Necesito automatizar el servicio ApiGestionUsuarios
  Para tener realizar pruebas a sus EndPoints

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
      | user_id  | <user_id>  |
      | username | <username> |

    Ejemplos:
      | user_id | username |
      | 21      | hectorL  |


  @ServiciosRest
  Esquema del escenario: Ver Usuarios exitosamente

    Dado me conecto a la api

    Cuando envío una solicitud GET para "listar" "page=" <page> "&per_page=" <per_page>

    Entonces el código de estado de respuesta debe ser 200

    Y la respuesta debe ser un arreglo JSON

    Y el arreglo JSON debe tener al menos 1 elemento

    Ejemplos:
      | page | per_page |
      | 1    | 10       |


  @ServiciosRest
  Esquema del escenario: Iniciar sesión a un usuario exitosamente

    Dado me conecto a la api

    Cuando envío una solicitud POST para "iniciar" con JSON:
      | username | <username> |
      | password | <password> |

    Entonces el código de estado de respuesta debe ser 200

    Y la respuesta debe ser un objeto JSON

    Y el JSON debe tener al menos un elemento

    Ejemplos:
      | password | username | token  |
      | P@ssw0rd | hectorL  | token1 |


  @ServiciosRest
  Esquema del escenario: Actualizar Datos del Usuario exitosamente

    Dado me conecto a la api

    Cuando envío una solicitud PUT a "actualizar" <user_id> con JSON:
      | username | <username> |
      | password | <password> |
      | email    | <email>    |

    Entonces el código de estado de respuesta debe ser 200
    Y la respuesta debe ser un objeto JSON
    Y el objeto JSON debe tener las propiedades:
      | message | <message> |


    Ejemplos:
      | user_id | username | password | email               | message                          |
      | 21      | hectorL  | 1        | hectorl@example.com | Usuario actualizado exitosamente |

  @ServiciosRest
  Esquema del escenario: Cambiar Contraseña exitosamente
    Dado me conecto a la api
    Cuando envío una solicitud PATCH a "cambio" <user_id> con JSON:
      | new_password | <new_password> |

    Entonces el código de estado de respuesta debe ser 200
    Y la respuesta debe ser un objeto JSON
    Y el objeto JSON debe tener las propiedades:
      | message | <message> |


    Ejemplos:
      | user_id | new_password | message                             |
      | 21      | nueva        | Contraseña actualizada exitosamente |

  @ServiciosRest
  Esquema del escenario: Eliminar Usuario exitosamente
    Dado me conecto a la api
    Cuando envío una solicitud DELETE a "eliminar" <user_id> con JSON
    Entonces el código de estado de respuesta debe ser 200
    Y la respuesta debe ser un objeto JSON
    Y el objeto JSON debe tener las propiedades:
      | message | <message> |

    Ejemplos:
      | user_id | message                        |
      | 21      | Usuario eliminado exitosamente |


  Esquema del escenario: Solicitud de recuperación de contraseña exitosamente
    Dado me conecto a la api
    Cuando envío una solicitud POST a "/emails" con JSON:
      | username | <username> |
    Entonces el código de estado de respuesta debe ser 200
    Y la respuesta debe ser un objeto JSON
    Y el objeto JSON debe tener las propiedades:
      | message | <message> |

    Ejemplos:
      | username | message                                         |
      | hectorL  | Solicitud de recuperación de contraseña exitosa |