package app.core.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import app.core.entities.Prescription;
import app.core.entities.User;
import app.core.services.DoctorService;

@RestController
@CrossOrigin
@RequestMapping("/api/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;

	public String welcom() {
		return "welcome from doctor";
	}
	
	@GetMapping("/hello") 
	public String hello(@RequestHeader String Authorization, Authentication authentication) {
//		User user = (User) authentication.getPrincipal();
//		System.out.println(user);
		return "Doctor";
	}

	@PostMapping("/addPrescription")
	public Prescription addPrescription(@RequestHeader String Authorization, Authentication authentication,
			@RequestBody Prescription prescription) {
		User doctor = (User) authentication.getPrincipal();
		prescription.setDoctor(doctor);
		return doctorService.addPrescription(prescription);
	}
	
	@GetMapping("/findUserByUserIdNumber")
	@ResponseBody
	public List<User> findByUserIdNumber(@RequestHeader String Authorization, Principal principal) {
		System.out.println(principal); 
		return doctorService.findUsersByUserIdNumber();
	}

}
