package com.company.pgi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class PgiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PgiApplication.class, args);
	}



    //Gera uma senha criptografada 
	//     public static void main(String[] args) {
    //     // Crie uma instância do BCryptPasswordEncoder
    //     BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //     // Senha a ser criptografada
    //     String rawPassword = "123456";

    //     // Gera o hash da senha
    //     String encodedPassword = passwordEncoder.encode(rawPassword);

    //     // Exibe a senha criptografada
    //     System.out.println("Senha original: " + rawPassword);
    //     System.out.println("Senha criptografada (BCrypt): " + encodedPassword);
    // }

}
