package com.pokemon.app.service.common;

import com.pokemon.app.model.User;
import com.pokemon.app.repository.UserRepository;
import com.pokemon.app.request.UserRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private UserRepository userRepository;
    private User loggedUser = null;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void loginUser(UserRequest userRequest) {
        Optional<User> optionalUser = userRepository.findByEmail(userRequest.getEmail());
        if (!optionalUser.isPresent()) {
            throw new LoginServiceException("Użytkownik o podanym mailu nie istnieje");
        }
        else if(!optionalUser.get().getPassword().equals(userRequest.getPassword())){
            throw new LoginServiceException("Podane hasło jest nieprawidłowe");
        }
        loggedUser = optionalUser.get();
    }

    public User getLoggedUserOrThrow() {
        return getLoggedUser()
                .orElseThrow(()-> new LoginServiceException("Brak zalogowanego użytkownika"));
    }

    private Optional<User> getLoggedUser() {
        return Optional.ofNullable(loggedUser);
    }
}

