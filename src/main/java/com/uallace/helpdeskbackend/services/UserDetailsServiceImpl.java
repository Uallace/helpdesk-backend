package com.uallace.helpdeskbackend.services;

import com.uallace.helpdeskbackend.domain.entities.Pessoa;
import com.uallace.helpdeskbackend.repositories.PessoaRepository;
import com.uallace.helpdeskbackend.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Pessoa> user = pessoaRepository.findByEmail(email);
        if(user.isPresent()){
            return new UserSS(user.get().getId(), user.get().getEmail(),user.get().getSenha(), user.get().getPerfil());
        }
        throw new UsernameNotFoundException(email);
    }

}
