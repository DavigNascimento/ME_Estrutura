package com.fut7.models.disputas;

import com.fut7.models.TAD.Fila;

import lombok.Data;

@Data
public class Oitavas implements Fase {

    private Fila<Disputa> oitavas;

    public void adicionarDisputa(Disputa disputa) {
        if (oitavas == null) {
            oitavas = new Fila<>();
        }
        
        if(oitavas.getSize() >= 16) {
            return;
        }
        disputa.setFase(TiposDisputa.OITAVAS);
        oitavas.enqueue(disputa);
    }

    public void popDisputa() {
        if (oitavas != null && !(oitavas.getSize() == 0)) {
            oitavas.dequeue();
        }
    }
}
