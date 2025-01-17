package com.example.login_form.service;

import com.example.login_form.model.UsersModel;
import com.example.login_form.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UsersModel registerUser(String login, String password, String email) {
        Optional<UsersModel> existingUser = usersRepository.findFirstByLogin(login);
        if (existingUser.isPresent()) {
            return null;
        }

        UsersModel user = new UsersModel();
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);

        return usersRepository.save(user);
    }

    public UsersModel authenticate(String login, String password) {
        return usersRepository.findByLoginAndPassword(login, password).orElse(null);
    }
}
