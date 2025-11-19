package com.fut7.models.disputas;

import com.fut7.models.TAD.Fila;

import lombok.Data;

@Data
public class Semifinal {
    
    private Fila<Disputa> semifinal;

    public void adicionarSemifinal(Disputa disputa) {
        if (semifinal == null) {
            semifinal = new Fila<>();
        }

        if(semifinal.getSize() >= 4) {
            return;
        }
        disputa.setFase(TiposDisputa.SEMI_FINAL);
        semifinal.enqueue(disputa);
    }

    public void popSemifinal() {
        if (semifinal != null && !(semifinal.getSize() == 0)) {
            semifinal.dequeue();
        }
    }
}
