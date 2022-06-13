package nl.inholland.ships.shipsapi.IT.steps;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "nl.inholland.ships.shipsapi.IT.steps",
        plugin = "pretty",
        publish = true)
@ContextConfiguration
public class CucumberIT {
}
