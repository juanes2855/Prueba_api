package certificacion.tasks;
import certificacion.models.Usuario;
import certificacion.enums.EndPoint;
import certificacion.utilities.CrearBody;
import certificacion.utilities.Obtener;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import net.serenitybdd.core.Serenity;
import java.util.List;
import static certificacion.enums.ConfiguracionAmbiente.CONSTANT_ENV;
import static certificacion.enums.DatosGenerales.*;
import static certificacion.utilities.Obtener.transactionChannelId;
import static certificacion.utilities.ObtenerLogger.mensaje;
public class CrearUsuario implements Task{


    List<Usuario> body;
    String endPoint;

    public CrearUsuario(List<Usuario> body, String endPoint) {
        this.body = body;
        this.endPoint = endPoint;
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        endPoint = EndPoint.obtenerUri(endPoint);
        actor.attemptsTo(Post.to(endPoint)
                .with(requestSpecification -> requestSpecification.headers(Obtener.valorCabecera(System.getProperty(CONSTANT_ENV.getMsj())))
                        .body(CrearBody.conLaPlantilla(RUTA_BODY.getMsj())
                                .yLosValores(body)).relaxedHTTPSValidation()));
        Serenity.setSessionVariable(TRANSACTION_ID_VALOR.getMsj()).to(LastResponse.received().answeredBy(actor).jsonPath().getString(DATA_TRANSACTION_ID.getMsj()));
        mensaje().info(RESPONSE.getMsj());
        SerenityRest.lastResponse().body().prettyPrint();
    }
    public static CrearUsuario conLosDatos(List<Usuario> body, String endPoint) {
        return Tasks.instrumented(CrearUsuario.class, body, endPoint);
    }
}