package com.pokemon.app.repository;

import com.pokemon.app.model.Trainer;
import com.pokemon.app.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void whenFindByEmail_thenReturnRightUser() {
        //given
        User user = new User();
        user.setEmail("krzychu@gmail.com");
        userRepository.save(user);
        //when
        User foundUser = userRepository.findByEmail("krzychu@gmail.com").orElseThrow();
        //then
        assertEquals(foundUser, user);
    }

    @Test
    void whenExistByEmail_thenReturnTrue() {
        //given
        User user = new User();
        user.setEmail("krzychu@gmail.com");
        userRepository.save(user);
        //when
        boolean foundUser = userRepository.existsByEmail("krzychu@gmail.com");
        //then
        assertTrue(foundUser);
    }

    @Test
    void whenExistsByName_thenReturnTrue() {
        //given
        User user = new User();
        user.setName("Krzysztof");
        userRepository.save(user);
        //when
        boolean foundUser = userRepository.existsByName("Krzysztof");
        //then
        assertTrue(foundUser);
    }
}