package com.uallace.helpdeskbackend.services;

import com.uallace.helpdeskbackend.domain.dtos.TecnicoDTO;
import com.uallace.helpdeskbackend.domain.entities.Pessoa;
import com.uallace.helpdeskbackend.domain.entities.Tecnico;
import com.uallace.helpdeskbackend.exceptions.DataIntegrityViolationException;
import com.uallace.helpdeskbackend.exceptions.NotFoundException;
import com.uallace.helpdeskbackend.repositories.PessoaRepository;
import com.uallace.helpdeskbackend.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BCryptPasswordEncoder enconder;

    public Tecnico findById(Integer id){
        Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
        return tecnico.orElseThrow(() -> new NotFoundException("Objeto não encontrado! id: " + id));
    }

    public List<Tecnico> findAll(){
        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDTO dto) {
        dto.setId(null);
        dto.setSenha(enconder.encode(dto.getSenha()));
        validaPorCpfEEmail(dto);
        Tecnico tecnico = new Tecnico(dto);
        return tecnicoRepository.save(tecnico);
    }

    public Tecnico update(Integer id, TecnicoDTO dto) {
        dto.setId(id);
        Tecnico tecnico = findById(id);

        if(!dto.getSenha().equals(tecnico.getSenha())) {
            dto.setSenha(enconder.encode(dto.getSenha()));
        }

        validaPorCpfEEmail(dto);
        tecnico = new Tecnico(dto);
        return tecnicoRepository.save(tecnico);
    }

    private void validaPorCpfEEmail(TecnicoDTO dto) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(dto.getCpf());
        if(pessoa.isPresent() && pessoa.get().getId() != dto.getId()){
            throw new DataIntegrityViolationException("CPF Já cadastrado no sistema!");
        }

        pessoa = pessoaRepository.findByEmail(dto.getEmail());

        if(pessoa.isPresent() && pessoa.get().getId() != dto.getId()){
            throw new DataIntegrityViolationException("Email Já cadastrado no sistema!");
        }

    }

    public void delete(Integer id) {
        Tecnico tecnico = findById(id);
        if(tecnico.getChamados().size() > 0){
            throw new DataIntegrityViolationException("Tecnico possui ordens de serviços e não pode ser deletado");
        }
        tecnicoRepository.deleteById(id);
    }
}
