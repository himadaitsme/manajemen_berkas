package kpp.payakumbuh.manajemenberkas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@SpringBootApplication
public class ManajemenBerkasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManajemenBerkasApplication.class, args);
	}
	
	public LayoutDialect layoutDialect(){
        return new LayoutDialect();
    }
}
