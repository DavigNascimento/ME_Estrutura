package com.fut7.models.disputas;

import com.fut7.models.TAD.Fila;
import com.fut7.util.DataGenerator;

import lombok.Data;

@Data
public class Oitavas implements Fase {

    private Fila<Disputa> oitavas;

    public void adicionarDisputa(Disputa disputa) {
        if (oitavas == null) {
            oitavas = new Fila<>();
        }
        
        if(oitavas.getSize() >= 16) {
            return;
        }
        disputa.setFase(TiposDisputa.OITAVAS);
        oitavas.enqueue(disputa);
    }

    public Disputa popDisputa() {
        if (oitavas != null && !(oitavas.getSize() == 0)) {
            return oitavas.dequeue();
        }
        return null;
    }

    public Fase realizarDisputas() {
        if (oitavas == null || oitavas.getSize() == 0) {
            System.out.println("Nenhuma disputa para realizar nas Oitavas.");
            return null;
        }
        
        Oitavas resultados = new Oitavas();
        while (oitavas.getSize() > 0) {
            Disputa disputa = oitavas.dequeue();
            DataGenerator.gerarResultado(disputa);
            resultados.adicionarDisputa(disputa);
        }

        return resultados;
    }

    public Fila<Disputa> getDisputas() {
        return oitavas;
    }
}
