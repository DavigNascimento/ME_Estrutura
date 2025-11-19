package com.fut7.models.disputas;

import com.fut7.models.Time;
import com.fut7.models.TAD.Fila;
import com.fut7.models.TAD.Lista;

import lombok.Data;

@Data
public class Semifinal implements Fase{
    
    private Lista<Disputa> semifinal;

    public void adicionarDisputa(Disputa disputa) {
        if (semifinal == null) {
            semifinal = new Lista<>();
        }

        if(semifinal.getSize() >= 4) {
            return;
        }
        disputa.setFase(TiposDisputa.SEMI_FINAL);
        semifinal.add(disputa);
    }

    public Disputa popDisputa() {
        if (semifinal != null && !(semifinal.getSize() == 0)) {
            return semifinal.remove(0);
        }
        return null;
    }

    public Disputa getDisputaTerceiro(){
        Lista<Time> times = new Lista<>();
        Disputa disputa = null;

        if (semifinal != null && semifinal.getSize() == 4) {
            times.add(semifinal.getElementAt(0).getPerdedor());
            times.add(semifinal.getElementAt(1).getPerdedor());
        }
        disputa = Disputa.builder()
                .times(times)
                .fase(TiposDisputa.TERCEIRO_COLOCADO)
                .build();
        return disputa;
    }

    public Fila<Disputa> getDisputas() {
        Fila<Disputa> filaSemifinal = new Fila<>();
        for (int i = 0; i < semifinal.getSize(); i++) {
            filaSemifinal.enqueue(semifinal.getElementAt(i));
        }
        return filaSemifinal;
    }
}
