package com.example.demo.domain.affiliate;

import java.sql.Date;
public record ResquestAffiliateDTO(
       String name,
       Date   data,
       String cpf,
       Long id_usuario,
       Long id_plano,
       Integer status
) { }
