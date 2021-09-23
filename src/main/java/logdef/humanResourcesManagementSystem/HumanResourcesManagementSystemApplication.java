package logdef.humanResourcesManagementSystem;

import java.util.Date;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class HumanResourcesManagementSystemApplication {
	
	@PostConstruct
    public void init() {

        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Istanbul"));

        System.out.println("Date in UTC: " + new Date().toString());
    }
	

	public static void main(String[] args) { 
		SpringApplication.run(HumanResourcesManagementSystemApplication.class, args);
	}
	
	@Bean
	public Docket api() { 
		
		
		
	    return new Docket(DocumentationType.SWAGGER_2)  
	          .select()                                  
	          .apis(RequestHandlerSelectors.basePackage("logdef.humanResourcesManagementSystem")) 
	          .paths(PathSelectors.any())                          
	          .build();                                           
	}
}
