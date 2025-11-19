package com.fut7.view;

public class Menu {
    
    public Menu() {
        System.out.println("Fut-7");

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
                
                case 1:
                    new MenuTime();
                    break;

                case 2:
                    new MenuJogador();
                    break;
            
                default:
                    System.out.println("Opção escolhida: " + choice);
                    break;
            }
        }
    }
}
