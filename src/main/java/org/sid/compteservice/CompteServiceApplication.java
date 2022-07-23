package org.sid.compteservice;

import java.util.Date;

import org.sid.compteservice.entites.Compte;
import org.sid.compteservice.enums.TypeCompte;
import org.sid.compteservice.repositories.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CompteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompteServiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(CompteRepository compteRepository, RepositoryRestConfiguration restConfiguration){
		return args -> {
			restConfiguration.exposeIdsFor(Compte.class);
			compteRepository.save(new Compte(null, 12500, new Date(), TypeCompte.COURANT));
			compteRepository.save(new Compte(null, 24000, new Date(), TypeCompte.EPARGNE));
			compteRepository.save(new Compte(null, 54000, new Date(), TypeCompte.COURANT));	
			
			compteRepository.findAll().forEach(cp -> {
				System.out.println(cp.getType());
				System.out.println(cp.getSolde());
			});
		};
	}

}
