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
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.util.List;

import static certificacion.enums.DatosGenerales.RESPONSE;
import static certificacion.enums.DatosGenerales.RUTA_BODY_INICIO;
import static certificacion.utilities.ObtenerLogger.mensaje;

public class IniciarSesion implements Task {

    List<String> body;
    String endPoint;

    String action;

    public IniciarSesion(DataTable body, String endPoint) {
        this.body = body.asList();
        this.endPoint = endPoint;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        action = endPoint;
        endPoint = EndPoint.obtenerUri(endPoint);
        actor.attemptsTo(Post.to(endPoint)
                .with(requestSpecification -> requestSpecification.headers(Obtener.valorCabecera())
                        .body(CrearBody.conLaPlantilla(RUTA_BODY_INICIO.getMsj())
                                .yLosValores(body, action)).relaxedHTTPSValidation()));
        Serenity.setSessionVariable(RESPONSE.getMsj()).to(SerenityRest.lastResponse().body());
        mensaje().info(RESPONSE.getMsj());
        SerenityRest.lastResponse().body().prettyPrint();
    }

    public static IniciarSesion conLosDatos(DataTable body, String endPoint) {
        return Tasks.instrumented(IniciarSesion.class, body, endPoint);
    }
}
