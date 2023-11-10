package certificacion.tasks;

import certificacion.enums.EndPoint;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static certificacion.enums.DatosGenerales.RESPONSE;
import static certificacion.utilities.ObtenerLogger.mensaje;

public class ConsultarLogsAplicacion implements Task {

    String endPoint;
    String aplication;

    public ConsultarLogsAplicacion(String endPoint, String aplication) {
        this.endPoint = endPoint;
        this.aplication = aplication;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        endPoint = EndPoint.obtenerUri(endPoint);
        endPoint= endPoint+aplication;

        actor.attemptsTo(Get.resource(endPoint).with(requestSpecification -> requestSpecification.param("application")));

        Serenity.setSessionVariable(RESPONSE.getMsj()).to(SerenityRest.lastResponse().body());
        mensaje().info(RESPONSE.getMsj());
        SerenityRest.lastResponse().body().prettyPrint();
    }

    public static ConsultarLogsAplicacion conLosDatos(String endPoint, String aplication){
        return Tasks.instrumented(ConsultarLogsAplicacion.class,endPoint,aplication);
    }
}
