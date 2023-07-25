package com.uallace.helpdeskbackend.services;

import com.uallace.helpdeskbackend.domain.entities.Chamado;
import com.uallace.helpdeskbackend.domain.entities.Cliente;
import com.uallace.helpdeskbackend.domain.entities.Tecnico;
import com.uallace.helpdeskbackend.domain.enums.Perfil;
import com.uallace.helpdeskbackend.domain.enums.Prioridade;
import com.uallace.helpdeskbackend.domain.enums.Status;
import com.uallace.helpdeskbackend.repositories.ChamadoRepository;
import com.uallace.helpdeskbackend.repositories.ClienteRepository;
import com.uallace.helpdeskbackend.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private BCryptPasswordEncoder enconder;

    public void intanciaDB(){

        Tecnico tec1 = new Tecnico(null,"Ualace","48370557880","uallace@email.com",enconder.encode("123mudar"));
        tec1.addPerfil(Perfil.ADMIN);

        Cliente cli1 = new Cliente(null,"Simone","16713235212","simone@email.com",enconder.encode("123"));

        Chamado c1 =  new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO,"chamado 01","Primeiro chamado",cli1, tec1);

        tecnicoRepository.saveAll(Arrays.asList(tec1));
        clienteRepository.saveAll(Arrays.asList(cli1));
        chamadoRepository.saveAll(Arrays.asList(c1));

    }
}
