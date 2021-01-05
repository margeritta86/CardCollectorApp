package com.pokemon.app.service.common;

import com.pokemon.app.model.Trainer;
import com.pokemon.app.model.User;
import com.pokemon.app.repository.UserRepository;
import com.pokemon.app.request.UserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private UserRepository userRepository;
    private TrainerAccessService trainerAccessService;
    private PasswordEncoder passwordEncoder;

    public RegisterService(UserRepository userRepository, TrainerAccessService trainerAccessService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.trainerAccessService = trainerAccessService;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(UserRequest userRequest) {

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new RegisterServiceException("Podany użytkownik już istnieje w bazie!");
        }
        Trainer trainer = new Trainer(userRequest.getName());
        User user = new User(userRequest.getEmail(), passwordEncoder.encode(userRequest.getPassword()),trainer);
        trainerAccessService.save(trainer);
        userRepository.save(user);

    }
}
