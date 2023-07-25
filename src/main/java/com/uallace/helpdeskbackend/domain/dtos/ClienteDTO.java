package com.uallace.helpdeskbackend.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uallace.helpdeskbackend.domain.entities.Cliente;
import com.uallace.helpdeskbackend.domain.enums.Perfil;


import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClienteDTO implements Serializable {

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

    public ClienteDTO(){
        super();
        addPerfil(Perfil.CLIENTE);
    }

    public ClienteDTO(Cliente Cliente) {
        this.id = Cliente.getId();
        this.nome = Cliente.getNome();
        this.cpf = Cliente.getCpf();
        this.email = Cliente.getEmail();
        this.senha = Cliente.getSenha();
        this.perfil = Cliente.getPerfil().stream().map(p -> p.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = Cliente.getDataCriacao();
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
