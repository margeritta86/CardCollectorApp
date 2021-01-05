package com.pokemon.app.service.common;

import com.pokemon.app.model.User;
import com.pokemon.app.repository.UserRepository;
import com.pokemon.app.request.UserRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class LoginService implements UserDetailsService {

    private UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User getLoggedUser() {
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (object instanceof User) {
            return (User) object;
        }
        throw new LoginServiceException("Brak zalogowanego użytkownika. Sprawdź ustawienia Security!");

    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        System.out.println("To jest username:" + userName);
        UserDetails user = userRepository.findByEmail(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Nie znaleziono!"));

        return user;

    }

}

