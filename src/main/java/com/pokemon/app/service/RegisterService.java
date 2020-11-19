package com.pokemon.app.service;

import com.pokemon.app.model.User;
import com.pokemon.app.repository.UserRepository;
import com.pokemon.app.request.UserRequest;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private UserRepository userRepository;

    public RegisterService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(UserRequest userRequest) {

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new RegisterServiceException("Podany użytkownik już istnieje w bazie!");
        }
        User user = new User(userRequest.getEmail(), userRequest.getPassword());
        userRepository.save(user);

    }
}
