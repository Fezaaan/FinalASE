package dhbw.ase;

import dhbw.ase.*;
import dhbw.domain.GroceryItemEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("dhbw.*")
@EnableJpaRepositories(basePackages ="dhbw.ase.*")
@ComponentScan(basePackages = "dhbw.*")
public class ListItCrudMavenApplication {

    public static void main(String[] args) {
        System.out.println("Start");
        SpringApplication.run(ListItCrudMavenApplication.class, args);
    }

}
