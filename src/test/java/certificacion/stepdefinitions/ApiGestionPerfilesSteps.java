package certificacion.stepdefinitions;
import certificacion.tasks.CrearPerfil;
import certificacion.tasks.CrearUsuario;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import static certificacion.enums.ConfiguracionAmbiente.BASE_URL_PROFILE;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class ApiGestionPerfilesSteps {
    @Cuando("envío una solicitud POST para {string} {int} de usuario")
    public void envíoUnaSolicitudPOSTParaCrearElPerfilDeUsuario(String endPoint,Integer userID, DataTable body) {
        theActorInTheSpotlight().attemptsTo(CrearPerfil.conLosDatos(endPoint,userID,body));
    }

    @Dado("que estoy conectado a la API perfiles con los datos necesarios para la solicitud")
    public void queEstoyConectadoALaAPIConLosDatosNecesariosParaLaSolicitud() {
        theActorInTheSpotlight().whoCan(CallAnApi.at(BASE_URL_PROFILE.getMsj()));
    }


    @Cuando("envío una solicitud PUT a {string} {int} para cambiar los datos del perfil:")
    public void envíoUnaSolicitudPUTAParaCambiarLosDatosDelPerfil(String string, Integer int1, io.cucumber.datatable.DataTable dataTable) {


    }
}
