package com.uallace.helpdeskbackend.services;


import com.uallace.helpdeskbackend.domain.dtos.ChamadoDTO;
import com.uallace.helpdeskbackend.domain.entities.Chamado;
import com.uallace.helpdeskbackend.domain.entities.Cliente;
import com.uallace.helpdeskbackend.domain.entities.Tecnico;
import com.uallace.helpdeskbackend.domain.enums.Prioridade;
import com.uallace.helpdeskbackend.domain.enums.Status;
import com.uallace.helpdeskbackend.exceptions.NotFoundException;
import com.uallace.helpdeskbackend.repositories.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    public List<Chamado> findAll(){
        return chamadoRepository.findAll();
    }

    public Chamado findById(Integer id){
        Optional<Chamado> chamado = chamadoRepository.findById(id);
        return chamado.orElseThrow(() -> new NotFoundException("Objeto não encontrado! id: " + id));
    }

    public Chamado create(ChamadoDTO dto) {
        return chamadoRepository.save(newChamado(dto));
    }

    public Chamado update(Integer id, ChamadoDTO dto) {
        dto.setId(id);
        Chamado chamado =  findById(id);
        chamado = newChamado(dto);
        return chamadoRepository.save(chamado);
    }

    private Chamado newChamado(ChamadoDTO dto){
        Tecnico tecnico = tecnicoService.findById(dto.getTecnico());
        Cliente cliente = clienteService.findById(dto.getCliente());

        Chamado chamado = new Chamado();

        if(dto.getId() != null){
            chamado.setId(dto.getId());
        }

        if(dto.getStatus().equals(2)){
            chamado.setDataFechamento(LocalDate.now());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(dto.getPrioridade()));
        chamado.setStatus(Status.toEnum(dto.getStatus()));
        chamado.setTitulo(dto.getTitulo());
        chamado.setObservacoes(dto.getObservacoes());

        return chamado;
    }


}
