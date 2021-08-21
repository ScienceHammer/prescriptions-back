package app.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.core.entities.User;
import app.core.models.CustomeUserDetails;
import app.core.security.JwtTokenProvider;
import app.core.security.SecurityConstrains;
import app.core.services.UserAuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class UserAuthController {
	
	@Autowired
	private UserAuthService customeUserDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@PostMapping("/register")
	public CustomeUserDetails registerUser(@RequestBody User user) {
		user.setActive(true);
		user.setRegistered(true);
		User dbUser = customeUserDetailsService.saveUser(user);
		CustomeUserDetails customeUser = new CustomeUserDetails(dbUser);
		String jwt = SecurityConstrains.TOKEN_PREFIX + jwtTokenProvider.generateToken(customeUser);
		customeUser.setToken(jwt); 
		return customeUser; 
	}
	
	@PostMapping("/login")
	public CustomeUserDetails loginUser(@RequestParam String username, @RequestParam String password) {
		System.out.println("------------------------");
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(username, password));
		System.out.println("------------------------");
		CustomeUserDetails user = (CustomeUserDetails) authentication.getPrincipal();
		System.out.println(user);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = SecurityConstrains.TOKEN_PREFIX + jwtTokenProvider.generateToken((CustomeUserDetails) authentication.getPrincipal());
		user.setToken(jwt); 
		return user; 
	}

}
