package com.uallace.helpdeskbackend.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uallace.helpdeskbackend.domain.dtos.ClienteDTO;
import com.uallace.helpdeskbackend.domain.enums.Perfil;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Cliente extends Pessoa{

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Chamado> chamados = new ArrayList<>();

    public Cliente(){
        super();
        addPerfil(Perfil.CLIENTE);
    }

    public Cliente(ClienteDTO clienteDTO) {
        this.id = clienteDTO.getId();
        this.nome = clienteDTO.getNome();
        this.cpf = clienteDTO.getCpf();
        this.email = clienteDTO.getEmail();
        this.senha = clienteDTO.getSenha();
        this.perfil = clienteDTO.getPerfil().stream().map(p -> p.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = clienteDTO.getDataCriacao();
    }


    public Cliente(Integer id, String nome, String cpf,String email, String senha) {
        super(id, nome, cpf,email, senha);
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}

