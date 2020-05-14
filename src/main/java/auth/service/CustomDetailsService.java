package auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import auth.dto.UserRepository;
import auth.model.CustomUser;
import auth.model.UserEntity;

@Service
public class CustomDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepo;
	
	@Override
	public CustomUser loadUserByUsername(final String username) throws UsernameNotFoundException {
		UserEntity user = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

		return new CustomUser(user);
	}
}
