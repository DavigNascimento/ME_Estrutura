package com.fut7.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Jogador {
    
    private String nome;
    private int idade;
    private double altura;
    private String nacionalidade;

    // info. pelo time (obs.: com persistencia seria outra tabela)
    private int numeroCamisa;
    private String posicao;

    @Override
    public String toString() {
        return String.format("[#%d] %s - %s | %d anos, %.2fm | %s", 
            numeroCamisa, 
            nome, 
            posicao, 
            idade, 
            altura, 
            nacionalidade);
    }
}
