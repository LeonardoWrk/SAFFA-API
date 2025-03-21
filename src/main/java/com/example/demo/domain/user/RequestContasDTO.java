package com.example.demo.domain.user;

import java.sql.Date;

public record RequestContasDTO(
        String name,
        Integer type,
        Date data,
        String cpf,
        String loc,
        String email
) { }