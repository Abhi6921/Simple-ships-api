package nl.inholland.ships.shipsapi.IT.steps;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(classes = CucumberContextConfig.class)
@Slf4j
public class BaseStepDefinations {

    @LocalServerPort
    private int port;

    @Value("${nl.inholland.ships.shipsapi}")
    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl + port;
    }

}
