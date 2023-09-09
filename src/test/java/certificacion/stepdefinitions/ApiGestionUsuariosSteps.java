package certificacion.stepdefinitions;

import certificacion.exceptions.ExcepcionGeneral;
import certificacion.models.Usuario;
import certificacion.questions.ObtenerRespuesta;
import certificacion.tasks.CrearUsuario;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.hamcrest.Matchers;

import java.util.List;

import static certificacion.enums.ConfiguracionAmbiente.BASE_URL;
import static certificacion.enums.DatosGenerales.BODY;
import static certificacion.enums.Mensajes.CONST_CREAR_USUARIO;
import static certificacion.enums.Mensajes.MENSAJE_ERROR_CODIGO;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class ApiGestionUsuariosSteps {

    private RequestSpecification request;
    private Response response;

    @Cuando("envío una solicitud POST a {string} con JSON:")
    public void envioUnaSolicitudPOSTAConJSON(String endpoint, List<Usuario> body) {
        theActorInTheSpotlight().whoCan(CallAnApi.at(BASE_URL.getMsj()));
        theActorInTheSpotlight().attemptsTo(CrearUsuario.conLosDatos(body, endpoint));
    }

    @Entonces("el código de estado de respuesta debe ser {int}")
    public void elCodigoDeEstadoDeRespuestaDebeSer(int statusCode) {
        System.out.println("--------------------+" + statusCode);
        theActorInTheSpotlight().should(GivenWhenThen.seeThat(
                ObtenerRespuesta.codigo(), Matchers.equalTo(statusCode)).orComplainWith(ExcepcionGeneral.class, MENSAJE_ERROR_CODIGO.getMsj()));
    }

    @Entonces("la respuesta debe ser un objeto JSON")
    public void laRespuestaDebeSerUnObjetoJSON() {
        response.then().body(Matchers.is(Matchers.notNullValue()));
    }

    @Entonces("el objeto JSON debe tener las propiedades:")
    public void elObjetoJSONDebeTenerLasPropiedades(List<Usuario> dataTable) {
       /* DataTableRow row = dataTable.row(0);
        for (String expectedKey : row.keySet()) {
            String expectedValue = row.get(expectedKey);
            response.then().body(expectedKey, Matchers.equalTo(expectedValue));
        }*/
    }
}
