package certificacion.tasks;

import certificacion.enums.EndPoint;
import certificacion.utilities.CrearBody;
import certificacion.utilities.Obtener;
import io.cucumber.datatable.DataTable;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Put;

import java.util.List;

import static certificacion.enums.DatosGenerales.*;
import static certificacion.utilities.ObtenerLogger.mensaje;

public class ActualizarUsuarioPerfil  implements Task  {

    List<String> body;
    String endPoint;
    String user_id;
    String token;

    public ActualizarUsuarioPerfil(DataTable body, String endPoint, String user_id, String token) {
        this.body = body.asList();
        this.endPoint = endPoint;
        this.user_id = user_id;
        this.token = token;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        endPoint = EndPoint.obtenerUri(endPoint);
        endPoint = endPoint + user_id;
        actor.attemptsTo(Put.to(endPoint)
                .with(requestSpecification -> requestSpecification.headers(Obtener.valorCabeceraAuth(token))
                        .body(CrearBody.conLaPlantilla(RUTA_BODY_ACTUALIZAR_CON_PERFIL.getMsj())
                                .yLosValoresActualizarConPerfil(body)).relaxedHTTPSValidation()));
        Serenity.setSessionVariable(RESPONSE.getMsj()).to(SerenityRest.lastResponse().body());
        mensaje().info(RESPONSE.getMsj());
        SerenityRest.lastResponse().body().prettyPrint();
    }

    public static ActualizarUsuarioPerfil conLosDatos(DataTable body, String endPoint , String user_id, String token){
        return Tasks.instrumented(ActualizarUsuarioPerfil.class, body,endPoint,user_id, token);
    }
}
