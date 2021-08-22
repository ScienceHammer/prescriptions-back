package app.core.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import app.core.entities.Prescription;
import app.core.entities.User;
import app.core.services.DoctorService;

@RestController
@CrossOrigin
@RequestMapping("/api/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;


	@PostMapping("/addPrescription")
	public Prescription addPrescription(@RequestHeader String Authorization, Authentication authentication,
			@RequestBody Prescription prescription) {
		System.out.println("Prescription: " + prescription.toString());
		User doctor = (User) authentication.getPrincipal();
		prescription.setDoctor(doctor);
		return doctorService.addPrescription(prescription);
	}
	
	@GetMapping("/findUserByUserIdNumber")
	@ResponseBody
	public List<User> findByUserIdNumber(@RequestHeader String Authorization) {
		return doctorService.findUsersByUserIdNumber();
	}

	@GetMapping("/getAllDoctorPrescriptions")
	public List<Prescription> getAllDoctorPrescriptions(@RequestHeader String Authorization, Authentication authentication) {
		User doctor = (User) authentication.getPrincipal();
		return doctorService.getAllDoctorPrescriptions(doctor.getId());
	}

}
