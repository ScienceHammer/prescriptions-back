package app.core.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.core.entities.Prescription;
import app.core.entities.User;
import app.core.services.UserService;

@RestController
@RequestMapping("/api/patient")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/hello")
	public String home(@RequestHeader String Authorization) {
		return ("<h1>Patient</h1>");
	}

	@GetMapping("/getAllPrescription")
	public List<Prescription> getAllPrescription(@RequestHeader String Authorization, Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		return userService.getAllPrescriptions(user.getId());
	}


}
