package com.example.demo.domain.subscription;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long>{
    Optional<Subscription> findSubscriptionById(Long id);
}
