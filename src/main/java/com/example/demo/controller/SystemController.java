package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.affiliate.Affiliate;
import com.example.demo.domain.affiliate.AffiliateRepository;
import com.example.demo.domain.affiliate.ResquestAffiliateDTO;
import com.example.demo.domain.subscription.Subscription;
import com.example.demo.domain.subscription.SubscriptionDTO;
import com.example.demo.domain.subscription.SubscriptionRepository;
import com.example.demo.domain.user.RequestContasDTO;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserRepository;

import jakarta.persistence.EntityNotFoundException;


@RestController
@RequestMapping("/contas")
public class SystemController {
    @Autowired
    private UserRepository repository;
    @Autowired
    private AffiliateRepository repositoryAf;
    @Autowired
    private SubscriptionRepository repositorySub;

    @GetMapping
    public ResponseEntity<List<User>> getContas() {
        var contas = repository.findAll();
        return ResponseEntity.ok(contas);
    }

    @PostMapping //realmente preciso passar como <list>?
    public ResponseEntity<List<User>> cadastrarConta(@RequestBody @Validated RequestContasDTO dataDto){
        User newUser = new User(dataDto);
        newUser.setPlano(null);
        System.out.println(newUser +"teste");
        repository.save(newUser);
        
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/affiliate") //realmente preciso passar como <list>?
    public ResponseEntity<List<User>> cadastrarAfiliado(@RequestBody @Validated ResquestAffiliateDTO dataDto){
        User user = this.repository.findUserById(dataDto.id_usuario())
        .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Affiliate newAffiliate = new Affiliate();
        newAffiliate.setName(dataDto.name());
        newAffiliate.setData(dataDto.data());
        newAffiliate.setCpf(dataDto.cpf());
        newAffiliate.setUsuario(user);
        newAffiliate.setStatus(dataDto.status());

        repositoryAf.save(newAffiliate);
        
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/Subscription") //realmente preciso passar como <list>?
    public ResponseEntity<List<User>> cadastrarAfiliado(@RequestBody @Validated SubscriptionDTO dataDto ){
        User user = this.repository.findUserById(dataDto.userId())
        .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Subscription subscription = this.repositorySub.findSubscriptionById(dataDto.planoId())
        .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        user.setPlano(subscription);

        repository.save(user);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String cpf = credentials.get("cpf");
        String senha = credentials.get("senha");

        Optional<User> userOptional = repository.findByCpfAndSenha(cpf, senha);
        
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }

    @PostMapping("/verificar-cpf")
    public ResponseEntity<Map<String, String>>  verificarCpf(@RequestBody Map<String, String> credentials) {
        String cpf = credentials.get("cpf");
        boolean existe = repository.findByCpf(cpf).isPresent();
        
         Map<String, String> response = new HashMap<>();

        if (existe) {
            response.put("message", "CPF ja cadastrado");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "CPF disponível para cadastro.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(
        @PathVariable Long id,
        @RequestParam Long requesterId // ID do usuário que está fazendo a requisição
    ) {
        User requester = repository.findById(requesterId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        
        if (requester.getType() != 2) {
            return ResponseEntity.status(403).body("Acesso negado: apenas tipo 2.");
        }
    
        User userToDelete = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário alvo não encontrado."));
        
        repository.delete(userToDelete);
        return ResponseEntity.ok("Conta excluída com sucesso.");
    }
}
