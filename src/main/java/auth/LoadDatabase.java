package auth;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import auth.dto.UserRepository;
import auth.model.UserEntity;

@Configuration
class LoadDatabase {

	@Bean
	CommandLineRunner initDatabase(UserRepository userRepo) {
		return args -> {
			userRepo.save(new UserEntity("User", passwordEncoder().encode("password"), "ROLE_USER"));
			userRepo.save(new UserEntity("Admin", passwordEncoder().encode("password"), "ROLE_ADMIN"));
		};
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
