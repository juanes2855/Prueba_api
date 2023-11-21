#language: es

Característica: API PROFILES
  Yo, como estudiante de Microservicios
  Quiero automatizar las pruebas del servicio ApiGestionPerfiles
  Para asegurarme de que sus EndPoints funcionen correctamente


  @ServiciosRest
  Esquema del escenario: Consultar el perfil de un usuario

    Dado que estoy conectado a la API con los datos necesarios para la solicitud
    Cuando envío una solicitud para obtener la informacion del perfil a "consultarPerfil" con <user_id>
    Entonces el código de estado de respuesta debe ser <statusCode>

    Y la respuesta debe ser un objeto JSON

    Y el objeto JSON debe tener las propiedades:
      | user_id  | <user_id>  |
      | username | <username> |

    Ejemplos:
      | user_id | username | statusCode |
      | 21      | hectorL  | 201        |
      | 2501    | hectorp  | 404        |

  @ServiciosRest
  Esquema del escenario: Crear un nuevo perfil de usuario exitosamente

    Dado que estoy conectado a la API con los datos necesarios para la solicitud

    Cuando envío una solicitud POST para crear el perfil de usuario

    Entonces el código de estado de respuesta debe ser <statusCode>
    Y la respuesta debe ser un objeto JSON
    Y el objeto JSON debe tener las propiedades:
      | <messagekey> | <message> |

    Ejemplos:
      | statusCode | message                         | messagekey |
      | 201        | Usuario registrado exitosamente | message    |

  @ServiciosRest
  Esquema del escenario: Actualizar Datos del Usuario exitosamente

    Dado que estoy conectado a la API con los datos necesarios para la solicitud

    Cuando envío una solicitud PUT a "actualizar" <user_id> para cambiar los datos del usuario:
      | username              | <username>              |
      | isContactInfoPublic   | <isContactInfoPublic>   |
      | correspondenceAddress | <correspondenceAddress> |
      | bio                   | <bio>                   |
      | organization          | <organization>          |
      | country               | <country>               |
      | faceBookLink          | <faceBookLink>          |

    Entonces el código de estado de respuesta debe ser <statusCode>
    Y la respuesta debe ser un objeto JSON
    Y el objeto JSON debe tener las propiedades:
      | <messagekey> | <message> |


    Ejemplos:
      | user_id | username | email                 | isContactInfoPublic | correspondenceAddress | bio    | organization | country | faceBookLink | message                                                                    | statusCode | messagekey |
      | 21      | hectorL  | juanes13916@gmail.com | true                | string                | string | string       | string  | string       | Usuario actualizado exitosamente. Usuario actualizó su perfil exitosamente | 200        | message    |
      | 53      | hectorL  | juanes13916@gmail.com | true                | string                | string | string       | string  | string       | No tienes permiso para realizar esta acción                                | 403        | error      |
