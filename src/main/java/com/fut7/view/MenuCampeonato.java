package com.fut7.view;

import com.fut7.models.Campeonato;

public class MenuCampeonato {
    
    public MenuCampeonato() {
        System.out.println("\n=== MENU Campeonato ===");
        
        while (true) {
            OptionHandler optionHandler = new OptionHandler();
            optionHandler.addOption("Iniciar Campeonato");
            optionHandler.addOption("Avançar Fase");
            optionHandler.addOption("Resetar Campeonato");
            
            int choice = optionHandler.getOption();

            switch (choice) {
                case 0:
                    System.out.println("Voltando ao menu principal...\n");
                    return;
                
                case 1:
                    iniciarCampeonato();
                    break;
                
                case 2:
                    proximaFase();
                    break;
                
                case 3:
                    Campeonato.resetar();
                    System.out.println("Campeonato resetado.");
                    break;

                default:
                    System.out.println("Opção escolhida: " + choice);
                    break;
            }
        }
    }

    private void iniciarCampeonato() {
        if(Campeonato.iniciado()) {
            System.out.println("Campeonato já iniciado!");
            return;
        }
        Campeonato.iniciar();

        try {
            Campeonato.povoarOitavas();

        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            Campeonato.resetar();
            return;
        }
        System.out.println("Campeonato iniciado com sucesso!");
    }

    private void proximaFase() {
        if(!Campeonato.iniciado()) {
            System.out.println("Campeonato não iniciado. Inicie o campeonato primeiro.");
            return;
        }
        System.err.println("Funcionalidade ainda não implementada.");
        //System.out.println("Avançou para a próxima fase do campeonato.");
    }
}
