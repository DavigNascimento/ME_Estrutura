package com.fut7.repository;

import com.fut7.models.Jogador;
import com.fut7.models.TAD.Lista;

public class JogadorRepository {
    
    private static Lista<Jogador> jogadores = new Lista<>();
    private static long lastId = 0;

    public static Lista<Jogador> getAll()
    {
        return jogadores;
    }

    public static Jogador findById(long id)
    {
        for(Jogador j: jogadores)
        {
            if(j.getId() == id)
            {
                return j;
            }
        }
        return null;
    }

    public static Jogador findByName(String nome)
    {
        for(Jogador j: jogadores)
        {
            if(j.getNome().equals(nome))
            {
                return j;
            }
        }
        return null;
    }

    public static void add(Jogador j)
    {
        if (null == j) {
            return;
        }
        j.setId(lastId);
        jogadores.add(j);
        lastId++;
    }

    public static void remove(String nome)
    {
        Jogador jogador = findByName(nome);
        if (null == jogador) {
            return;
        }
        jogadores.remove(jogador);
    }

    public static void remove(long id)
    {
        Jogador jogador = findById(id);
        if (null == jogador) {
            return;
        }
        jogadores.remove(jogador);
    }

    public static void update(Jogador j)
    {
        Jogador jogador = findById(j.getId());
        jogador.setAltura(j.getAltura());
        jogador.setIdade(j.getIdade());
        jogador.setNacionalidade(j.getNacionalidade());
        jogador.setNome(j.getNome());
        jogador.setNumeroCamisa(j.getNumeroCamisa());
        jogador.setPosicao(j.getPosicao());
    }
}
