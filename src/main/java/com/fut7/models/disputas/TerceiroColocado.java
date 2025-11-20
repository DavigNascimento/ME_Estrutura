package com.fut7.models.disputas;

import com.fut7.models.TAD.Fila;
import com.fut7.util.DataGenerator;

import lombok.Data;

@Data
public class TerceiroColocado implements Fase {
    
    private Fila<Disputa> disputa;

    public void adicionarDisputa(Disputa disputa) {
        this.disputa = new Fila<>();
        this.disputa.enqueue(disputa);
    }

    public Disputa popDisputa() {
        if (disputa != null && disputa.getSize() > 0) {
            return disputa.dequeue();
        }
        return null;
    }

    public Fase realizarDisputas() {
        if (disputa == null || disputa.getSize() == 0) {
            System.out.println("Nenhuma disputa para realizar para o Terceiro Colocado.");
            return null;
        }

        TerceiroColocado resultados = new TerceiroColocado();
        while (disputa.getSize() > 0) {
            Disputa disputa = this.disputa.dequeue();
            DataGenerator.gerarResultado(disputa);

            System.out.println("Disputa pelo Terceiro Colocado realizada entre: " +
                disputa.getTimes().getElementAt(0).getNome() + " e " + disputa.getTimes().getElementAt(1).getNome());

            resultados.adicionarDisputa(disputa);
        }
        
        return resultados;
    }

    public Fila<Disputa> getDisputas() {
        return this.disputa;
    }
}
