package fm.statify.backend_service;

import fm.statify.backend_service.util.HTTPHelper;
import fm.statify.backend_service.util.Parser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public HTTPHelper createHttpHelper() {
        return new HTTPHelper();
    }

    @Bean
    public Parser createParser() {
        return new Parser();
    }
}
