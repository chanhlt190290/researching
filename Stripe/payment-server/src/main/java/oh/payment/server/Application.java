package oh.payment.server;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

//            System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//            String[] beanNames = ctx.getBeanDefinitionNames();
//            Arrays.sort(beanNames);
//            for (String beanName : beanNames) {
//                System.out.println(beanName);
//            }


            // Set your secret key: remember to change this to your live secret key in production
            // See your keys here: https://dashboard.stripe.com/account/apikeys
//            Stripe.apiKey = "sk_test_Zyf3eFu08S48AQrwI9J6r64z";
//
//            Map<String, Object> params = new HashMap<>();
//            params.put("amount", 999);
//            params.put("currency", "usd");
//            params.put("source", "tok_visa");
//            params.put("receipt_email", "jenny.rosen@example.com");
//            Charge charge = Charge.create(params);
//            
//            System.out.println(charge.toJson());
        };
    }

}
