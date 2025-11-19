package com.fut7.view;

import com.fut7.models.Time;
import com.fut7.models.Jogador;
import com.fut7.models.TAD.Lista;
import com.fut7.util.DataGenerator;

public class Menu {
    
    private Lista<Jogador> jogadores;

    public Menu() {
        System.out.println("Fut-7");
        this.jogadores = new Lista<>();

        while (true) {
            OptionHandler optionHandler = new OptionHandler();
            optionHandler.addOption("Times");
            optionHandler.addOption("Jogadores");
            optionHandler.addOption("Partidas");
            optionHandler.addOption("Relatórios");
            int choice = optionHandler.getOption();

            switch (choice)
            {
                case 0:
                    System.out.println("Saindo...");
                    return;
                
                case 2:
                    menuJogadores();
                    break;
            
                default:
                    System.out.println("Opção escolhida: " + choice);
                    break;
            }

        }
    }

    private void menuJogadores() {
        System.out.println("\n=== MENU JOGADORES ===");
        
        while (true) {
            OptionHandler optionHandler = new OptionHandler();
            optionHandler.addOption("Listar Jogadores");
            optionHandler.addOption("Gerar Jogador Aleatório");
            optionHandler.addOption("Gerar Time Aleatório");
            optionHandler.addOption("Lista Times");


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

                case 3:
                    gerarTimeAleatorio();
                    break;
                
                case 4:
                    listarTimes();
                    break;

                
                default:
                    System.out.println("Opção escolhida: " + choice);
                    break;
            }
        }
    }
    
    private void listarJogadores() {
        System.out.println("\n--- LISTA DE JOGADORES ---");
        if (jogadores.getSize() == 0) {
            System.out.println("Nenhum jogador cadastrado.\n");
        } else {
            jogadores.print();
            System.out.println("Total: " + jogadores.getSize() + " jogador(es)\n");
        }
    }
    
    private void gerarJogadorAleatorio() {
        Jogador novoJogador = DataGenerator.gerarJogador();
        jogadores.add(novoJogador);
        System.out.println("\n✓ Jogador gerado com sucesso:");
        System.out.println(novoJogador);
        System.out.println();
    }
    private void gerarTimeAleatorio() {
        Time novoTime = DataGenerator.gerarTime();
        System.out.println("\n✓ Time gerado com sucesso:");
        System.out.println(novoTime);
        System.out.println();
    }
    private void listarTimes() {
        System.out.println("\n--- LISTA DE TIMES ---");
        // Implementar a lógica para listar os times cadastrados
        System.out.println("Funcionalidade não implementada ainda.\n");
    }
}
