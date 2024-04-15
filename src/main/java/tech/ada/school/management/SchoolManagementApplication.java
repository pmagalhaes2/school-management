package tech.ada.school.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SchoolManagementApplication {

	@RequestMapping("/")
	@ResponseBody
	String home(){
		return "Hello, world!";
	}


	public static void main(String[] args) {
		SpringApplication.run(SchoolManagementApplication.class, args);
	}

}
