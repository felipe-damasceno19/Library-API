package io.github.felipe_damasceno19.libraryapi.service;

import io.github.felipe_damasceno19.libraryapi.model.User;
import io.github.felipe_damasceno19.libraryapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public void save(User user){
        var password = user.getPassword();
        user.setPassword(encoder.encode(password));
        repository.save(user);
    }

    public User getByLogin(String login){
        return repository.findByLogin(login);
    }
}
