package ManishLokesh.Neptune;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"ManishLokesh.Neptune.v1","ManishLokesh.Neptune.AuthController","ManishLokesh.Neptune.v2","ManishLokesh.Neptune.EmailTrigger"})
public class NeptuneApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeptuneApplication.class, args);
	}

}
