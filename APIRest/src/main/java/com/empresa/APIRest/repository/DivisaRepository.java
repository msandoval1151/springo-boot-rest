package com.empresa.APIRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empresa.APIRest.model.Divisa;

@Repository
public interface  DivisaRepository extends JpaRepository<Divisa,Long> {

}
