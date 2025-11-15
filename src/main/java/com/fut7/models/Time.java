package com.fut7.models;

import com.fut7.models.TAD.Lista;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Time {
   
    private Lista<Jogador> jogadores;
    private Lista<Titulo> titulos;
    private String nome;
    private String estadio;
    private String cidadeSede;
    private String tecnico;

    // automaticamente a todos os jogadores
    public void atribuirNumeros()
    {
        int numero = 1;
        for (Jogador jogador : jogadores) {
            jogador.setNumeroCamisa(numero);
            numero++;
        }
    }

    // individualmente por jogador
    public void atribuirNumero(String nome, int numero)
    {
        for (Jogador jogador : jogadores)
        {
            if (jogador.getNome().equals(nome)) {
                jogador.setNumeroCamisa(numero);
                return;
            }
        }
    }
}
