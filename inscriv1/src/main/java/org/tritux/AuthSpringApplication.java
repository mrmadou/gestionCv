package org.tritux;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tritux.dao.ExperienceRepo;
import org.tritux.dao.OffreRepo;
import org.tritux.dao.ProfilRepo;
import org.tritux.dao.UserRepo;
import org.tritux.entites.Admin;
import org.tritux.entites.Candidat;
import org.tritux.entites.Exeprience;
import org.tritux.entites.Offre;
import org.tritux.entites.Profil;
import org.tritux.entites.Recruteur;
import org.tritux.entites.User;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class AuthSpringApplication {

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		org.springframework.context.ApplicationContext ctx = SpringApplication
				.run(AuthSpringApplication.class, args);

		UserRepo userRepo = ctx.getBean(UserRepo.class);
		ProfilRepo profilRepo = ctx.getBean(ProfilRepo.class);
		ExperienceRepo experienceRepo = ctx.getBean(ExperienceRepo.class);
		OffreRepo offreRepo=ctx.getBean(OffreRepo.class);
		
		
		userRepo.save(new Admin("admin2", "root", "admin", "admin2"));
		userRepo.save(new Admin("admin", "root", "admin", "super admin"));
		userRepo.save(new Recruteur("recr", "recr", "recruteur",
				"super Recruteur", "service informatique"));

		Candidat c = new Candidat("cand", "cand", "candidat");
		userRepo.save(c);
		
		Recruteur r= new Recruteur("aa", "bb");
		userRepo.save(r);
		Offre o=new Offre("ingnieur", "ingénieur telecom", null);
		
		o.setDeposeur(r);
		offreRepo.save(o);
		
		c.getListCandidatures().add(o);
		o.getListCandidatureOffre().add(c);
		//offreRepo.save(o);
		//userRepo.save(c);
		System.out.println(c);
		
		// List<User> etds = userRepo.findAll();
		// etds.forEach(e -> System.out.println(e.getLogin()));

		Profil p = new Profil();
		p.setNom("mehdi");
		p.setAge(25);
		p.setEmail("mohamed.slama@esprit.tn");
		profilRepo.save(p);

		c.setProfil(p);

		Exeprience e1= new Exeprience(1, "angularjs");
		Exeprience e2= new Exeprience(1, "spring");
		e1.setProfil(p);
		e2.setProfil(p);
		experienceRepo.save(e1);
		experienceRepo.save(e2);
		
	
		
		profilRepo.save(p);
		
		p.getExperiences().add(e1);
		p.getExperiences().add(e2);
		
		//Diplomes d1=new Diplomes("BAC", 1235,p);
		//p.getDiplomes().add(d1);
		
		profilRepo.save(p);
		
		userRepo.save(c);
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("profil to exepreince "+profilRepo.findOne((long) 1));
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
		
		//System.out.println(profilRepo.findOne((long) 1));
	}
}
