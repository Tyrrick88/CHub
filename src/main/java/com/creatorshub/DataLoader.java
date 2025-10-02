package com.creatorshub;

import com.creatorshub.model.Gig;
import com.creatorshub.model.User;
import com.creatorshub.repository.GigRepository;
import com.creatorshub.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner init(UserRepository userRepo, GigRepository gigRepo, PasswordEncoder encoder) {
        return args -> {
            if (userRepo.count() == 0) {
                User u = new User();
                u.setUsername("alice");
                u.setPassword(encoder.encode("password"));
                u.setFullName("Alice Creator");
                u.setRoles(Set.of("ROLE_USER"));
                userRepo.save(u);

                Gig g = new Gig();
                g.setTitle("Logo Design");
                g.setDescription("Professional minimalist logo design");
                g.setPrice(30.0);
                g.setCategory("Design");
                g.setOwnerId(u.getId());
                gigRepo.save(g);
            }
        };
    }
}
