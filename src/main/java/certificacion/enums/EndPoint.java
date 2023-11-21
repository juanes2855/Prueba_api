package certificacion.enums;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum EndPoint {
    CREAR_USUARIO("crear", "/users"),
    CREAR_LOG("crearlog", "/logs"),
    LISTAR_LOG("listarLogs", "/logs"),
    CONSULTAR_USUARIO("consultar", "/users/"),
    CONSULTAR_PERFIL("consultarPerfil", "/profile/"),
    CONSULTAR_LOS_APLICACION("consultarLogs", "/logs/%7Bapplication%7D?application="),
    LISTAR_USUARIOS("listar", "/users?"),
    ACTUALIZAR_USUARIO("actualizar", "/users/"),
    CAMBIAR_PASSWORD("cambio", "/users/"),
    ELIMINAR_USUARIO("eliminar", "/users/"),
    ENVIAR_EMAIL("emails", "/emails"),
    INICIAR_SESION("iniciar", "/users/login");

    private final String llave;
    private final String valor;
    private static Map<String, String> uri;

    EndPoint(String llave, String valor) {
        this.llave = llave;
        this.valor = valor;
    }

    static {
        Map<String, String> endPoint = new HashMap<>();
        for (EndPoint uri : EndPoint.values()) {
            endPoint.put(uri.llave, uri.valor);
        }
        uri = Collections.unmodifiableMap(endPoint);
    }

    public static String obtenerUri(String ambiente) {
        if (uri.containsKey(ambiente)) {
            return uri.get(ambiente);
        }
        return null;
    }
}
