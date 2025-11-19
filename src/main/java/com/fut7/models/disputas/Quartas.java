package com.fut7.models.disputas;

import com.fut7.models.TAD.Fila;

import lombok.Data;

@Data
public class Quartas {

    private Fila<Disputa> quartas;

    public void adicionarQuarta(Disputa disputa) {
        if (quartas == null) {
            quartas = new Fila<>();
        }
        
        if(quartas.getSize() >= 8) {
            return;
        }
        disputa.setFase(TiposDisputa.QUARTAS);
        quartas.enqueue(disputa);
    }

    public void popQuarta() {
        if (quartas != null && !(quartas.getSize() == 0)) {
            quartas.dequeue();
        }
    }
}
