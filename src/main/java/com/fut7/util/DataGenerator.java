package com.fut7.util;

import java.util.Locale;
import java.util.Random;

import com.github.javafaker.Faker;
import com.fut7.models.Jogador;
import com.fut7.models.Time;


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
            .estadio(faker.company().name() + " Stadium")
            .cidadeSede(faker.address().cityName())
            .tecnico(faker.name().fullName())
            .build();
    }
}

