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
    private static final Fase terceiroColocado = new TerceiroColocado();
    private static final Lista<Fase> resultados = new Lista<>();
    private static TiposDisputa faseAtual;
    private static boolean iniciado = false;

    public static void iniciar() {
        if (iniciado)
            return;

        fases.enqueue(new Oitavas());
        fases.enqueue(new Quartas());
        fases.enqueue(new Semifinal());
        fases.enqueue(new Final());
    }

    public static void resetar() {
        fases.clear();
        iniciado = false;
    }

    public static void povoarOitavas() {
        Fase oitavas = fases.getElementAt(0);
        Lista<Time> todosTimes = TimeRepository.getAll();

        if(TimeRepository.getAll().getSize() < 16) {
            throw new IllegalStateException("Não há times suficientes para iniciar o campeonato. É necessário pelo menos 16 times.");
        }

        // create 8 matches pairing teams (0-1, 2-3, ... 14-15)
        for(int i = 0; i < 16; i += 2) {
            Lista<Time> times = new Lista<>();
            times.add(todosTimes.getElementAt(i));
            times.add(todosTimes.getElementAt(i + 1));

            Disputa disputaOitava = DataGenerator.gerarDisputaAleatoria(times);
            oitavas.adicionarDisputa(disputaOitava);
        }

        iniciado = true;
        faseAtual = TiposDisputa.OITAVAS;
    }
    
    public static void paraQuartas() {
        Fase oitavas = fases.dequeue();
        Fase quartas = fases.getElementAt(0);

        Oitavas resultadosRetornados = new Oitavas();
        Fase resultadosOitavas = oitavas.realizarDisputas();

        // resultadosOitavas contains 8 disputas -> create 4 quartas pairing winners
        for(int i = 0; i < 4; i++) {
            Disputa disputa = resultadosOitavas.getDisputas().dequeue();
            Disputa disputa2 = resultadosOitavas.getDisputas().dequeue();

            resultadosRetornados.adicionarDisputa(disputa);
            resultadosRetornados.adicionarDisputa(disputa2);
            
            quartas.adicionarDisputa(Disputa.builder()
                .times(new Lista<Time>() {{
                    add(disputa.getVencedor());
                    add(disputa2.getVencedor());
                }})
                .build());
        }

        resultados.add(resultadosRetornados);
        faseAtual = TiposDisputa.QUARTAS;
    }

    public static void paraSemifinal() {
        Fase quartas = fases.dequeue();
        Fase semifinal = fases.getElementAt(0);
        // don't touch terceiro aqui; será preenchido após as semifinais (na paraFinal)

        Quartas resultadosRetornados = new Quartas();
        Fase resultadosQuartas = quartas.realizarDisputas();

        // resultadosQuartas contém 4 disputas -> criar 2 semifinais
        for(int i = 0; i < 2; i++) {
            Disputa disputa = resultadosQuartas.getDisputas().dequeue();
            Disputa disputa2 = resultadosQuartas.getDisputas().dequeue();

            resultadosRetornados.adicionarDisputa(disputa);
            resultadosRetornados.adicionarDisputa(disputa2);
            
            semifinal.adicionarDisputa(Disputa.builder()
                .times(new Lista<Time>() {{
                    add(disputa.getVencedor());
                    add(disputa2.getVencedor());
                }})
                .build());
        }

        resultados.add(resultadosRetornados);
        faseAtual = TiposDisputa.SEMI_FINAL;
    }

    public static void paraFinal() {
        Fase semifinal = fases.dequeue();
        Fase finalCampeonato = fases.getElementAt(0);

        Semifinal resultadosRetornados = new Semifinal();
        Fase resultadosSemifinal = semifinal.realizarDisputas();

        // resultadosSemifinal contém 2 disputas -> criar final e terceiro colocado
        Disputa disputa1 = resultadosSemifinal.getDisputas().getElementAt(0);
        Disputa disputa2 = resultadosSemifinal.getDisputas().getElementAt(1);

        resultadosRetornados.adicionarDisputa(disputa1);
        resultadosRetornados.adicionarDisputa(disputa2);

        // Adiciona a disputa do terceiro colocado
        Disputa terceiro = Disputa.builder()
            .times(new Lista<Time>() {{
                add(disputa1.getPerdedor());
                add(disputa2.getPerdedor());
            }})
            .build();
        terceiroColocado.adicionarDisputa(terceiro);
        Fase resultadoTerceiro = terceiroColocado.realizarDisputas();

        // Adiciona a disputa da final
        finalCampeonato.adicionarDisputa(Disputa.builder()
            .times(new Lista<Time>() {{
                add(disputa1.getVencedor());
                add(disputa2.getVencedor());
            }})
            .build());

        resultados.add(resultadosRetornados);
        resultados.add(resultadoTerceiro);
        faseAtual = TiposDisputa.FINAL;
    }

    public static void getCampeao() {
        faseAtual = TiposDisputa.FIM;
        Fase finalCampeonato = fases.dequeue();
        Fase resultadosFinal = finalCampeonato.realizarDisputas();
        resultados.add(resultadosFinal);
        faseAtual = TiposDisputa.FIM;
    }

    public static boolean iniciado() {
        return iniciado;
    }

    public static TiposDisputa getFaseAtual() {
        return faseAtual;
    }

    public static Lista<Fase> getResultados() {
        return resultados;
    }
}
