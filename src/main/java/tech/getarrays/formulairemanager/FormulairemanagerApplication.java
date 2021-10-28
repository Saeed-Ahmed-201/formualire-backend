package tech.getarrays.formulairemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@PropertySource(value ="classpath:application.properties")
@EnableJpaRepositories  
public class FormulairemanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FormulairemanagerApplication.class, args);
	}

}
