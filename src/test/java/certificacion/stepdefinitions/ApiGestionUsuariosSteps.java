package certificacion.stepdefinitions;

import certificacion.enums.EndPoint;
import certificacion.exceptions.ExcepcionGeneral;
import certificacion.models.Usuario;
import certificacion.questions.ObtenerRespuesta;
import certificacion.tasks.ConsultarUsuario;
import certificacion.tasks.CrearUsuario;
import certificacion.tasks.IniciarSesion;
import certificacion.tasks.ListarUsuarios;
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
import org.json.JSONArray;
import org.json.JSONObject;
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
        response = Serenity.sessionVariableCalled(RESPONSE.getMsj());
        response.then().body(Matchers.is(Matchers.notNullValue()));
    }

    @Y("valida que el mensaje de error sea {string}")
    public void validaQueElMensajeDeErrorSea(String mensaje) {
        response = Serenity.sessionVariableCalled(RESPONSE.getMsj());
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

    @Cuando("envío una solicitud POST para {string} con JSON:")
    public void envíoUnaSolicitudPOSTParaConJSON(String endPoint, DataTable body) {
        theActorInTheSpotlight().attemptsTo(IniciarSesion.conLosDatos(body, endPoint));
    }

    @Cuando("envío una solicitud GET para {string} {string} {int} {string} {int}")
    public void envíoUnaSolicitudGETPara(String endPoint, String page, Integer pageValue, String perPage, Integer perPageValue) {
        theActorInTheSpotlight().attemptsTo(ListarUsuarios.conLosDatos(endPoint, page, pageValue, perPage, perPageValue));
    }

    @Entonces("la respuesta debe ser un arreglo JSON")
    public void laRespuestaDebeSerUnArregloJSON() {
        response = Serenity.sessionVariableCalled(RESPONSE.getMsj());
        // Verificar que el encabezado "Content-Type" de la respuesta indique que es un JSON
        Assert.assertTrue(response.getContentType().toLowerCase().contains("application/json"));
        // Convertir la respuesta en un arreglo JSON
        JSONObject jsonResponse = new JSONObject(response.getBody().asString());

        // Obtener el arreglo "a.users" del objeto JSON
        JSONArray jsonArray = jsonResponse.getJSONArray("users");

        // Verificar que el arreglo JSON tenga al menos un elemento
        Assert.assertTrue(jsonArray.length() > 0);
    }

    @Entonces("el arreglo JSON debe tener al menos 1 elemento")
    public void elArregloJSONDebeTenerAlMenosUnElemento() {
        response = Serenity.sessionVariableCalled(RESPONSE.getMsj());
        JSONObject jsonResponse = new JSONObject(response.getBody().asString());
        // Convertir la respuesta en un arreglo JSON
        JSONArray jsonArray = jsonResponse.getJSONArray("users");

        // Verificar que el arreglo JSON tenga al menos un elemento
        Assert.assertTrue(jsonArray.length() > 0);
    }


    /*@Entonces("cada elemento en el arreglo JSON debe tener las propiedades:")
    public void cadaElementoEnElArregloJSONDebeTenerLasPropiedades(DataTable dataTable) {

        response = Serenity.sessionVariableCalled(RESPONSE.getMsj());
        JSONObject jsonResponse = new JSONObject(response.getBody().asString());
        // Convertir la respuesta en un arreglo JSON
        JSONArray jsonArray = jsonResponse.getJSONArray("users");

        // Obtener la lista de mapas desde la DataTable
        List<Map<String, String>> data = dataTable.asMaps();

        // Iterar a través de cada elemento en el arreglo JSON
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            System.out.println("-*-*-*+**-*-*-*-*-"+jsonObject);
            Map<String, String> properties = dataTable.asMap(String.class, String.class);

            // Verificar las propiedades de cada elemento
            for (Map.Entry<String, String> row : properties.entrySet()) {
                String propertyName = row.getKey();
                String expectedValue = row.getValue();

                System.out.println("-*-*-*+**-*-*-*-*-"+propertyName+" "+expectedValue);

                Assert.assertEquals("Valor incorrecto para la propiedad " + propertyName,
                        expectedValue, jsonObject.optString(propertyName));
            }

        }
    }*/


}
