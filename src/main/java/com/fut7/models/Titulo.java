package com.fut7.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Titulo {
    
    private String nome;
    private int ano;
    private String organizacao;
    private String pais;
}
