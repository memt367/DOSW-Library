package edu.eci.dosw.tdd.service;

import edu.eci.dosw.tdd.exception.ResourceNotFoundException;
import edu.eci.dosw.tdd.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserServiceTest {

    private final UserService userService = new UserService();

    @Test
    void shouldAddAndRetrieveUser() {
        User user = new User("U1", "Ana", "ana@mail.com");

        userService.addUser(user);

        assertEquals("Ana", userService.getUserById("U1").getName());
    }

    @Test
    void shouldThrowWhenUserNotFound() {
        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById("UNKNOWN"));
    }
}
