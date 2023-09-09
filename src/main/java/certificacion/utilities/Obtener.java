package certificacion.utilities;

import java.util.HashMap;
import java.util.Map;
import static certificacion.enums.ConfiguracionAmbiente.CONTENT_TYPE;
import static certificacion.enums.ConfiguracionAmbiente.CONTENT_TYPE_JSON;
public class Obtener {
    public static Map<String, String> valorCabecera() {
        Map<String, String> valores = new HashMap<>();
            valores.put(CONTENT_TYPE.getMsj(), CONTENT_TYPE_JSON.getMsj());

        return valores;
    }

}