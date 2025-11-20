package com.fut7.view;

import com.fut7.models.Campeonato;
import com.fut7.models.Jogador;
import com.fut7.models.Time;
import com.fut7.models.disputas.Disputa;
import com.fut7.models.disputas.Fase;
import com.fut7.models.TAD.Lista;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class MenuRelatorio {
    
    public MenuRelatorio() {
        System.out.println("\n=== MENU RELATÓRIOS ===");
        
        while (true) {
            OptionHandler optionHandler = new OptionHandler();
            optionHandler.addOption("Mostrar Resultado do Campeonato");
            optionHandler.addOption("Relatório de Jogadores");
            optionHandler.addOption("Relatório de Times");
            
            int choice = optionHandler.getOption();

            switch (choice) {
                case 0:
                    System.out.println("Voltando ao menu principal...\n");
                    return;
                
                case 1:
                    mostrarResultadoCampeonato();
                    break;

                case 2:
                    relatorioJogadores();
                    break;

                case 3:
                    relatorioTimes();
                    break;

                default:
                    System.out.println("Opção inválida.\n");
                    break;
            }
        }
    }

    private void mostrarResultadoCampeonato() {
        System.out.println("\n--- RESULTADO DO CAMPEONATO ---");
        
        Lista<Fase> resultados = Campeonato.getResultados();
        if (resultados.getSize() == 0) {
            System.out.println("Nenhum resultado disponível. Inicie e dispute o campeonato primeiro.\n");
            return;
        }

        for (int i = 0; i < resultados.getSize(); i++) {
            Fase fase = resultados.getElementAt(i);
            System.out.println("\n=== " + fase.getClass().getSimpleName() + " ===");
            
            System.out.printf("%-35s %-15s %-35s\n", "Time A", "Placar", "Time B");
            System.out.println("─".repeat(85));
            
            for (int j = 0; j < fase.getDisputas().getSize(); j++) {
                Disputa disputa = fase.getDisputas().getElementAt(j);
                Time timeA = disputa.getTimes().getElementAt(0);
                Time timeB = disputa.getTimes().getElementAt(1);
                int golsA = disputa.getGolsPorTime().get(timeA);
                int golsB = disputa.getGolsPorTime().get(timeB);
                
                System.out.printf("%-35s %-15s %-35s\n", 
                    timeA.getNome(), golsA + " x " + golsB, timeB.getNome());
            }
        }
        System.out.println();
    }

    private void relatorioJogadores() {
        System.out.println("\n--- RANKING DE JOGADORES ---");
        
        Lista<Fase> resultados = Campeonato.getResultados();
        if (resultados.getSize() == 0) {
            System.out.println("Nenhum resultado disponível. Inicie e dispute o campeonato primeiro.\n");
            return;
        }

        HashMap<Jogador, Integer> todosGols = new HashMap<>();
        HashMap<Jogador, Integer> todasAssistencias = new HashMap<>();
        
        for (int i = 0; i < resultados.getSize(); i++) {
            Fase fase = resultados.getElementAt(i);
            for (int j = 0; j < fase.getDisputas().getSize(); j++) {
                Disputa disputa = fase.getDisputas().getElementAt(j);
                
                for (Map.Entry<Jogador, Integer> entry : disputa.getGolsPorJogador().entrySet()) {
                    todosGols.put(entry.getKey(), 
                        todosGols.getOrDefault(entry.getKey(), 0) + entry.getValue());
                }
                
                for (Map.Entry<Jogador, Integer> entry : disputa.getAssistenciasPorJogador().entrySet()) {
                    todasAssistencias.put(entry.getKey(), 
                        todasAssistencias.getOrDefault(entry.getKey(), 0) + entry.getValue());
                }
            }
        }

        System.out.println("\n--- CLASSIFICAÇÃO GERAL (Gols + Assistências) ---");
        HashMap<Jogador, Integer> pontuacaoTotal = new HashMap<>();
        
        for (Map.Entry<Jogador, Integer> entry : todosGols.entrySet()) {
            int total = entry.getValue() + todasAssistencias.getOrDefault(entry.getKey(), 0);
            pontuacaoTotal.put(entry.getKey(), total);
        }
        
        for (Map.Entry<Jogador, Integer> entry : todasAssistencias.entrySet()) {
            if (!pontuacaoTotal.containsKey(entry.getKey())) {
                pontuacaoTotal.put(entry.getKey(), entry.getValue());
            }
        }
        
        if (pontuacaoTotal.isEmpty()) {
            System.out.println("Nenhuma estatística registrada ainda.\n");
        } else {
            ArrayList<Map.Entry<Jogador, Integer>> ranking = new ArrayList<>(pontuacaoTotal.entrySet());
            ranking.sort(Map.Entry.<Jogador, Integer>comparingByValue().reversed());
            
            System.out.printf("%-5s %-30s %-10s %-10s %-10s\n", "Pos.", "Jogador", "Total", "Gols", "Assist.");
            System.out.println("─".repeat(70));
            
            int posicao = 1;
            for (Map.Entry<Jogador, Integer> entry : ranking) {
                int gols = todosGols.getOrDefault(entry.getKey(), 0);
                int assists = todasAssistencias.getOrDefault(entry.getKey(), 0);
                System.out.printf("%-5d %-30s %-10d %-10d %-10d\n", 
                    posicao++, entry.getKey().getNome(), entry.getValue(), gols, assists);
            }
            System.out.println();
        }
    }

    private void relatorioTimes() {
        System.out.println("\n--- RELATÓRIO DE TIMES ---");
        
        Lista<Fase> resultados = Campeonato.getResultados();
        if (resultados.getSize() == 0) {
            System.out.println("Nenhum resultado disponível. Inicie e dispute o campeonato primeiro.\n");
            return;
        }

        HashMap<Time, Integer> golsMarcados = new HashMap<>();
        HashMap<Time, Integer> golsSofridos = new HashMap<>();
        HashMap<Time, Integer> vitorias = new HashMap<>();
        HashMap<Time, Integer> derrotas = new HashMap<>();
        
        for (int i = 0; i < resultados.getSize(); i++) {
            Fase fase = resultados.getElementAt(i);
            for (int j = 0; j < fase.getDisputas().getSize(); j++) {
                Disputa disputa = fase.getDisputas().getElementAt(j);
                Time timeA = disputa.getTimes().getElementAt(0);
                Time timeB = disputa.getTimes().getElementAt(1);
                int golsA = disputa.getGolsPorTime().get(timeA);
                int golsB = disputa.getGolsPorTime().get(timeB);
                
                golsMarcados.put(timeA, golsMarcados.getOrDefault(timeA, 0) + golsA);
                golsMarcados.put(timeB, golsMarcados.getOrDefault(timeB, 0) + golsB);
                golsSofridos.put(timeA, golsSofridos.getOrDefault(timeA, 0) + golsB);
                golsSofridos.put(timeB, golsSofridos.getOrDefault(timeB, 0) + golsA);
                
                if (golsA > golsB) {
                    vitorias.put(timeA, vitorias.getOrDefault(timeA, 0) + 1);
                    derrotas.put(timeB, derrotas.getOrDefault(timeB, 0) + 1);
                } else if (golsB > golsA) {
                    vitorias.put(timeB, vitorias.getOrDefault(timeB, 0) + 1);
                    derrotas.put(timeA, derrotas.getOrDefault(timeA, 0) + 1);
                }
            }
        }

        System.out.println("\n--- TIMES COM MAIS GOLS ---");
        if (golsMarcados.isEmpty()) {
            System.out.println("Nenhum gol registrado ainda.");
        } else {
            ArrayList<Map.Entry<Time, Integer>> rankingGols = new ArrayList<>(golsMarcados.entrySet());
            rankingGols.sort(Map.Entry.<Time, Integer>comparingByValue().reversed());
            
            int posicao = 1;
            for (Map.Entry<Time, Integer> entry : rankingGols) {
                System.out.printf("%d. %s - %d gol(s)\n", posicao++, entry.getKey().getNome(), entry.getValue());
            }
        }

        System.out.println("\n--- TIMES COM MAIS VITÓRIAS ---");
        if (vitorias.isEmpty()) {
            System.out.println("Nenhuma vitória registrada ainda.");
        } else {
            ArrayList<Map.Entry<Time, Integer>> rankingVitorias = new ArrayList<>(vitorias.entrySet());
            rankingVitorias.sort(Map.Entry.<Time, Integer>comparingByValue().reversed());
            
            System.out.printf("%-5s %-30s %-12s\n", "Pos.", "Time", "Vitórias");
            System.out.println("─".repeat(50));
            
            int posicao = 1;
            for (Map.Entry<Time, Integer> entry : rankingVitorias) {
                Time time = entry.getKey();
                System.out.printf("%-5d %-30s %-12d\n", 
                    posicao++, time.getNome(), entry.getValue());
            }
        }
        
        System.out.println();
    }
}