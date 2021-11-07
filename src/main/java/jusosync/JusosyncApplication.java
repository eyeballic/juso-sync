package jusosync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JusosyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(JusosyncApplication.class, args);
	}

}
