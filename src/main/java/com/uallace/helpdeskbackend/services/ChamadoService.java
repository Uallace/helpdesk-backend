package com.uallace.helpdeskbackend.services;


import com.uallace.helpdeskbackend.domain.entities.Chamado;
import com.uallace.helpdeskbackend.exceptions.NotFoundException;
import com.uallace.helpdeskbackend.repositories.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    public Chamado findById(Integer id){
        Optional<Chamado> chamado = chamadoRepository.findById(id);
        return chamado.orElseThrow(() -> new NotFoundException("Objeto não encontrado! id: " + id));
    }
}
