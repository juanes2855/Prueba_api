package certificacion.setup.datadefinition;
import certificacion.models.Usuario;
import io.cucumber.java.DataTableType;

import java.util.Map;

import static certificacion.enums.DatosGenerales.*;

public class CargaDeModelos {

    @DataTableType
    public Usuario UsuarioEntry(Map<String,String> entry){
        return new Usuario(Integer.parseInt(entry.get(USER_ID_VALOR.getMsj())),entry.get(EMAIL_VALOR.getMsj()),entry.get(USERNAME_VALOR.getMsj()), entry.get(PASSWORD_VALOR.getMsj()));
    }


}
