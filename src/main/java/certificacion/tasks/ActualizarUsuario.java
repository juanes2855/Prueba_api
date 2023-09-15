package certificacion.tasks;

import certificacion.enums.EndPoint;
import io.cucumber.datatable.DataTable;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Put;

import java.util.List;

public class ActualizarUsuario implements Task {

    List<String> body;
    String endPoint;
    String user_id;

    public ActualizarUsuario(List<String> body, String endPoint, String user_id) {
        this.body = body;
        this.endPoint = endPoint;
        this.user_id = user_id;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        endPoint = EndPoint.obtenerUri(endPoint);
        actor.attemptsTo(Put.to(endPoint));
    }

    public static ActualizarUsuario conLosDatos(DataTable body, String endPoint , String user_id){
        return Tasks.instrumented(ActualizarUsuario.class, body,endPoint,user_id);
    }
}
