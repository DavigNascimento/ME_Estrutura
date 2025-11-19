package com.fut7.models.disputas;

import com.fut7.models.TAD.Fila;
import com.fut7.models.TAD.Lista;

import lombok.Data;

@Data
public class Final implements Fase {

    private Fila<Disputa> finalistas;

    public void adicionarDisputa(Disputa disputa) {
        if (finalistas == null) {
            finalistas = new Fila<>();
        }

        if(finalistas.getSize() >= 2) {
            return;
        }
        disputa.setFase(TiposDisputa.FINAL);
        finalistas.enqueue(disputa);
    }

    public Disputa popDisputa() {
        if (finalistas != null && !(finalistas.getSize() == 0)) {
            return finalistas.dequeue();
        }
        return null;
    }

    public Fila<Disputa> getDisputas() {
        return finalistas;
    }
}
