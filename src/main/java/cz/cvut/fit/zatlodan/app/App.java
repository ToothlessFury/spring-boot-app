package cz.cvut.fit.zatlodan.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by jack on 18.12.16.
 */

@SpringBootApplication
@ComponentScan(basePackages = {"cz.cvut.fit.zatlodan"})
@EntityScan(basePackages = {"cz.cvut.fit.zatlodan.models"})
@EnableJpaRepositories(basePackages = {"cz.cvut.fit.zatlodan.repositories"})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}/**/
