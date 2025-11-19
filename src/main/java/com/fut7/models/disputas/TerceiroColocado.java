package com.fut7.models.disputas;

import com.fut7.models.TAD.Fila;

import lombok.Data;

@Data
public class TerceiroColocado implements Fase {
    
    private Fila<Disputa> disputa;

    public void adicionarDisputa(Disputa disputa) {
        this.disputa = new Fila<>();
        this.disputa.enqueue(disputa);
    }

    public Disputa popDisputa() {
        if (disputa != null && disputa.getSize() > 0) {
            return disputa.dequeue();
        }
        return null;
    }

    public Fila<Disputa> getDisputas() {
        return this.disputa;
    }
}
