package com.fut7.models.disputas;

import com.fut7.models.TAD.Fila;

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

    public void popDisputa() {
        if (finalistas != null && !(finalistas.getSize() == 0)) {
            finalistas.dequeue();
        }
    }
}
