package com.uallace.helpdeskbackend.repositories;

import com.uallace.helpdeskbackend.domain.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
