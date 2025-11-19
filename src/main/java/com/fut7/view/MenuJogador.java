package com.fut7.view;

import com.fut7.models.Jogador;
import com.fut7.models.TAD.Lista;
import com.fut7.repository.JogadorRepository;
import com.fut7.util.DataGenerator;

public class MenuJogador {
    
    public MenuJogador() {
        System.out.println("\n=== MENU JOGADORES ===");
        
        while (true) {
            OptionHandler optionHandler = new OptionHandler();
            optionHandler.addOption("Listar Jogadores");
            optionHandler.addOption("Gerar Jogador Aleatório");


            int choice = optionHandler.getOption();

            switch (choice) {
                case 0:
                    System.out.println("Voltando ao menu principal...\n");
                    return;
                
                case 1:
                    listarJogadores();
                    break;
                
                case 2:
                    gerarJogadorAleatorio();
                    break;

                default:
                    System.out.println("Opção escolhida: " + choice);
                    break;
            }
        }
    }
    
    private void gerarJogadorAleatorio() {
        Jogador novoJogador = DataGenerator.gerarJogador();
        JogadorRepository.add(novoJogador);
        System.out.println("\n✓ Jogador gerado com sucesso:");
        System.out.println(novoJogador);
        System.out.println();
    }

    private void listarJogadores() {
        System.out.println("\n--- LISTA DE JOGADORES ---");
        Lista<Jogador> jogadores = JogadorRepository.getAll();
        if (jogadores.getSize() == 0) {
            System.out.println("Nenhum jogador cadastrado.\n");
        } else {
            jogadores.print();
            System.out.println("Total: " + jogadores.getSize() + " jogador(es)\n");
        }
    }
}
