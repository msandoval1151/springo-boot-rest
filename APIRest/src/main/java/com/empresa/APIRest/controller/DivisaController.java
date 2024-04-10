package com.empresa.APIRest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.APIRest.jwt.JwtService;
import com.empresa.APIRest.service.DivisaService;
import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("divisas")
public class DivisaController {

	@Autowired
	private DivisaService divisaService;
	@Autowired
	private Gson gson;
	@Autowired
	private JwtService jwtService;

	@RequestMapping(value = "/consultarCambio", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String consultar() {
		return gson.toJson(divisaService.consultarDivisa());
	}

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String iniciar() {
		return gson.toJson("Bienvenido al sistema de inicio de LA EMPRESA");
	}

	@RequestMapping(value = "/procesarCambio", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String procesar(Long id, double monto, HttpServletRequest request) {
		String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		String token = "";
		if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
		}
		String username = jwtService.getUsernameFromToken(token);
		return gson.toJson(divisaService.procesarCambio(id, monto, username));
	}

	@RequestMapping(value = "/buscarProcesos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String buscarProcesos() {
		return gson.toJson(divisaService.buscarProcesos());
	}
}
