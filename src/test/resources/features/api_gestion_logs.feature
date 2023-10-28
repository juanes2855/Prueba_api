#language: es

Característica: API de Logs
  Yo como estudiante de Microservicios
  Necesito automatizar el servicio ApiGestionLogs
  Para realizar pruebas a sus EndPoints

  @ServiciosRest
  Esquema del escenario: Crear un nuevo log exitosamente

    Dado que llegan datos de prueba para generar un log

    Cuando envío una solicitud POST para "crear" un "log" con datos de prueba:
      | application | <application> |
      | tipoLog     | <tipoLog>     |
      | modulo      | <modulo>      |
      | resumen     | <resumen>     |
      | descripcion | <descripcion> |

    Entonces el código de estado de respuesta debe ser <statusCode>
    Y la respuesta debe ser un objeto JSON
    Y el objeto JSON debe tener las propiedades:
      | <messagekey> | <message> |

    Ejemplos:
      | statusCode | message                         | messagekey | application          | tipoLog | modulo   | resumen                                                         | descripcion                                                                                                                                                             |
      | 201        | Usuario registrado exitosamente | message    | API_de_Autenticacion | INFO    | add_user | Usuario julian se registro exitosamente desde la automatizacion | Usuario julian se registro exitosamente en la aplicacion API_de_Autenticacion mediante el modulo add_user, en la fecha: 2023-10-28T09:43:59.524751Z (hora de Colombia). |

  @ServiciosRest
  Esquema del escenario: Ver Logs almacenados exitosamente

    Dado que existen logs almacenados

    Cuando envío una solicitud GET para "listarLogs"

    Entonces el código de estado de respuesta debe ser <statusCode>

    Ejemplos:
      | statusCode |
      | 200        |


  @ServiciosRest
  Esquema del escenario: Obtener los detalles de un log  por aplicacion

    Dado que existen logs almacenados

    Cuando envío una solicitud GET para "consultarLogs" por <application>

    Entonces el código de estado de respuesta debe ser <statusCode>

    Y la respuesta debe ser un objeto JSON


    Ejemplos:
      | application          | statusCode |
      | API_de_Autenticacion | 200        |