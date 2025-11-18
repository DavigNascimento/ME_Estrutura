package com.fut7.models;

import com.fut7.models.TAD.Lista;

public class Semifinal extends Disputa {

    public Semifinal(Lista<Time> times) {
        super(times, TiposDisputa.SEMI_FINAL);
    }
}
