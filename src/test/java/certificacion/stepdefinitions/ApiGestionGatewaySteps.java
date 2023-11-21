package certificacion.stepdefinitions;

import certificacion.models.Usuario;
import certificacion.tasks.ActualizarUsuario;
import certificacion.tasks.ActualizarUsuarioPerfil;
import certificacion.tasks.ConsultarUsuario;
import certificacion.tasks.CrearUsuario;
import certificacion.utilities.TokenManager;
import com.github.javafaker.Faker;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.restassured.response.Response;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import java.util.ArrayList;
import java.util.List;

import static certificacion.enums.ConfiguracionAmbiente.BASE_URL_GATEWAY;
import static certificacion.enums.ConfiguracionAmbiente.BASE_URL_LOGS;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class ApiGestionGatewaySteps {
    private Response response;

    private String token;


    @Dado("que estoy conectado a la API con los datos necesarios para la solicitud")
    public void queEstoyConectadoALaAPIConLosDatosNecesariosParaLaSolicitud() {
        theActorInTheSpotlight().whoCan(CallAnApi.at(BASE_URL_GATEWAY.getMsj()));
    }

    @Cuando("envío una solicitud POST a {string} con datos aleatorios")
    public void envíoUnaSolicitudPOSTAConDatosAleatorios(String endPoint) {
        Faker faker = new Faker();
        Integer user_id = Math.toIntExact(faker.number().randomNumber());
        String username = faker.name().username();
        String password = faker.internet().password();
        String email = faker.internet().emailAddress();
        Usuario usuario = new Usuario(user_id,email,username,password);

        List<Usuario> body = new ArrayList<>();
        body.add(usuario);

        theActorInTheSpotlight().attemptsTo(CrearUsuario.conLosDatos(body, endPoint));
    }

    @Cuando("envío una solicitud PUT a {string} {} para cambiar los datos del usuario:")
    public void envíoUnaSolicitudPUTAParaCambiarLosDatosDelUsuario(String endPoint, String user_id , DataTable body) {
        token = TokenManager.getAuthToken();
        theActorInTheSpotlight().attemptsTo(ActualizarUsuarioPerfil.conLosDatos(body,endPoint, user_id, token));
    }

    @Cuando("envío una solicitud para obtener la informacion del perfil a {string} con {}")
    public void envíoUnaSolicitudParaObtenerLaInformacionDelPerfilACon(String endPoint, String user_id) {
        theActorInTheSpotlight().attemptsTo(ConsultarUsuario.conLosDatos(endPoint, user_id));
    }
}
