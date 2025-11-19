package com.fut7.view;

import java.util.Scanner;

import com.fut7.models.TAD.Lista;

import lombok.Data;

@Data
public class OptionHandler {
    
    private Scanner scanner = new Scanner(System.in);

    private Lista<String> options;

    public OptionHandler(Lista<String> options) {
        this.options = options;
    }

    public OptionHandler() {
        this.options = new Lista<String>();
    }

    public void addOption(String option) {
        options.add(option);
    }

    public int getOption()
    {
        int choice = -1;

        for(int i = 0; i < options.getSize(); i++)
        {
            System.out.println((i + 1) + " - " + options.getElementAt(i).toString());
        }
        System.out.println("0 - Sair");

        do {
            System.out.print(": ");
            choice = scanner.nextInt();
            if (choice < 0 || choice > options.getSize()) {
                System.out.println("Opção inválida. Tente novamente.");
            }
        } while (choice < 0 || choice > options.getSize());

        return choice;
    }

}
