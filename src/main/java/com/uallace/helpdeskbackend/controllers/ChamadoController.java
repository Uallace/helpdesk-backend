package com.uallace.helpdeskbackend.controllers;

import com.uallace.helpdeskbackend.domain.dtos.ChamadoDTO;
import com.uallace.helpdeskbackend.domain.dtos.TecnicoDTO;
import com.uallace.helpdeskbackend.domain.entities.Chamado;
import com.uallace.helpdeskbackend.services.ChamadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chamados")
public class ChamadoController {

    @Autowired
    private ChamadoService chamadoService;

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll(){
        List<Chamado> list = chamadoService.findAll();
        List<ChamadoDTO> listDTO = list.stream().map(c -> new ChamadoDTO(c)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id){
        Chamado chamado = chamadoService.findById(id);
        return ResponseEntity.ok().body(new ChamadoDTO(chamado));
    }

    @PostMapping
    public ResponseEntity<ChamadoDTO> create(@Valid @RequestBody ChamadoDTO dto){
        Chamado chamado = chamadoService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(chamado.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChamadoDTO> update(@PathVariable Integer  id, @Valid @RequestBody ChamadoDTO dto){
        Chamado chamado = chamadoService.update(id,dto);
        return ResponseEntity.ok().body(new ChamadoDTO(chamado));
    }


}
