package com.interview.prep.security.service;

import com.interview.prep.entities.User;
import com.interview.prep.exceptions.UserNotFoundException;
import com.interview.prep.repositories.UserRepository;
import com.interview.prep.security.model.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
        Optional<User> user = userRepository.findByName(username);
                if(!user.isPresent())
                     throw new UserNotFoundException(username,true);
        return new CustomUserDetails(user.get());
    }
}
