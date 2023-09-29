#language: es

Característica: API de Autenticación
  Yo como estudiante de Microservicios
  Necesito automatizar el servicio ApiGestionUsuarios
  Para tener realizar pruebas a sus EndPoints

  @ServiciosRest
  Esquema del escenario: Crear un nuevo usuario exitosamente

    Dado me conecto a la api

    Cuando envío una solicitud POST a "crear" con JSON:

    Entonces el código de estado de respuesta debe ser <statusCode>
    Y la respuesta debe ser un objeto JSON
    Y el objeto JSON debe tener las propiedades:
      | <messagekey> | <message> |

    Ejemplos:
      | statusCode | message                         | messagekey |
      | 201        | Usuario registrado exitosamente | message    |


  @ServiciosRest
  Esquema del escenario: Obtener los detalles de un usuario  por user_id exitosamente

    Dado me conecto a la api

    Cuando envío una solicitud GET a "consultar" <user_id>

    Entonces el código de estado de respuesta debe ser <statusCode>

    Y la respuesta debe ser un objeto JSON

    Y el objeto JSON debe tener las propiedades:
      | user_id  | <user_id>  |
      | username | <username> |

    Ejemplos:
      | user_id | username | statusCode |
      | 21      | hectorL  | 200        |
      | 25      | hectorp  | 404        |


  @ServiciosRest
  Esquema del escenario: Ver Usuarios exitosamente

    Dado me conecto a la api

    Cuando envío una solicitud GET para "listar" "page=" <page> "&per_page=" <per_page>

    Entonces el código de estado de respuesta debe ser <statusCode>

    Y la respuesta debe ser un arreglo JSON

    Y el arreglo JSON debe tener al menos 1 elemento

    Ejemplos:
      | page | per_page | statusCode |
      | 1    | 10       | 200        |


  @ServiciosRest
  Esquema del escenario: Iniciar sesión a un usuario exitosamente

    Dado me conecto a la api

    Cuando envío una solicitud POST para "iniciar" con JSON:
      | username | <username> |
      | password | <password> |

    Entonces el código de estado de respuesta debe ser <statusCode>

    Y la respuesta debe ser un objeto JSON

    Y el JSON debe tener al menos un elemento

    Ejemplos:
      | password | username | statusCode |
      | P@ssw0rd | hectorL  | 200        |
      | P@ssw0r  | hectorL  | 401        |


  @ServiciosRest
  Esquema del escenario: Actualizar Datos del Usuario exitosamente

    Dado me conecto a la api

    Cuando envío una solicitud PUT a "actualizar" <user_id> con JSON:
      | username | <username> |
      | password | <password> |
      | email    | <email>    |

    Entonces el código de estado de respuesta debe ser <statusCode>
    Y la respuesta debe ser un objeto JSON
    Y el objeto JSON debe tener las propiedades:
      | <messagekey> | <message> |


    Ejemplos:
      | user_id | username | password | email                 | message                                     | statusCode | messagekey |
      | 21      | hectorL  | 1        | juanes13916@gmail.com | Usuario actualizado exitosamente            | 200        | message    |
      | 53      | hectorL  | 1        | juanes13916@gmail.com | No tienes permiso para realizar esta acción | 403        | error      |

  @ServiciosRest
  Esquema del escenario: Solicitud de recuperación de contraseña exitosamente
    Dado me conecto a la api
    Cuando envío una solicitud POST a envio de "emails" con JSON:
      | username | <username> |
    Entonces el código de estado de respuesta debe ser <statusCode>
    Y la respuesta debe ser un objeto JSON
    Y el objeto JSON debe tener las propiedades:
      | <messagekey> | <message> |

    Ejemplos:
      | username   | message                                                                | statusCode | messagekey |
      | hectorL    | Correo electrónico de recuperación de contraseña enviado correctamente | 200        | message    |
      | hectorLavo | Usuario no encontrado                                                  | 404        | error      |


  @ServiciosRest
  Esquema del escenario: Cambiar Contraseña exitosamente
    Dado me conecto a la api
    Cuando envío una solicitud PATCH a "cambio" <user_id> con JSON:
      | new_password | <new_password> |

    Entonces el código de estado de respuesta debe ser <statusCode>
    Y la respuesta debe ser un objeto JSON
    Y el objeto JSON debe tener las propiedades:
      | <messagekey> | <message> |


    Ejemplos:
      | user_id | new_password | message                                     | statusCode | messagekey |
      | 21      | nueva        | Contraseña actualizada exitosamente         | 200        | message    |
      | 11      | nueva        | No tienes permiso para realizar esta acción | 403        | error      |

  @ServiciosRest
  Esquema del escenario: Eliminar Usuario exitosamente
    Dado me conecto a la api
    Cuando envío una solicitud DELETE a "eliminar" <user_id> con JSON
    Entonces el código de estado de respuesta debe ser <statusCode>
    Y la respuesta debe ser un objeto JSON
    Y el objeto JSON debe tener las propiedades:
      | <messagekey> | <message> |

    Ejemplos:
      | user_id | message                                     | statusCode | messagekey |
      | 21      | Usuario eliminado exitosamente              | 200        | message    |
      | 11      | No tienes permiso para realizar esta acción | 403        | error      |

