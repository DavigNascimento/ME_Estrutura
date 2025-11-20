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
import com.fut7.models.disputas.TiposDisputa;
import com.fut7.repository.TimeRepository;
import com.fut7.util.DataGenerator;

import lombok.Data;

@Data
public class Campeonato {
    
    private static final Fila<Fase> fases = new Fila<>();
    private static TiposDisputa faseAtual;
    private static boolean iniciado = false;

    public static void iniciar() {
        if (iniciado)
            return;

        fases.enqueue(new Oitavas()); // 0
        fases.enqueue(new Quartas()); // 1
        fases.enqueue(new Semifinal()); // 2
        fases.enqueue(new TerceiroColocado()); // 3
        fases.enqueue(new Final()); // 4
    }

    public static void resetar() {
        fases.clear();
        iniciado = false;
    }

    public static void povoarOitavas() {
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

        iniciado = true;
        faseAtual = TiposDisputa.OITAVAS;
    }
    
    public static boolean iniciado() {
        return iniciado;
    }

    public static void paraQuartas() {
        faseAtual = TiposDisputa.QUARTAS;
    }

    public static void paraSemifinal() {
        faseAtual = TiposDisputa.SEMI_FINAL;
    }

    public static void paraTerceiroColocado() {
        faseAtual = TiposDisputa.TERCEIRO_COLOCADO;
    }

    public static void paraFinal() {
        faseAtual = TiposDisputa.FINAL;
    }

    public static void getCampeao() {
        Fase finalCampeonato = fases.dequeue();
        Disputa disputaFinal = finalCampeonato.getDisputas().getElementAt(0);
        Time campeao = disputaFinal.getVencedor();
        System.out.println("Campeão do Campeonato: " + campeao.getNome());
    }
}
