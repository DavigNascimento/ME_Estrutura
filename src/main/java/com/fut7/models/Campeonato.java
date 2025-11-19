package com.fut7.models;

import com.fut7.models.TAD.Fila;
import com.fut7.models.TAD.Lista;
import com.fut7.models.disputas.Disputa;
import com.fut7.models.disputas.Fase;
import com.fut7.models.disputas.Final;
import com.fut7.models.disputas.Oitavas;
import com.fut7.models.disputas.Quartas;
import com.fut7.models.disputas.Semifinal;
import com.fut7.models.disputas.TerceiroColocado;
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
        for(int i = 0; i < 16; i += 2) {
            Lista<Time> times = new Lista<>();
            times.add(DataGenerator.gerarTime());
            times.add(DataGenerator.gerarTime());

            Disputa disputaOitava = DataGenerator.gerarDisputaAleatoria(times);
            oitavas.adicionarDisputa(disputaOitava);
        }
        fases.enqueue(oitavas);
    }

    public void moverParaProximaFase() {
        // precisa de pelo menos duas fases para mover
        if (fases.getSize() < 2) return;

        Fase faseAtual = fases.dequeue();
        Fase proximaFase = fases.dequeue();

        Disputa disputa = faseAtual.popDisputa();
        // se não há disputa a mover, restaura a ordem e retorna
        if (disputa == null) {
            if (faseAtual.getDisputas().getSize() > 0)
                fases.enqueue(faseAtual);
            fases.enqueue(proximaFase);
            return;
        }

        // Gerar dados
        // disputa.realizarDisputa();
        Time t1 = disputa.getVencedor();
        
        if(proximaFase instanceof TerceiroColocado && faseAtual instanceof Semifinal) {
            Disputa disputaPerdedores = faseAtual.popDisputa();
            Time t2 = disputaPerdedores.getPerdedor();

            if (proximaFase.getDisputas().getSize() == 0) {
                Lista<Time> timesProximaFase = new Lista<>();
                timesProximaFase.add(t2);

                Disputa proximaDisputa = Disputa.builder()
                    .times(timesProximaFase)
                    .build();
                proximaFase.adicionarDisputa(proximaDisputa);
            } else {
                Disputa disputaProximaFase = proximaFase.popDisputa();
                disputaProximaFase.getTimes().add(t2);
                proximaFase.adicionarDisputa(disputaProximaFase);
            }

            if (faseAtual.getDisputas().getSize() > 0)
                fases.enqueue(faseAtual);
            fases.enqueue(proximaFase);
            return;
        }

        if (proximaFase.getDisputas().getSize() == 0) {
            Lista<Time> timesProximaFase = new Lista<>();
            timesProximaFase.add(t1);

            Disputa proximaDisputa = Disputa.builder()
                .times(timesProximaFase)
                .build();
            proximaFase.adicionarDisputa(proximaDisputa);
        } else {
            Disputa disputaProximaFase = proximaFase.popDisputa();
            disputaProximaFase.getTimes().add(t1);
            proximaFase.adicionarDisputa(disputaProximaFase);
        }

        if (faseAtual.getDisputas().getSize() > 0)
            fases.enqueue(faseAtual);
        fases.enqueue(proximaFase);
    }
}
