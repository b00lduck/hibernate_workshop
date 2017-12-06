package tarent;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tarent.demo.DemoComponent;

@SpringBootApplication
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(final DemoComponent dc) {
        return (args) -> {
            dc.createCustomers();
            dc.addOrderToFirstCustomer();
            dc.printCustomers();
        };
    }

}
