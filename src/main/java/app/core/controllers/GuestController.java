package app.core.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.core.entities.User;
import app.core.services.GuestService;

@RestController
@RequestMapping("/api/guest")
@CrossOrigin
public class GuestController {
	
	@Autowired
	private GuestService guestService;
	
	@GetMapping("/getAllDoctors")
	public List<User> getAllDoctors() {
		return guestService.getAllDoctors();
	}

}
