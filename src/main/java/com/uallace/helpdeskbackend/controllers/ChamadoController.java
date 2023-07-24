package com.uallace.helpdeskbackend.controllers;

import com.uallace.helpdeskbackend.domain.dtos.ChamadoDTO;
import com.uallace.helpdeskbackend.domain.dtos.TecnicoDTO;
import com.uallace.helpdeskbackend.domain.entities.Chamado;
import com.uallace.helpdeskbackend.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chamados")
public class ChamadoController {

    @Autowired
    private ChamadoService chamadoService;

    @GetMapping("/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id){
        Chamado chamado = chamadoService.findById(id);
        return ResponseEntity.ok().body(new ChamadoDTO(chamado));
    }

}
