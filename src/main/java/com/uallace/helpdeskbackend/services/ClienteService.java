package com.uallace.helpdeskbackend.services;

import com.uallace.helpdeskbackend.domain.dtos.ClienteDTO;
import com.uallace.helpdeskbackend.domain.entities.Pessoa;
import com.uallace.helpdeskbackend.domain.entities.Cliente;
import com.uallace.helpdeskbackend.exceptions.DataIntegrityViolationException;
import com.uallace.helpdeskbackend.exceptions.NotFoundException;
import com.uallace.helpdeskbackend.repositories.PessoaRepository;
import com.uallace.helpdeskbackend.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BCryptPasswordEncoder enconder;

    public Cliente findById(Integer id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new NotFoundException("Objeto não encontrado! id: " + id));
    }

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Cliente create(ClienteDTO dto) {
        dto.setId(null);
        dto.setSenha(enconder.encode(dto.getSenha()));
        validaPorCpfEEmail(dto);
        Cliente cliente = new Cliente(dto);
        return clienteRepository.save(cliente);
    }

    public Cliente update(Integer id, ClienteDTO dto) {
        dto.setId(id);
        Cliente cliente = findById(id);
        validaPorCpfEEmail(dto);
        cliente = new Cliente(dto);
        return clienteRepository.save(cliente);
    }

    private void validaPorCpfEEmail(ClienteDTO dto) {
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
        Cliente cliente = findById(id);
        if(cliente.getChamados().size() > 0){
            throw new DataIntegrityViolationException("Cliente possui ordens de serviços e não pode ser deletado");
        }
        clienteRepository.deleteById(id);
    }
}
