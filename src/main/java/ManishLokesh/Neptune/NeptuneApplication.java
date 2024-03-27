package ManishLokesh.Neptune;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan({"ManishLokesh.Neptune.v1","ManishLokesh.Neptune.v2","ManishLokesh.Neptune.AuthController","ManishLokesh.Neptune.v2","ManishLokesh.Neptune.EmailTrigger","ManishLokesh.Neptune.Scheduler"})
@EnableScheduling
public class NeptuneApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeptuneApplication.class, args);
	}

}
