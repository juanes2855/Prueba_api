package certificacion.tasks;

import certificacion.enums.EndPoint;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static certificacion.enums.DatosGenerales.RESPONSE;
import static certificacion.utilities.ObtenerLogger.mensaje;

public class ConsultarUsuario implements Task {

    String endPoint;
    String user_id;

    public ConsultarUsuario(String endPoint, String user_id) {
        this.endPoint = endPoint;
        this.user_id = user_id;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        endPoint = EndPoint.obtenerUri(endPoint);
        endPoint = endPoint + user_id;
        actor.attemptsTo(Get.resource(endPoint));

        Serenity.setSessionVariable(RESPONSE.getMsj()).to(SerenityRest.lastResponse().body());
        mensaje().info(RESPONSE.getMsj());
        SerenityRest.lastResponse().body().prettyPrint();
    }

    public static ConsultarUsuario conLosDatos(String endPoint, String user_id) {
        return Tasks.instrumented(ConsultarUsuario.class, endPoint, user_id);
    }
}
