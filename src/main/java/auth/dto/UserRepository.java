package auth.dto;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import auth.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
	Optional<UserEntity> findByUsername(String username);
}
