package app.core.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.core.entities.User;
import app.core.enums.Roles;
import app.core.repositories.UserRepository;

@Service
@Transactional
public class GuestService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllDoctors() {
		return userRepository.findByRole(Roles.ROLE_DOC);
	}

}
