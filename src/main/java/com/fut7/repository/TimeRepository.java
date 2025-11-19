package com.fut7.repository;

import com.fut7.models.Time;
import com.fut7.models.TAD.Lista;

public class TimeRepository {
    
    private static Lista<Time> times = new Lista<>();
    private static long lastId = 0;

    public static Lista<Time> getAll()
    {
        return times;
    }

    public static Time findById(long id)
    {
        for(Time t: times)
        {
            if(t.getId() == id)
            {
                return t;
            }
        }
        return null;
    }

    public static Time findByName(String nome)
    {
        for(Time t: times)
        {
            if(t.getNome().equals(nome))
            {
                return t;
            }
        }
        return null;
    }

    public static void add(Time t)
    {
        if (null == t) {
            return;
        }
        t.setId(lastId);
        times.add(t);
        lastId++;
    }

    public static void remove(String nome)
    {
        Time time = findByName(nome);
        if (null == time) {
            return;
        }
        times.remove(time);
    }

    public static void remove(long id)
    {
        Time time = findById(id);
        if (null == time) {
            return;
        }
        times.remove(time);
    }


    public static void update(Time t)
    {
        Time time = findById(t.getId());
        time.setCidadeSede(t.getCidadeSede());
        time.setPatrocinio(t.getPatrocinio());
        time.setJogadores(t.getJogadores());
        time.atribuirNumeros();
        time.setNome(t.getNome());
        time.setTecnico(t.getTecnico());
        time.setTitulos(t.getTitulos());
    }
}
