package ma.emsi.patientsmvc;

import ma.emsi.patientsmvc.entities.Patient;
import ma.emsi.patientsmvc.repositories.PatientRepositoriy;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientsMvcApplication.class, args);
    }

    //@Bean
    CommandLineRunner commandLineRunner(PatientRepositoriy patientRepositoriy){
        return args -> {

            patientRepositoriy.save(
                    new Patient(null,"Anas",new Date(),false,112));
            patientRepositoriy.save(
                    new Patient(null,"Hassane",new Date(),true,212));
            patientRepositoriy.save(
                    new Patient(null,"Aymane",new Date(),true,412));
            patientRepositoriy.save(
                    new Patient(null,"Hanaa",new Date(),false,132));

            patientRepositoriy.findAll().forEach(p -> {
                System.out.println(p.getNom());
            });

        };
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}