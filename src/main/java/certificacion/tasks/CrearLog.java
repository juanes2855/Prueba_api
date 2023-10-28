package certificacion.tasks;

import certificacion.enums.EndPoint;
import certificacion.models.Usuario;
import certificacion.utilities.CrearBody;
import certificacion.utilities.Obtener;
import io.cucumber.datatable.DataTable;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.util.List;

import static certificacion.enums.DatosGenerales.*;
import static certificacion.utilities.ObtenerLogger.mensaje;

public class CrearLog implements Task {

    List<String> body;
    String operacion;
    String api;
    String endPoint;

    public CrearLog(String operacion, String api,DataTable body) {
        this.operacion = operacion;
        this.api = api;
        this.body = body.asList();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        endPoint=operacion+api;
        endPoint = EndPoint.obtenerUri(endPoint);
        actor.attemptsTo(Post.to(endPoint)
                .with(requestSpecification -> requestSpecification.headers(Obtener.valorCabecera())
                        .body(CrearBody.conLaPlantilla(RUTA_BODY_CREAR_LOG.getMsj())
                                .yLosValores(body,endPoint)).relaxedHTTPSValidation()));
        Serenity.setSessionVariable(RESPONSE.getMsj()).to(SerenityRest.lastResponse().body());
        mensaje().info(RESPONSE.getMsj());
        SerenityRest.lastResponse().body().prettyPrint();

    }

    public static CrearLog conLosDatos(String operacion, String api,DataTable body) {
        return Tasks.instrumented(CrearLog.class, operacion, api,body);
    }
}
