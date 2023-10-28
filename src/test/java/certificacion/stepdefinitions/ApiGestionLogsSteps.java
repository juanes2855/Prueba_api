package certificacion.stepdefinitions;

import certificacion.tasks.ConsultarLogsAplicacion;
import certificacion.tasks.ConsultarUsuario;
import certificacion.tasks.CrearLog;
import certificacion.tasks.ListarLogs;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import static certificacion.enums.ConfiguracionAmbiente.BASE_URL_LOGS;
import static certificacion.enums.DatosGenerales.RESPONSE;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class ApiGestionLogsSteps {

    private Response response;

    @Dado("que llegan datos de prueba para generar un log")
    public void queLleganDatosDePruebaParaGenerarUnLog() {
        theActorInTheSpotlight().whoCan(CallAnApi.at(BASE_URL_LOGS.getMsj()));
    }

    @Cuando("envío una solicitud POST para {string} un {string} con datos de prueba:")
    public void envíoUnaSolicitudPOSTParaUnLogConDatosDePrueba(String operacion, String api, DataTable body) {
        theActorInTheSpotlight().attemptsTo(CrearLog.conLosDatos(operacion, api, body));
    }

    @Dado("que existen logs almacenados")
    public void queExistenLogsAlmacenados() {
        theActorInTheSpotlight().whoCan(CallAnApi.at(BASE_URL_LOGS.getMsj()));
    }

    @Cuando("envío una solicitud GET para {string}")
    public void envíoUnaSolicitudGETPara(String endPoint) {
        theActorInTheSpotlight().attemptsTo(ListarLogs.conLosDatos(endPoint));
    }

    @Cuando("envío una solicitud GET para {string} por {}")
    public void envioUnaSolicitudGETParaPor(String endPoint, String aplication) {
        theActorInTheSpotlight().attemptsTo(ConsultarLogsAplicacion.conLosDatos(endPoint, aplication));
    }
}
