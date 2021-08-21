package app.core.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entities.User;
import app.core.enums.Roles;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);
	List<User> findByRole(Roles role);
	List<User> findByUserIdNumberContains(String userIdNumber);
	List<User> findByFirstNameOrLastName(String firstName, String lastName);
	
}
