package com.fut7.models.disputas;

import java.util.HashMap;

import com.fut7.models.Jogador;
import com.fut7.models.Time;
import com.fut7.models.TAD.Lista;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Disputa {
    
    private final Lista<Time> times;
    private TiposDisputa fase;
    
    // Resultado
    private HashMap<Time, Integer> golsPorTime;
    private HashMap<Jogador, Integer> golsPorJogador;
    private HashMap<Jogador, Integer> assistenciasPorJogador;
}
