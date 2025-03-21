package com.example.demo.domain.affiliate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AffiliateRepository extends JpaRepository<Affiliate, Long> {
    List<Affiliate> findAffiliateById(Long id); 
}
