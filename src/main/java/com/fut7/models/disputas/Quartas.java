package com.fut7.models.disputas;

import com.fut7.models.TAD.Fila;

import lombok.Data;

@Data
public class Quartas implements Fase {

    private Fila<Disputa> quartas;

    public void adicionarDisputa(Disputa disputa) {
        if (quartas == null) {
            quartas = new Fila<>();
        }
        
        if(quartas.getSize() >= 8) {
            return;
        }
        disputa.setFase(TiposDisputa.QUARTAS);
        quartas.enqueue(disputa);
    }

    public Disputa popDisputa() {
        if (quartas != null && !(quartas.getSize() == 0)) {
            return quartas.dequeue();
        }
        return null;
    }

    public Fila<Disputa> getDisputas() {
        return quartas;
    }
}
