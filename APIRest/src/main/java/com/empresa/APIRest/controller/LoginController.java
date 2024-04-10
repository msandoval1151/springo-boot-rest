package com.empresa.APIRest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.APIRest.model.LoginRequest;
import com.empresa.APIRest.service.AutentificacionService;
import com.empresa.APIRest.service.DivisaService;
import com.google.gson.Gson;

@RestController
@RequestMapping("auth")
public class LoginController {
	
	@Autowired
	private AutentificacionService autentificacionService;
	@Autowired
    private DivisaService divisaService;
	@Autowired
	private Gson gson;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST,
		    produces =  MediaType.APPLICATION_JSON_VALUE) 
	public String iniciar(@RequestBody LoginRequest request)
    {
		if(divisaService.consultarDivisa().isEmpty()){
			divisaService.crearDivisa();
		}
		return gson.toJson(autentificacionService.login(request));
    }
	
}
