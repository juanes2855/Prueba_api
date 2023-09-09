package certificacion.enums;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum EndPoint {
    PERMISO("token", "/oauth/v2.0/token"),
    CREAR_USUARIO("crear", "/users"),
    CONSULTAR_USUARIO("consultar", "/users/"),
    PERMISO_SOY_YO("endPoint", "/oauth2/token"),
    PERMISO_EMBAJADOR_VALIDAR("validar", "validateUser");
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
