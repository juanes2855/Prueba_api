package certificacion.utilities;


import certificacion.models.Usuario;
import net.serenitybdd.core.Serenity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    /*public String yLosValores(List<Usuario> values) {
        String nuevaPlantilla = parseJson(plantillaRuta);
        nuevaPlantilla = getString(values.get(0).getEmail(), nuevaPlantilla, EMAIL_VALOR.getMsj());
        nuevaPlantilla = getString(values.get(0).getPassword(), nuevaPlantilla, PASSWORD_VALOR.getMsj());
        nuevaPlantilla = getString(String.valueOf(values.get(0).getUser_id()), nuevaPlantilla, USER_ID.getMsj());
        nuevaPlantilla = getString(values.get(0).getUsername(), nuevaPlantilla, USERNAME_VALOR.getMsj());
        mensaje().log(Level.INFO, REQUEST_BODY.getMsj(), nuevaPlantilla);
        return nuevaPlantilla;
    }*/

    public String yLosValores(List<Usuario> values) {
        String nuevaPlantilla = parseJson(plantillaRuta);
        nuevaPlantilla = nuevaPlantilla.replace(EMAIL.getMsj(), values.get(0).getEmail());
        nuevaPlantilla = nuevaPlantilla.replace(PASSWORD.getMsj(), values.get(0).getPassword());
        nuevaPlantilla = nuevaPlantilla.replace("\""+USER_ID.getMsj()+"\"", String.valueOf(values.get(0).getUser_id()));
        nuevaPlantilla = nuevaPlantilla.replace(USERNAME.getMsj(),values.get(0).getUsername());
        mensaje().log(Level.INFO, REQUEST_BODY.getMsj(), nuevaPlantilla);
        return nuevaPlantilla;
    }

 /*   private String getString(String values, String nuevaPlantilla, String k) {
        String key = "${" + k + "}";
        nuevaPlantilla = nuevaPlantilla.replace(key, values);
        mensaje().log(Level.SEVERE, REQUEST_BODY.getMsj(), nuevaPlantilla);
        return nuevaPlantilla;
    }*/


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
