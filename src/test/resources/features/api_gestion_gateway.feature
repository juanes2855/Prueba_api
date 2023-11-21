#language: es

Característica: API GATEWAY
  Yo, como estudiante de Microservicios
  Quiero automatizar las pruebas del servicio ApiGestionGateway
  Para asegurarme de que sus EndPoints funcionen correctamente


  @ServiciosRest
  Esquema del escenario: Crear un nuevo usuario exitosamente

    Dado que estoy conectado a la API con los datos necesarios para la solicitud

    Cuando envío una solicitud POST a "crear" con datos aleatorios

    Entonces el código de estado de respuesta debe ser <statusCode>
    Y la respuesta debe ser un objeto JSON
    Y el objeto JSON debe tener las propiedades:
      | <messagekey> | <message> |

    Ejemplos:
      | statusCode | message                         | messagekey |
      | 201        | Usuario registrado exitosamente | message    |


  @ServiciosRest
  Esquema del escenario: Obtener los detalles de un usuario  por user_id exitosamente

    Dado que estoy conectado a la API con los datos necesarios para la solicitud

    Cuando envío una solicitud GET a "consultar" <user_id>

    Entonces el código de estado de respuesta debe ser <statusCode>

    Y la respuesta debe ser un objeto JSON

    Y el objeto JSON debe tener las propiedades:
      | user_id  | <user_id>  |
      | username | <username> |

    Ejemplos:
      | user_id | username | statusCode |
      | 21      | hectorL  | 200        |
      | 2501    | hectorp  | 404        |


  @ServiciosRest
  Esquema del escenario: Ver Usuarios exitosamente

    Dado que estoy conectado a la API con los datos necesarios para la solicitud

    Cuando envío una solicitud GET para "listar" "page=" <page> "&per_page=" <per_page>

    Entonces el código de estado de respuesta debe ser <statusCode>

    Y la respuesta debe ser un arreglo JSON

    Y el arreglo JSON debe tener al menos 1 elemento

    Ejemplos:
      | page | per_page | statusCode |
      | 1    | 10       | 200        |


  @ServiciosRest
  Esquema del escenario: Iniciar sesión a un usuario exitosamente

    Dado que estoy conectado a la API con los datos necesarios para la solicitud

    Cuando envío una solicitud POST para "iniciar" con JSON:
      | username | <username> |
      | password | <password> |

    Entonces el código de estado de respuesta debe ser <statusCode>

    Y la respuesta debe ser un objeto JSON

    Y el JSON debe tener al menos un elemento

    Ejemplos:
      | password | username | statusCode |
      | string   | hectorL  | 200        |
      | P@ssw0r  | hectorL  | 401        |

  @ServiciosRest
  Esquema del escenario: Actualizar Datos del Usuario exitosamente

    Dado que estoy conectado a la API con los datos necesarios para la solicitud

    Cuando envío una solicitud PUT a "actualizar" <user_id> para cambiar los datos del usuario:
      | username              | <username>              |
      | password              | <password>              |
      | email                 | <email>                 |
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
      | user_id | username | password | email                 | isContactInfoPublic | correspondenceAddress | bio    | organization | country | faceBookLink | message                                                                    | statusCode | messagekey |
      | 21      | hectorL  | string   | juanes13916@gmail.com | true                | string                | string | string       | string  | string       | Usuario actualizado exitosamente. Usuario actualizó su perfil exitosamente | 200        | message    |
      | 53      | hectorL  | 1        | juanes13916@gmail.com | true                | string                | string | string       | string  | string       | No tienes permiso para realizar esta acción                                | 403        | error      |


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
      | 2501      | hectorp  | 404        |
