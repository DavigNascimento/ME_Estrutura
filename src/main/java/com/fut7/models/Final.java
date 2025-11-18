package com.fut7.models;

import com.fut7.models.TAD.Lista;

public class Final extends Disputa {

    public Final(Lista<Time> times) {
        super(times, TiposDisputa.FINAL);
    }
}
