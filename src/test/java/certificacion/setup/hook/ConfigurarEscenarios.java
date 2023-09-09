package certificacion.setup.hook;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnlineCast;
import static certificacion.enums.Actor.VALIDADOR_SERVICIOS;
import static net.serenitybdd.screenplay.actors.OnStage.setTheStage;
import static net.serenitybdd.screenplay.actors.OnStage.theActor;

public class ConfigurarEscenarios {
    @Before("@ServiciosRest")
    public void prepararEscenarioServicioRest() {
        setTheStage(new OnlineCast());
        theActor(VALIDADOR_SERVICIOS.getNombre());
    }
}

