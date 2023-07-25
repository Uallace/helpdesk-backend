package com.uallace.helpdeskbackend.controllers;


import com.uallace.helpdeskbackend.domain.dtos.TecnicoDTO;
import com.uallace.helpdeskbackend.domain.entities.Tecnico;
import com.uallace.helpdeskbackend.services.TecnicoService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/tecnicos")
public class TecnicoController {

    @Autowired
    private TecnicoService tecnicoService;

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll(){
        List<Tecnico> list = tecnicoService.findAll();
        List<TecnicoDTO> listDTO = list.stream().map(t -> new TecnicoDTO(t)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable("id") Integer id){
        Tecnico tecnico = tecnicoService.findById(id);
        return ResponseEntity.ok().body(new TecnicoDTO(tecnico));
    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO dto){
        Tecnico tecnico = tecnicoService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(tecnico.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id,@Valid @RequestBody TecnicoDTO dto){
        Tecnico tecnico = tecnicoService.update(id,dto);
        return  ResponseEntity.ok().body(new TecnicoDTO(tecnico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TecnicoDTO> delete(@PathVariable Integer id){
        tecnicoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

