package com.empresa.APIRest.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.empresa.APIRest.model.CambioResponse;
import com.empresa.APIRest.model.Cotizacion;
import com.empresa.APIRest.model.Divisa;
import com.empresa.APIRest.repository.CotizacionRepository;
import com.empresa.APIRest.repository.DivisaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DivisaService {

	@Autowired
	private DivisaRepository divisaRepository;

	@Autowired
	private CotizacionRepository cotizacionRepository;
	
	public String crearDivisa() {

		Divisa divisa = new Divisa();
		ObjectMapper objectMapper = new ObjectMapper();
		List<Divisa> lista = divisaRepository.findAll();
		String usuarioJson = "";
		try {
			Divisa divisa1 = new Divisa();
			divisa1.setEquivalencia(3.55);
			divisaRepository.save(divisa1);
			Divisa divisa2 = new Divisa();
			divisa2.setEquivalencia(4.10);
			divisaRepository.save(divisa2);
			Divisa divisa3 = new Divisa();
			divisa.setEquivalencia(10.15);
			divisaRepository.save(divisa3);
			usuarioJson = objectMapper.writeValueAsString(lista);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return usuarioJson;
	}

	public List<Divisa> consultarDivisa() {
		return divisaRepository.findAll();
	}

	@SuppressWarnings("finally")
	public Cotizacion procesarCambio(Long id, double montoOrigen,String username) {
		
		double tipoCambio = 0;
		double montoTotal= 0;
		Cotizacion cotizacion = new Cotizacion();
		
		try {
			Optional<Divisa> divisa = divisaRepository.findById(id);
			tipoCambio = divisa.get().getEquivalencia();
			montoTotal = montoOrigen*tipoCambio;
			cotizacion.setMontoDestino(montoTotal);
			cotizacion.setMontoOrigen(montoOrigen);
			cotizacion.setTipoCambio(tipoCambio);
			cotizacion.setEstado(true);
			cotizacion.setUsuarioCreacion(username);
			cotizacion.setFechaCreacion(LocalDate.now().toString());
			cotizacion = cotizacionRepository.save(cotizacion);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			return cotizacion;
		}
	}
	public List<Cotizacion> buscarProcesos() {
		return cotizacionRepository.findAll();
	}
}
