package com.unideb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class Main {

    // start everything
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    // run this only on profile 'demo', avoid run this in test
    @Profile("demo")
    @Bean
    CommandLineRunner initDatabase(StudentRepository repository) {
        return args -> {
            repository.save(new Student("Nagy", "Norbert", 20, "DE", "IK", false));
            repository.save(new Student("Kocsis", "József", 24, "BME", "GTK", false));
            repository.save(new Student("Huszti", "Anna", 21, "SZTE", "ETSZK", false));
            repository.save(new Student("Parker", "Josh", 21, "DE", "BTK", true));
            repository.save(new Student("Ferenczi", "Ilona", 20, "ELTE", "TTK", false));
            repository.save(new Student("Chiraff", "Hassan", 23, "DE", "MK", true));
            repository.save(new Student("Micskai", "Andrea", 24, "ME", "EK", false));
            repository.save(new Student("Farkas", "Bertalan", 25, "BME", "IK", false));
            repository.save(new Student("Kisfalvi", "Fatime", 20, "DE", "GTK", false));
            repository.save(new Student("Smith", "Adam", 23, "ELTE", "BTK", true));
        };
    }
}