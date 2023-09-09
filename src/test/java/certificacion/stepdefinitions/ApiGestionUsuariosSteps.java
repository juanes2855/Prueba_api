package certificacion.stepdefinitions;

import certificacion.exceptions.ExcepcionGeneral;
import certificacion.models.Usuario;
import certificacion.questions.ObtenerRespuesta;
import certificacion.tasks.ConsultarUsuario;
import certificacion.tasks.CrearUsuario;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.model.DataTableRow;
import org.hamcrest.Matchers;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static certificacion.enums.ConfiguracionAmbiente.BASE_URL;
import static certificacion.enums.DatosGenerales.BODY;
import static certificacion.enums.DatosGenerales.RESPONSE;
import static certificacion.enums.Mensajes.*;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class ApiGestionUsuariosSteps {

    private RequestSpecification request;
    private Response response;

    @Dado("me conecto a la api")
    public void meConectoALaApi() {
        theActorInTheSpotlight().whoCan(CallAnApi.at(BASE_URL.getMsj()));
    }

    @Cuando("envío una solicitud POST a {string} con JSON:")
    public void envioUnaSolicitudPOSTAConJSON(String endPoint, List<Usuario> body) {

        theActorInTheSpotlight().attemptsTo(CrearUsuario.conLosDatos(body, endPoint));
    }

    @Entonces("el código de estado de respuesta debe ser {int}")
    public void elCodigoDeEstadoDeRespuestaDebeSer(int statusCode) {
        theActorInTheSpotlight().should(GivenWhenThen.seeThat(
                ObtenerRespuesta.codigo(), Matchers.equalTo(statusCode)).orComplainWith(ExcepcionGeneral.class, MENSAJE_ERROR_CODIGO.getMsj()));
    }

    @Entonces("la respuesta debe ser un objeto JSON")
    public void laRespuestaDebeSerUnObjetoJSON() {
        response= Serenity.sessionVariableCalled(RESPONSE.getMsj());
        response.then().body(Matchers.is(Matchers.notNullValue()));
    }

    @Y("valida que el mensaje de error sea {string}")
    public void validaQueElMensajeDeErrorSea(String mensaje) {
        response= Serenity.sessionVariableCalled(RESPONSE.getMsj());
        Assert.assertEquals(mensaje, response.jsonPath().getString(CONST_MENSAJE.getMsj()));
    }

    @Cuando("envío una solicitud GET a {string} {}")
    public void envioUnaSolicitudGETA(String endPoint, String user_id) {
        theActorInTheSpotlight().attemptsTo(ConsultarUsuario.conLosDatos(endPoint, user_id));
    }

    @Entonces("el objeto JSON debe tener las propiedades:")
    public void elObjetoJSONDebeTenerLasPropiedades(DataTable dataTable) {
        // Convierte la tabla de datos en un mapa de propiedades
        Map<String, String> properties = dataTable.asMap(String.class, String.class);

        // Verifica que el cuerpo de la respuesta contenga las propiedades y valores esperados
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            String propertyName = entry.getKey();
            String expectedValue = entry.getValue();
            String actualValue = response.jsonPath().getString(propertyName);

            Assert.assertEquals("Valor incorrecto para la propiedad " + propertyName, expectedValue, actualValue);
        }
    }
}
