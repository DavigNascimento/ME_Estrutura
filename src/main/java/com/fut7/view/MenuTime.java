package com.fut7.view;

import com.fut7.models.Time;
import com.fut7.models.TAD.Lista;
import com.fut7.repository.TimeRepository;
import com.fut7.util.DataGenerator;

public class MenuTime {

    public MenuTime() {
        System.out.println("\n=== MENU Times ===");
        
        while (true) {
            OptionHandler optionHandler = new OptionHandler();
            optionHandler.addOption("Gerar Time Aleatório");
            optionHandler.addOption("Listar Times");


            int choice = optionHandler.getOption();

            switch (choice) {
                case 0:
                    System.out.println("Voltando ao menu principal...\n");
                    return;
                
                case 1:
                    gerarTimeAleatorio();
                    break;
                
                case 2:
                    listarTimes();
                    break;

                
                default:
                    System.out.println("Opção escolhida: " + choice);
                    break;
            }
        }
    }


    private void gerarTimeAleatorio() {
        Time novoTime = DataGenerator.gerarTime();
        TimeRepository.add(novoTime);
        System.out.println("\n✓ Time gerado com sucesso:");
        System.out.println(novoTime);
        System.out.println();
    }

    private void listarTimes() {
        System.out.println("\n--- LISTA DE TIMES ---");
        Lista<Time> times = TimeRepository.getAll();
        if (times.getSize() == 0) {
            System.out.println("Nenhum time cadastrado.\n");
        } else {
            times.print();
            System.out.println("Total: " + times.getSize() + " time(es)\n");
        }
    }
}