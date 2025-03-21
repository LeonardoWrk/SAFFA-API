package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class SystemrController {
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
}
