package com.pokemon.app.service;

import com.pokemon.app.model.Trainer;
import com.pokemon.app.model.User;
import com.pokemon.app.repository.UserRepository;
import com.pokemon.app.request.UserRequest;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private UserRepository userRepository;
    private TrainerAccessService trainerAccessService;

    public RegisterService(UserRepository userRepository, TrainerAccessService trainerAccessService) {
        this.userRepository = userRepository;
        this.trainerAccessService = trainerAccessService;
    }

    public void registerUser(UserRequest userRequest) {

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new RegisterServiceException("Podany użytkownik już istnieje w bazie!");
        }
        else if(userRepository.existsByName(userRequest.getName())){
            throw new RegisterServiceException("Podana nazwa użytkownika już istnieje w bazie!");
        }
        Trainer trainer = new Trainer(userRequest.getName());
        User user = new User(userRequest.getEmail(), userRequest.getPassword(),trainer);
        trainerAccessService.save(trainer);
        userRepository.save(user);

    }
}
