package com.fut7.view;

import com.fut7.models.TAD.Lista;
import com.fut7.models.disputas.Disputa;

public class MenuPartida {
    
    private static Lista<Disputa> partidas = new Lista<>();

    public MenuPartida() {
        System.out.println("\n=== MENU PARTIDAS ===");
        
        while (true) {
            OptionHandler optionHandler = new OptionHandler();
            optionHandler.addOption("Gerar Partidas");
            optionHandler.addOption("Listar Partidas");
            optionHandler.addOption("Adicionar Partida");
            
            int choice = optionHandler.getOption();

            switch (choice) {
                case 0:
                    System.out.println("Voltando ao menu principal...\n");
                    return;
                
                case 1:
                    // gerarPartidas();
                    break;
                
                case 2:
                    listarPartidas();
                    break;

                default:
                    System.out.println("Opção escolhida: " + choice);
                    break;
            }
        }
    }

    private void listarPartidas() {
        System.out.println("\n--- LISTA DE JOGADORES ---");
        if (partidas.getSize() == 0) {
            System.out.println("Nenhum jogador cadastrado.\n");
        } else {
            partidas.print();
            System.out.println("Total: " + partidas.getSize() + " jogador(es)\n");
        }
    }
}
