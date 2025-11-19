package com.fut7.models;

import com.fut7.models.TAD.Fila;
import com.fut7.models.disputas.Disputa;
import com.fut7.models.disputas.Fase;
import com.fut7.models.disputas.Final;
import com.fut7.models.disputas.Oitavas;
import com.fut7.models.disputas.Quartas;
import com.fut7.models.disputas.Semifinal;

import lombok.Data;

@Data
public class Campeonato {
    
    private Fila<Fase> fases;

    public Campeonato() {
        this.fases = new Fila<>();
        fases.enqueue(new Oitavas());
        fases.enqueue(new Quartas());
        fases.enqueue(new Semifinal());
        fases.enqueue(new Final());
    }

    public void povoarOitavas(Fila<Disputa> disputas) {
        Fase oitavas = fases.dequeue();
        for (int i = 0; i < disputas.getSize(); i++) {
            oitavas.adicionarDisputa(disputas.dequeue());
        }
        fases.enqueue(oitavas);
    }

    public void povoarQuartas(Fila<Disputa> disputas) {
        fases.dequeue(); // Remover Oitavas
        Fase quartas = fases.dequeue();
        for (int i = 0; i < disputas.getSize(); i++) {
            quartas.adicionarDisputa(disputas.dequeue());
        }
        fases.enqueue(quartas);
    }

    public void povoarSemifinal(Fila<Disputa> disputas) {
        fases.dequeue(); // Remover Quartas
        Fase semifinal = fases.dequeue();
        for (int i = 0; i < disputas.getSize(); i++) {
            semifinal.adicionarDisputa(disputas.dequeue());
        }
        fases.enqueue(semifinal);
    }

    public void povoarFinal(Fila<Disputa> disputas) {
        fases.dequeue(); // Remover Semifinal
        Fase finalCampeonato = fases.dequeue();
        for (int i = 0; i < disputas.getSize(); i++) {
            finalCampeonato.adicionarDisputa(disputas.dequeue());
        }
        fases.enqueue(finalCampeonato);
    }
}
