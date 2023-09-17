package certificacion.tasks;

import certificacion.enums.EndPoint;
import certificacion.utilities.Obtener;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import static certificacion.enums.DatosGenerales.RESPONSE;
import static certificacion.utilities.ObtenerLogger.mensaje;

public class EliminarUsuario implements Task {

    String endPoint;
    String user_id;
    String token;

    public EliminarUsuario(String endPoint, String user_id, String token) {
        this.endPoint = endPoint;
        this.user_id = user_id;
        this.token = token;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        endPoint = EndPoint.obtenerUri(endPoint);
        endPoint = endPoint + user_id;
        actor.attemptsTo(Delete.from(endPoint)
                .with(requestSpecification -> requestSpecification.headers(Obtener.valorCabeceraAuth(token)).relaxedHTTPSValidation()));
        Serenity.setSessionVariable(RESPONSE.getMsj()).to(SerenityRest.lastResponse().body());
        mensaje().info(RESPONSE.getMsj());
        SerenityRest.lastResponse().body().prettyPrint();
    }

    public static EliminarUsuario conLosDatos(String endPoint , String user_id, String token){
        return Tasks.instrumented(EliminarUsuario.class, endPoint,user_id, token);
    }
}
