package com.example.demo.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserById(long id);
    Optional<User> findByCpf(String cpf);
    Optional<User> findByCpfAndSenha(String cpf, String Senha);
}
