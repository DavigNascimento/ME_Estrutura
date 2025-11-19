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
            optionHandler.addOption("Mostrar Detalhes");
            optionHandler.addOption("Atribuir Jogadores");


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
                    
                case 3:
                    mostrarDetalhes();
                    break;

                case 4:
                    atribuirJogadores();
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

    private void mostrarDetalhes() {
        System.out.println("\n--- DETALHES DO TIME ---");
        Lista<Time> times = TimeRepository.getAll();
        if (times.getSize() == 0) {
            System.out.println("Nenhum time cadastrado.\n");
            return;
        }

        for (int i = 0; i < times.getSize(); i++) {
            Time time = times.getElementAt(i);
            System.out.println("Detalhes do Time: " + time.getNome());
            System.out.println("Cidade: " + time.getCidadeSede());
            System.out.println("Patrocínio: " + time.getPatrocinio());
            System.out.println("Técnico: " + time.getTecnico());
            System.out.println("Jogadores:");
            if (time.getJogadores().getSize() == 0) {
                System.out.println("  Nenhum jogador atribuído.");
            } else {
                for (int j = 0; j < time.getJogadores().getSize(); j++) {
                    System.out.println("  - " + time.getJogadores().getElementAt(j).getNome());
                }
            }
            System.out.println();
        }
    }

    private void atribuirJogadores() {
        System.out.println("\n--- ATRIBUIR JOGADORES AOS TIMES ---");
        Lista<Time> times = TimeRepository.getAll();
        if (times.getSize() == 0) {
            System.out.println("Nenhum time cadastrado. Por favor, crie um time primeiro.\n");
            return;
        }

        for (int i = 0; i < times.getSize(); i++) {
            Time time = times.getElementAt(i);
            System.out.println("Atribuindo jogadores para o time: " + time.getNome());
            DataGenerator.atribuirJogadoresAoTime(time);
        }
        System.out.println("Jogadores atribuídos com sucesso!\n");
    }
}