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

public class ListarUsuarios implements Task {


    String endPoint;
    String page;
    Integer pageValue;
    String perPage;
    Integer perPageValue;

    public ListarUsuarios(String endPoint, String page, Integer pageValue, String perPage, Integer perPageValue) {
        this.endPoint = endPoint;
        this.page = page;
        this.pageValue = pageValue;
        this.perPage = perPage;
        this.perPageValue = perPageValue;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        endPoint = EndPoint.obtenerUri(endPoint);
        endPoint = endPoint + page + pageValue + perPage + perPageValue;
        actor.attemptsTo(Get.resource(endPoint));

        Serenity.setSessionVariable(RESPONSE.getMsj()).to(SerenityRest.lastResponse().body());
        mensaje().info(RESPONSE.getMsj());
        SerenityRest.lastResponse().body().prettyPrint();

    }

    public static ListarUsuarios conLosDatos(String endPoint, String page, Integer pageValue, String perPage, Integer perPageValue) {
        return Tasks.instrumented(ListarUsuarios.class, endPoint, page, pageValue, perPage, perPageValue);
    }

}
