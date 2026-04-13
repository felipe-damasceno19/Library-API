package io.github.felipe_damasceno19.libraryapi.service;

import io.github.felipe_damasceno19.libraryapi.model.SystemUser;
import io.github.felipe_damasceno19.libraryapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public void save(SystemUser systemUser){
        var password = systemUser.getPassword();
        systemUser.setPassword(encoder.encode(password));
        repository.save(systemUser);
    }

    public SystemUser getByLogin(String login){
        return repository.findByLogin(login);
    }
}
