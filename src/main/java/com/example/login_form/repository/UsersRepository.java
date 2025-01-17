package com.example.login_form.repository;

import com.example.login_form.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UsersModel, Long> {
    Optional<UsersModel> findFirstByLogin(String login);
    Optional<UsersModel> findByLoginAndPassword(String login, String password);
}
