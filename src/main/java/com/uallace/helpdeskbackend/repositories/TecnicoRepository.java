package com.uallace.helpdeskbackend.repositories;


import com.uallace.helpdeskbackend.domain.entities.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

}
