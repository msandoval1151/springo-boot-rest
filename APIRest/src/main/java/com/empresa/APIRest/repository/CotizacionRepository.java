package com.empresa.APIRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.APIRest.model.Cotizacion;

public interface CotizacionRepository extends JpaRepository<Cotizacion,Long>{

}
