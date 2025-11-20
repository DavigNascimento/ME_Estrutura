package com.fut7.models.disputas;

import com.fut7.models.TAD.Fila;

public interface Fase {
    void adicionarDisputa(Disputa disputa);
    Disputa popDisputa();
    Fase realizarDisputas();
    Fila<Disputa> getDisputas();
}
