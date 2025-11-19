package com.fut7.util;

import java.util.Locale;
import java.util.Random;

import com.github.javafaker.Faker;
import com.fut7.models.Jogador;


public class DataGenerator {
    private final Faker faker;
    private final Random random;

    public DataGenerator() {
        this.faker = new Faker(Locale.of("pt", "BR"));
        this.random = new Random();
    }


    public Jogador gerarJogador() {
        return Jogador.builder()
            .nome(faker.name().fullName())
            .idade(random.nextInt(17, 41))
            .altura(Math.round((random.nextDouble(1.60, 2.00)) * 100.0) / 100.0)
            .nacionalidade(faker.country().name())
            .numeroCamisa(0)
            .posicao(gerarPosicao())
            .build();
    }

    private String gerarPosicao() {
        String[] posicoes = {"Goleiro", "Zagueiro", "Lateral", "Volante", "Meia", "Atacante"};
        return posicoes[random.nextInt(posicoes.length)];
    }
}
