package com.fut7.models;

import java.util.HashMap;
import com.fut7.models.TAD.Fila;
import com.fut7.models.TAD.Lista;
import com.fut7.models.disputas.Disputa;
import com.fut7.models.disputas.Fase;
import com.fut7.models.disputas.Final;
import com.fut7.models.disputas.Oitavas;
import com.fut7.models.disputas.Quartas;
import com.fut7.models.disputas.Semifinal;
import com.fut7.models.disputas.TerceiroColocado;
import com.fut7.repository.TimeRepository;
import com.fut7.util.DataGenerator;

import lombok.Data;

@Data
public class Campeonato {
    
    private Fila<Fase> fases;

    public Campeonato() {
        this.fases = new Fila<>();
        fases.enqueue(new Oitavas()); // 0
        fases.enqueue(new Quartas()); // 1
        fases.enqueue(new Semifinal()); // 2
        fases.enqueue(new TerceiroColocado()); // 3
        fases.enqueue(new Final()); // 4
    }

    public void povoarOitavas() {
        Fase oitavas = fases.dequeue();
        Lista<Time> todosTimes = TimeRepository.getAll();

        if(TimeRepository.getAll().getSize() < 16) {
            throw new IllegalStateException("Não há times suficientes para iniciar o campeonato. É necessário pelo menos 16 times.");
        }

        for(int i = 0; i < 16; i += 2) {
            Lista<Time> times = new Lista<>();
            times.add(todosTimes.getElementAt(i));
            times.add(todosTimes.getElementAt(i + 1));

            Disputa disputaOitava = DataGenerator.gerarDisputaAleatoria(times);
            oitavas.adicionarDisputa(disputaOitava);
        }
        fases.enqueue(oitavas);
    }
    
}
