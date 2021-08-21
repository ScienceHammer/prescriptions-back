package app.core.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import app.core.entities.User;
import app.core.enums.Roles;
import app.core.models.CustomeUserDetails;
import app.core.repositories.UserRepository;

@Service
@Transactional
public class CustomeUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = new User();
		if (username.equals("admin")) {
			user.setUsername(username);
			user.setPassword(bCryptPasswordEncoder.encode("admin"));
			user.setRole(Roles.ROLE_ADMIN);
			user.setId((long) 0);
		} else {			
			Optional<User> optional = userRepository.findByUsername(username);
			if (!optional.isPresent()) {
				throw new UsernameNotFoundException("Not Found: " + username);
			}
			user = optional.get();
		}
		return new CustomeUserDetails(user);
	}

	
	public User loadUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			new UsernameNotFoundException("User not found");
		}
		return user.get();

	}
 
	public User saveUser(User newUser) {
		newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
		return userRepository.save(newUser);
	}

}
