package com.uallace.helpdeskbackend.controllers;


import com.uallace.helpdeskbackend.domain.dtos.ClienteDTO;
import com.uallace.helpdeskbackend.domain.entities.Cliente;
import com.uallace.helpdeskbackend.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll(){
        List<Cliente> list = clienteService.findAll();
        List<ClienteDTO> listDTO = list.stream().map(t -> new ClienteDTO(t)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable("id") Integer id){
        Cliente cliente = clienteService.findById(id);
        return ResponseEntity.ok().body(new ClienteDTO(cliente));
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO dto){
        Cliente cliente = clienteService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id,@Valid @RequestBody ClienteDTO dto){
        Cliente cliente = clienteService.update(id,dto);
        return  ResponseEntity.ok().body(new ClienteDTO(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteDTO> delete(@PathVariable Integer id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

