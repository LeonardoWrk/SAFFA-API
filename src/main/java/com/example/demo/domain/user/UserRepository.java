package com.example.demo.domain.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserById(long id);
    Optional<User> findByCpf(String cpf);
    Optional<User> findByCpfAndSenha(String cpf, String Senha); // Optional<User> é usado quando você espera no máximo um resultado (como em buscas por ID)
    List<User> findAllByType(Integer type);  // List<User> é usado quando você quer todos os resultados que atendem ao critério
}
