package com.example.demo.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserById(long id);
    Optional<User> findByCpfAndEmail(String cpf, String email);
    Optional<User> findByCpf(String cpf);
}
