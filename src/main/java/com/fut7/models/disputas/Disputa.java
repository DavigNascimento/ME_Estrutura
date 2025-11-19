package com.fut7.models.disputas;

import com.fut7.models.Time;
import com.fut7.models.TAD.Lista;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Disputa {
    
    private final Lista<Time> times;
    private TiposDisputa fase;
}
