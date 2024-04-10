package com.empresa.APIRest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cotizacion")
public class Cotizacion {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	double montoOrigen;
	double montoDestino;
	double tipoCambio;
	boolean estado;
	String usuarioCreacion;
	String usuarioModificacion;
	String fechaCreacion;
	String fechaModicacion;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getMontoOrigen() {
		return montoOrigen;
	}
	public void setMontoOrigen(double montoOrigen) {
		this.montoOrigen = montoOrigen;
	}
	public double getMontoDestino() {
		return montoDestino;
	}
	public void setMontoDestino(double montoDestino) {
		this.montoDestino = montoDestino;
	}
	public double getTipoCambio() {
		return tipoCambio;
	}
	public void setTipoCambio(double tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}
	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getFechaModicacion() {
		return fechaModicacion;
	}
	public void setFechaModicacion(String fechaModicacion) {
		this.fechaModicacion = fechaModicacion;
	}
	
}
