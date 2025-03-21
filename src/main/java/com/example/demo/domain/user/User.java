package com.example.demo.domain.user;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.domain.affiliate.Affiliate;
import com.example.demo.domain.subscription.Subscription;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "usuario")
@Entity(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer type;

    private Date data;

    private String cpf;

    private String loc;

    private String email;

    @ManyToOne
    @JoinColumn(name = "id_plano", nullable = true)
    private Subscription plano;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Affiliate> afiliados = new ArrayList<>();

    public User(RequestContasDTO dataDto) {
        this.name  = dataDto.name();
        this.type  = dataDto.type();
        this.data  = dataDto.data();
        this.cpf   = dataDto.cpf();
        this.loc   = dataDto.loc() ;
        this.email = dataDto.email();
    }   
}
