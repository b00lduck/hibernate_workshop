package tarent;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tarent.demo.ExampleComponent;

@SpringBootApplication
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(final ExampleComponent exampleComponent) {
        return (args) -> {

            // Logging example
            //exampleComponent.Example1();

            // Persist simple entity
            //exampleComponent.Example2();

            // Simple entity with embedded field
            //exampleComponent.Example3();

            // Simple entity with embedded list of strings
            //exampleComponent.Example4();

            // OneToOne shared PK example
            //exampleComponent.Example5();

            // OneToMany FK join unidirectional example
            //exampleComponent.Example6();

            // OneToMany FK join bidirectional example
            //exampleComponent.Example7();

            // Generate some data and issue some queries
            exampleComponent.Example8();

        };
    }

}
