package com.uallace.helpdeskbackend.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uallace.helpdeskbackend.domain.entities.Tecnico;
import com.uallace.helpdeskbackend.domain.enums.Perfil;
import jakarta.validation.constraints.NotNull;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TecnicoDTO implements Serializable {

    protected Integer id;

    @NotNull(message = "Campo NOME é requerido!")
    protected String nome;

    @NotNull(message = "Campo CPF é requerido!")
    protected String cpf;

    @NotNull(message = "Campo EMAIL é requerido!")
    protected String email;

    @NotNull(message = "Campo SENHA é requerido!")
    protected String senha;

    protected Set<Integer> perfil = new HashSet<>();
    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now();

    public TecnicoDTO(){
        super();
        addPerfil(Perfil.CLIENTE);
    }

    public TecnicoDTO(Tecnico tecnico) {
        this.id = tecnico.getId();
        this.nome = tecnico.getNome();
        this.cpf = tecnico.getCpf();
        this.email = tecnico.getEmail();
        this.senha = tecnico.getSenha();
        this.perfil = tecnico.getPerfil().stream().map(p -> p.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = tecnico.getDataCriacao();
        addPerfil(Perfil.CLIENTE);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Perfil> getPerfil() {

        return perfil.stream().map(p -> Perfil.toEnum(p)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        this.perfil.add(perfil.getCodigo());
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}