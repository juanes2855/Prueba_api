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

public class ListarLogs implements Task {

    String endPoint;

    public ListarLogs(String endPoint) {
        this.endPoint = endPoint;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        endPoint = EndPoint.obtenerUri(endPoint);

        actor.attemptsTo(Get.resource(endPoint));

        Serenity.setSessionVariable(RESPONSE.getMsj()).to(SerenityRest.lastResponse().body());
        mensaje().info(RESPONSE.getMsj());
        SerenityRest.lastResponse().body().prettyPrint();
    }

    public static ListarLogs conLosDatos(String endPoint){
        return Tasks.instrumented(ListarLogs.class,endPoint);
    }
}
