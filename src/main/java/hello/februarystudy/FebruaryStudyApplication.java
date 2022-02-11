package hello.februarystudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
public class FebruaryStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(FebruaryStudyApplication.class, args);
	}

}
