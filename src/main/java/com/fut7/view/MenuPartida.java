package com.fut7.view;

import com.fut7.models.TAD.Lista;
import com.fut7.models.disputas.Disputa;
import com.fut7.repository.TimeRepository;
import com.fut7.util.DataGenerator;

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
                    gerarPartidas();
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

    private void gerarPartidas() {
        System.out.println("\n--- GERAR PARTIDAS ---");
        partidas.add(DataGenerator.gerarDisputaAleatoria(TimeRepository.getAll()));
        System.out.println("\n✓ Partida gerada com sucesso:\n");
    }

    private void listarPartidas() {
        System.out.println("\n--- LISTA DE Partidas ---");
        if (partidas.getSize() == 0) {
            System.out.println("Nenhum jogador cadastrado.\n");
        } else {
            System.out.println("Partidas cadastradas:");
            for (int i = 0; i < partidas.getSize(); i++) {
                Disputa partida = partidas.getElementAt(i);
                System.out.println((i + 1) + ". " + partida.getFase());
                System.out.println("   Times: " + partida.getTimes().getElementAt(0).getNome() + " vs " + partida.getTimes().getElementAt(1).getNome());
            }
            System.out.println("Total: " + partidas.getSize() + " partidas(es)\n");
        }
    }
}
