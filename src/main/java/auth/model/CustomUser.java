package auth.model;

import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUser extends User{
	private static final long serialVersionUID = 1L;

	public CustomUser(UserEntity user) {
		super(user.getUsername(), user.getPassword(), List.of(new SimpleGrantedAuthority(user.getAuthority())));
	}
}
