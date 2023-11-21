package certificacion.utilities;


import certificacion.models.Usuario;
import net.serenitybdd.core.Serenity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;

import static certificacion.enums.DatosGenerales.*;
import static certificacion.utilities.ObtenerLogger.mensaje;

public class CrearBody<Int> {
    private final String plantillaRuta;

    public CrearBody(String plantillaRuta) {
        this.plantillaRuta = plantillaRuta;
    }

    public static CrearBody conLaPlantilla(String plantilla) {
        return new CrearBody(plantilla);
    }

    public String yLosValores(List<Usuario> values) {
        String nuevaPlantilla = parseJson(plantillaRuta);
        nuevaPlantilla = nuevaPlantilla.replace(EMAIL.getMsj(), values.get(0).getEmail());
        nuevaPlantilla = nuevaPlantilla.replace(PASSWORD.getMsj(), values.get(0).getPassword());
        nuevaPlantilla = nuevaPlantilla.replace(USER_ID.getMsj(), String.valueOf(values.get(0).getUser_id()));
        nuevaPlantilla = nuevaPlantilla.replace(USERNAME.getMsj(), values.get(0).getUsername());
        mensaje().log(Level.INFO, REQUEST_BODY.getMsj(), nuevaPlantilla);
        return nuevaPlantilla;
    }

    public String yLosValores(List<String> values, String endPoint) {
        String nuevaPlantilla = parseJson(plantillaRuta);
        if (endPoint.equalsIgnoreCase("/logs")) {
            nuevaPlantilla = nuevaPlantilla.replace(APLICATION.getMsj(), values.get(1));
            nuevaPlantilla = nuevaPlantilla.replace(TIPO_LOG.getMsj(), values.get(3));
            nuevaPlantilla = nuevaPlantilla.replace(MODULO.getMsj(), values.get(5));
            // Configurar la zona horaria de Colombia
            ZoneId colombiaTimeZone = ZoneId.of("America/Bogota");
            // Obtener la fecha y hora actual en la zona horaria de Colombia
            LocalDateTime currentDateTime = LocalDateTime.now(colombiaTimeZone);
            // Formatear la hora en formato de 24 horas
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            String formattedTime = formatter.format(currentDateTime);
            nuevaPlantilla = nuevaPlantilla.replace(FECHA_HORA_GENERACION.getMsj(), formattedTime);
            nuevaPlantilla = nuevaPlantilla.replace(RESUMEN.getMsj(), values.get(7));
            nuevaPlantilla = nuevaPlantilla.replace(DESCRIPCION.getMsj(), values.get(9));
            mensaje().log(Level.INFO, REQUEST_BODY.getMsj(), nuevaPlantilla);
        } else {
            nuevaPlantilla = nuevaPlantilla.replace(USERNAME.getMsj(), values.get(1));
            nuevaPlantilla = nuevaPlantilla.replace(PASSWORD.getMsj(), values.get(3));
            mensaje().log(Level.INFO, REQUEST_BODY.getMsj(), nuevaPlantilla);
        }
          return nuevaPlantilla;
    }

    public String yLosValoresActualizar(List<String> values) {
        String nuevaPlantilla = parseJson(plantillaRuta);
        nuevaPlantilla = nuevaPlantilla.replace(EMAIL.getMsj(), values.get(5));
        nuevaPlantilla = nuevaPlantilla.replace(PASSWORD.getMsj(), values.get(3));
        nuevaPlantilla = nuevaPlantilla.replace(USERNAME.getMsj(), values.get(1));
        mensaje().log(Level.INFO, REQUEST_BODY.getMsj(), nuevaPlantilla);
        return nuevaPlantilla;
    }
    public String yLosValoresActualizarConPerfil(List<String> values) {
        String nuevaPlantilla = parseJson(plantillaRuta);
        nuevaPlantilla = nuevaPlantilla.replace(FACEBOOK_LINK.getMsj(), values.get(17));
        nuevaPlantilla = nuevaPlantilla.replace(COUNTRY.getMsj(), values.get(15));
        nuevaPlantilla = nuevaPlantilla.replace(ORGANIZATION.getMsj(), values.get(13));
        nuevaPlantilla = nuevaPlantilla.replace(BIO.getMsj(), values.get(11));
        nuevaPlantilla = nuevaPlantilla.replace(CORRESPONDENCE_ADDRESS.getMsj(), values.get(9));
        nuevaPlantilla = nuevaPlantilla.replace(IS_CONTACT_INFO_PUBLIC.getMsj(), values.get(7));
        nuevaPlantilla = nuevaPlantilla.replace(EMAIL.getMsj(), values.get(5));
        nuevaPlantilla = nuevaPlantilla.replace(PASSWORD.getMsj(), values.get(3));
        nuevaPlantilla = nuevaPlantilla.replace(USERNAME.getMsj(), values.get(1));
        mensaje().log(Level.INFO, REQUEST_BODY.getMsj(), nuevaPlantilla);
        return nuevaPlantilla;
    }

    public String yLosValoresCambiarPassword(List<String> values) {
        String nuevaPlantilla = parseJson(plantillaRuta);
        nuevaPlantilla = nuevaPlantilla.replace(NEW_PASSWORD.getMsj(), values.get(1));
        ;
        mensaje().log(Level.INFO, REQUEST_BODY.getMsj(), nuevaPlantilla);
        return nuevaPlantilla;
    }

    public String yLosValoresEnviarEmail(List<String> values) {
        String nuevaPlantilla = parseJson(plantillaRuta);
        nuevaPlantilla = nuevaPlantilla.replace(USERNAME.getMsj(), values.get(1));
        ;
        mensaje().log(Level.INFO, REQUEST_BODY.getMsj(), nuevaPlantilla);
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
