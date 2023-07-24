package com.uallace.helpdeskbackend.repositories;

import com.uallace.helpdeskbackend.domain.entities.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

}