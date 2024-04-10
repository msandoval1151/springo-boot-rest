package com.empresa.APIRest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.empresa.APIRest.jwt.JwtService;
import com.empresa.APIRest.model.AuthResponse;
import com.empresa.APIRest.model.LoginRequest;
import com.empresa.APIRest.model.Role;
import com.empresa.APIRest.model.User;
import com.empresa.APIRest.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutentificacionService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;

	public AuthResponse login(LoginRequest request) {
		AuthResponse authResponse = new AuthResponse();
		List<User>  listadoUser = userRepository.findAll();
		if(listadoUser.isEmpty()) {
			registrar();
		}
		
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
		String token = jwtService.getToken(user);
		authResponse.setToken(token);
		return authResponse;

	}

	public AuthResponse registrar() {
		AuthResponse authResponse = new AuthResponse();
		User user = new User();
		user.setUsername("admin");
		user.setPassword(passwordEncoder.encode("12345678"));
		user.setRole(Role.USER);
		userRepository.save(user);

		return authResponse;

	}

}