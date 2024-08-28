package clearcb.imin.BusinessApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BusinessApiApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BusinessApiApplication.class, args);

    }

}
