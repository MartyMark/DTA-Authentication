package auth.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class UserEntity {
	
	private @Id String username;
	private String password;
	private String authority;
	
	public UserEntity() {}
	
	public UserEntity(String username, String password, String authority) {
		this.username = username;
		this.password = password;
		this.authority = authority;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}
	
	public void setAuthorities(String authority) {
		this.authority = authority;
	}
}
