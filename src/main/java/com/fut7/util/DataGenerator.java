package com.fut7.util;

import java.util.Locale;
import java.util.Random;
import java.util.HashMap;

import com.github.javafaker.Faker;
import com.fut7.models.Jogador;
import com.fut7.models.Time;
import com.fut7.models.TAD.Lista;
import com.fut7.models.disputas.Disputa;


public class DataGenerator {
    private static final Faker faker = new Faker(Locale.of("pt", "BR"));
    private static final Random random = new Random();


    public static Jogador gerarJogador() {
        return Jogador.builder()
            .nome(faker.name().fullName())
            .idade(random.nextInt(17, 41))
            .altura(Math.round((random.nextDouble(1.60, 2.00)) * 100.0) / 100.0)
            .nacionalidade(faker.country().name())
            .posicao(gerarPosicao())
            .build();
    }

    private static String gerarPosicao() {
        String[] posicoes = {"Goleiro", "Zagueiro", "Lateral", "Volante", "Meia", "Atacante"};
        return posicoes[random.nextInt(posicoes.length)];
    }

    public static Time gerarTime() {
        return Time.builder()
            .nome(faker.team().name())
            .jogadores(new Lista<>())
            .estadio(faker.company().name() + " Stadium")
            .cidadeSede(faker.address().cityName())
            .tecnico(faker.name().fullName())
            .build();
    }

    // Inicializa estatísticas da disputa (gols e assistências) com zero
    public static void gerarEstatisticasIniciais(Disputa disputa) {
        if (disputa == null || disputa.getTimes() == null) return;

        if (disputa.getGolsPorTime() == null) {
            disputa.setGolsPorTime(new HashMap<>());
        }
        if (disputa.getGolsPorJogador() == null) {
            disputa.setGolsPorJogador(new HashMap<>());
        }
        if (disputa.getAssistenciasPorJogador() == null) {
            disputa.setAssistenciasPorJogador(new HashMap<>());
        }

        for (Time time : disputa.getTimes()) {
            disputa.getGolsPorTime().putIfAbsent(time, 0);
            if (time.getJogadores() != null) {
                for (Jogador jogador : time.getJogadores()) {
                    disputa.getGolsPorJogador().putIfAbsent(jogador, 0);
                    disputa.getAssistenciasPorJogador().putIfAbsent(jogador, 0);
                }
            }
        }
    }

    public static Disputa gerarDisputaAleatoria(Lista<Time> times) {
        if (times == null || times.getSize() < 2) {
            throw new IllegalArgumentException("É necessário pelo menos dois times para gerar uma disputa.");
        }

        Lista<Time> timesNaDisputa = new Lista<>();
        timesNaDisputa.add(times.getElementAt(0));
        timesNaDisputa.add(times.getElementAt(1));

        Disputa disputa = Disputa.builder()
            .times(timesNaDisputa)
            .golsPorTime(new HashMap<>())
            .golsPorJogador(new HashMap<>())
            .assistenciasPorJogador(new HashMap<>())
            .build();

        gerarResultado(disputa);
        return disputa;
    }

    public static void atribuirJogadoresAoTime(Time time) {
        for (int i = 0; i < 7; i++) {
            Jogador jogador = gerarJogador();
            time.getJogadores().add(jogador);
        }
        time.atribuirNumeros();
    }

    public static String gerarResultado(Disputa disputa) {
        if (disputa == null || disputa.getTimes() == null || disputa.getTimes().getSize() < 2) {
            return "Disputa sem times suficientes";
        }
        // Inicia os valores dos HashMaps se estiverem nulos com 0
        if (disputa.getGolsPorTime() == null) disputa.setGolsPorTime(new HashMap<>());
        if (disputa.getGolsPorJogador() == null) disputa.setGolsPorJogador(new HashMap<>());
        if (disputa.getAssistenciasPorJogador() == null) disputa.setAssistenciasPorJogador(new HashMap<>());
        for (Time t : disputa.getTimes()) {
            disputa.getGolsPorTime().putIfAbsent(t, 0);
            if (t.getJogadores() != null) {
                for (Jogador j : t.getJogadores()) {
                    disputa.getGolsPorJogador().putIfAbsent(j, 0);
                    disputa.getAssistenciasPorJogador().putIfAbsent(j, 0);
                }
            }
        }

        Time timeA = disputa.getTimes().getElementAt(0);
        Time timeB = disputa.getTimes().getElementAt(1);
        disputa.getGolsPorTime().putIfAbsent(timeA, disputa.getGolsPorTime().getOrDefault(timeA, 0));
        disputa.getGolsPorTime().putIfAbsent(timeB, disputa.getGolsPorTime().getOrDefault(timeB, 0));

        int golsA = random.nextInt(8); // 0..7
        int golsB = random.nextInt(8);

        // registra gols do time A (inclui chance de assistência 60%)
        for (int i = 0; i < golsA; i++) {
            if (timeA.getJogadores() != null && timeA.getJogadores().getSize() > 0) {
                int qtd = timeA.getJogadores().getSize();
                Jogador artilheiro = timeA.getJogadores().getElementAt(random.nextInt(qtd));
                disputa.getGolsPorJogador().put(artilheiro, disputa.getGolsPorJogador().getOrDefault(artilheiro, 0) + 1);
                if (qtd > 1 && random.nextDouble() < 0.6) {
                    Jogador assistente;
                    do { assistente = timeA.getJogadores().getElementAt(random.nextInt(qtd)); } while (assistente == artilheiro);
                    disputa.getAssistenciasPorJogador().put(assistente, disputa.getAssistenciasPorJogador().getOrDefault(assistente, 0) + 1);
                }
            }
        }
        // registra gols do time B (inclui chance de assistência 60%)
        for (int i = 0; i < golsB; i++) {
            if (timeB.getJogadores() != null && timeB.getJogadores().getSize() > 0) {
                int qtd = timeB.getJogadores().getSize();
                Jogador artilheiro = timeB.getJogadores().getElementAt(random.nextInt(qtd));
                disputa.getGolsPorJogador().put(artilheiro, disputa.getGolsPorJogador().getOrDefault(artilheiro, 0) + 1);
                if (qtd > 1 && random.nextDouble() < 0.6) {
                    Jogador assistente;
                    do { assistente = timeB.getJogadores().getElementAt(random.nextInt(qtd)); } while (assistente == artilheiro);
                    disputa.getAssistenciasPorJogador().put(assistente, disputa.getAssistenciasPorJogador().getOrDefault(assistente, 0) + 1);
                }
            }
        }

        disputa.getGolsPorTime().put(timeA, disputa.getGolsPorTime().getOrDefault(timeA, 0) + golsA);
        disputa.getGolsPorTime().put(timeB, disputa.getGolsPorTime().getOrDefault(timeB, 0) + golsB);

        return String.format("%s %d x %d %s", timeA.getNome(), golsA, golsB, timeB.getNome());
    }

}

