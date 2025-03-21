package com.example.demo.domain.payment;

import java.sql.Date;
import com.example.demo.domain.affiliate.Affiliate;
import com.example.demo.domain.subscription.Subscription;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_afiliado")
    private Affiliate afiliado;

    @ManyToOne
    @JoinColumn(name = "id_plano")
    private Subscription plano;

    private Date dataPag;

    private String formaPag;

}
