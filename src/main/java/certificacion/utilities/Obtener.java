package certificacion.utilities;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import static certificacion.enums.ConfiguracionAmbiente.CONSTANT_ENV_CERTI;
import static certificacion.enums.ConfiguracionAmbiente.CONSTANT_ENV_PRUEBAS;
import static certificacion.enums.ConfiguracionAmbiente.HOST;
import static certificacion.enums.ConfiguracionAmbiente.HOST_CERTI;
import static certificacion.enums.ConfiguracionAmbiente.HOST_PRUB;
import static certificacion.enums.ConfiguracionAmbiente.CONTENT_TYPE;
import static certificacion.enums.ConfiguracionAmbiente.CONTENT_TYPE_JSON;
import static certificacion.enums.DatosGenerales.FORMATO_FECHA_HORA;
public class Obtener {
    public static Map<String, String> valorCabecera(String servicio) {
        Map<String, String> valores = new HashMap<>();
        if (servicio.equalsIgnoreCase(CONSTANT_ENV_CERTI.getMsj())) {
            valores.put(HOST.getMsj(), HOST_CERTI.getMsj());
            valores.put(CONTENT_TYPE.getMsj(), CONTENT_TYPE_JSON.getMsj());
        }
        if (servicio.equalsIgnoreCase(CONSTANT_ENV_PRUEBAS.getMsj())) {
            valores.put(HOST.getMsj(), HOST_PRUB.getMsj());
            valores.put(CONTENT_TYPE.getMsj(), CONTENT_TYPE_JSON.getMsj());
        }
        return valores;
    }
    public static String transactionChannelId() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMATO_FECHA_HORA.getMsj());
        return simpleDateFormat.format(date);
    }
}