package app.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.core.entities.Medicine;
import app.core.services.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@GetMapping("/hello")
	public String home(@RequestHeader String Authorization) {
		return ("<h1>Admin</h1>");
	}

	@PostMapping("/addMedicine")
	public Medicine addMedicine(@RequestBody Medicine medicine) {
		return adminService.addMedicine(medicine);
	}

}
