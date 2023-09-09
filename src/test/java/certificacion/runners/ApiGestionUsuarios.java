package certificacion.runners;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        glue = {"certificacion.stepdefinitions", "certificacion.setup"},
        features = "src/test/resources/features/api_gestion_usuarios.feature",
        tags = "@ServiciosRest",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        monochrome = true)
public class ApiGestionUsuarios {

}
