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

import static certificacion.enums.DatosGenerales.*;
import static certificacion.utilities.ObtenerLogger.mensaje;

public class CrearPerfil implements Task {

    String endPoint;
    Integer userId;

    List<String> body;

    public CrearPerfil(String endPoint, Integer userId, DataTable body) {
        this.endPoint = endPoint;
        this.userId = userId;
        this.body = body.asList();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        endPoint = EndPoint.obtenerUri(endPoint);
        endPoint+= userId;
        actor.attemptsTo(Post.to(endPoint)
                .with(requestSpecification -> requestSpecification.headers(Obtener.valorCabecera())
                        .body(CrearBody.conLaPlantilla(RUTA_BODY_CREAR_PERFIL.getMsj())
                                .yLosValoresCrearPerfil(body)).relaxedHTTPSValidation()));
        Serenity.setSessionVariable(RESPONSE.getMsj()).to(SerenityRest.lastResponse().body());
        mensaje().info(RESPONSE.getMsj());
        SerenityRest.lastResponse().body().prettyPrint();

    }

    public static CrearPerfil conLosDatos(String endPoint, Integer userId,DataTable body){
        return Tasks.instrumented(CrearPerfil.class,endPoint,userId , body);
    }
}
