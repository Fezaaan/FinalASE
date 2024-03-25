package dhbw.ase;

import dhbw.ase.*;
import dhbw.domain.GroceryItemEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("dhbw.domain.GroceryItemEntity")
public class ListItCrudMavenApplication {

    public static void main(String[] args) {
        SpringApplication.run(ListItCrudMavenApplication.class, args);
    }

}
