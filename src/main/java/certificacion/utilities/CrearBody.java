package certificacion.utilities;


import certificacion.models.Usuario;
import net.serenitybdd.core.Serenity;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import static certificacion.enums.DatosGenerales.RUTA_BODY_REGISTRO;
import static certificacion.enums.DatosGenerales.TRANSACTION_ID;
import static certificacion.enums.DatosGenerales.TRANSACTION_ID_VALOR;
import static certificacion.enums.DatosGenerales.USER_ID;
import static certificacion.enums.DatosGenerales.TRANSACTION_CHANNEL_VALOR;
import static certificacion.enums.DatosGenerales.EMAIL_VALOR;
import static certificacion.enums.DatosGenerales.PASSWORD_VALOR;
import static certificacion.enums.DatosGenerales.REQUEST_BODY;

import static certificacion.utilities.ObtenerLogger.mensaje;

public class CrearBody {
    private final String plantillaRuta;
    public CrearBody(String plantillaRuta) {
        this.plantillaRuta = plantillaRuta;
    }
    public static CrearBody conLaPlantilla(String plantilla) {
        return new CrearBody(plantilla);
    }
    public String yLosValores(List<Usuario> values) {
        String nuevaPlantilla = parseJson(plantillaRuta);
        nuevaPlantilla = getString(values.get(0).getEmail(), nuevaPlantilla, EMAIL_VALOR.getMsj());
        nuevaPlantilla = getString(values.get(0).getPassword(), nuevaPlantilla, PASSWORD_VALOR.getMsj());
        nuevaPlantilla = getString(String.valueOf(values.get(0).getUser_id()), nuevaPlantilla, USER_ID.getMsj());
        nuevaPlantilla = getString(values.get(0).getUsername(), nuevaPlantilla, PASSWORD_VALOR.getMsj());
        mensaje().log(Level.INFO, REQUEST_BODY.getMsj(), nuevaPlantilla);
        return nuevaPlantilla;
    }
    private String getString(String values, String nuevaPlantilla, String k) {
        String key = "${" + k + "}";
        nuevaPlantilla = nuevaPlantilla.replace(key, values);
        return nuevaPlantilla;
    }
    private String parseJson(String ruta) {
        String resultStr = "";
        resultStr = readFileAsString(ruta);
        return resultStr;
    }
    private String readFileAsString(String fileName) {
        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            mensaje().info(e.toString());
        }
        return data;
    }
}
