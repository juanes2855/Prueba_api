package certificacion.utilities;

import java.util.HashMap;
import java.util.Map;

import static certificacion.enums.ConfiguracionAmbiente.*;

public class Obtener {
    public static Map<String, String> valorCabecera() {
        Map<String, String> valores = new HashMap<>();
            valores.put(CONTENT_TYPE.getMsj(), CONTENT_TYPE_JSON.getMsj());

        return valores;
    }

    public static Map<String, String> valorCabeceraAuth(String TOKEN) {
        Map<String, String> valores = new HashMap<>();
        valores.put(CONTENT_TYPE.getMsj(), CONTENT_TYPE_JSON.getMsj());
        valores.put(AUTHORIZATION.getMsj(), "Bearer "+TOKEN);
        return valores;
    }

}