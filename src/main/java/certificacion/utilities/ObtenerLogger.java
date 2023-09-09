package certificacion.utilities;

import java.util.logging.Logger;
public class ObtenerLogger {
    public ObtenerLogger() {
    }
    public static Logger mensaje(){
        return Logger.getLogger(ObtenerLogger.class.getName());
    }
}
