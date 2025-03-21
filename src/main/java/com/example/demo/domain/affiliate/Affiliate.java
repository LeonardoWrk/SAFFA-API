package com.example.demo.domain.affiliate;

import java.sql.Date;

import com.example.demo.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "afiliado")
@Entity(name = "afiliado")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Affiliate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Date data;

    private String cpf;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    @JsonIgnore
    private User usuario;

    private Integer status;
}
