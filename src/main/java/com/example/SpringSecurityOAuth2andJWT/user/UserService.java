package com.example.SpringSecurityOAuth2andJWT.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TokenService tokenService;


    public User findByUsername(String username)  {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User with email: " + username + " not found"));
    }

    public UserDTO getUserById(Long id) {
        User existingUser = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User with id: " + id + " not found"));

        return modelMapper.map(existingUser, UserDTO.class);
    }
}