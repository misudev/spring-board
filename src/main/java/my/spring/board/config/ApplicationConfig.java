package my.spring.board.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = { "my.spring.board.dao", "my.spring.board.service" })
@Import({DataSourceConfig.class})
public class ApplicationConfig {
}
