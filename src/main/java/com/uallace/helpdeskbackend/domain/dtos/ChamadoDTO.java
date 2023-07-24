package com.uallace.helpdeskbackend.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uallace.helpdeskbackend.domain.entities.Chamado;
import jakarta.validation.constraints.NotNull;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.io.Serializable;
import java.time.LocalDate;

public class ChamadoDTO implements Serializable {

    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura = LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento = LocalDate.now();

    @NotNull(message = "O campo PRIORIDADE é requerido!")
    private Integer prioridade;
    @NotNull(message = "O campo STATUS é requerido!")
    private Integer status;
    @NotNull(message = "O campo TITULO é requerido!")
    private String titulo;
    @NotNull(message = "O campo OBSERVAÇÕES é requerido!")
    private String observacoes;
    @NotNull(message = "O campo CLIENTE é requerido!")
    private Integer cliente;
    @NotNull(message = "O campo TECNICO é requerido!")
    private Integer tecnico;
    private String nomeTecnico;
    private String nomeCliente;

    public ChamadoDTO(){
        super();
    }

    public ChamadoDTO(Chamado chamado) {
        this.id = chamado.getId();
        this.dataAbertura = chamado.getDataAbertura();
        this.dataFechamento = chamado.getDataFechamento();
        this.prioridade = chamado.getPrioridade().getCodigo();
        this.status = chamado.getStatus().getCodigo();
        this.titulo = chamado.getTitulo();
        this.observacoes = chamado.getObservacoes();
        this.cliente = chamado.getCliente().getId();
        this.tecnico = chamado.getTecnico().getId();
        this.nomeTecnico = chamado.getTecnico().getNome();
        this.nomeCliente = chamado.getCliente().getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public Integer getTecnico() {
        return tecnico;
    }

    public void setTecnico(Integer tecnico) {
        this.tecnico = tecnico;
    }

    public String getNomeTecnico() {
        return nomeTecnico;
    }

    public void setNomeTecnico(String nomeTecnico) {
        this.nomeTecnico = nomeTecnico;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
}
