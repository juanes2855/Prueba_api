package certificacion.questions;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ObtenerRespuesta implements Question<Integer> {
    public static ObtenerRespuesta codigo() {
        return new ObtenerRespuesta();
    }
    @Override
    public Integer answeredBy(Actor actor) {
        return SerenityRest.lastResponse().getStatusCode();
    }
}