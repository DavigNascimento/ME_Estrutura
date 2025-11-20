package com.fut7.view;

import com.fut7.models.Campeonato;
import com.fut7.models.disputas.Disputa;
import com.fut7.models.disputas.Fase;

public class MenuCampeonato {
    
    public MenuCampeonato() {
        System.out.println("\n=== MENU Campeonato ===");
        
        while (true) {
            OptionHandler optionHandler = new OptionHandler();
            optionHandler.addOption("Iniciar Campeonato");
            optionHandler.addOption("Avançar Fase");
            optionHandler.addOption("Listar resultados da Fase Atual");
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
                    listarResultadosFaseAtual();
                    break;

                case 4:
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
    
        switch (Campeonato.getFaseAtual()) {
            case OITAVAS:
                Campeonato.paraQuartas();
                System.out.println("Avançou para as Quartas de Final.");
                break;
    
            case QUARTAS:
                Campeonato.paraSemifinal();
                System.out.println("Avançou para a Semifinal.");
                break;
    
            case SEMI_FINAL:
                Campeonato.paraFinal();
                System.out.println("Avançou para a Final.");
                break;
    
            case FINAL:
                Campeonato.getCampeao();
                System.out.println("Campeonato encerrado.");
                Campeonato.resetar();
                break;
    
            default:
                System.out.println("Fase desconhecida.");
                break;
        }
    }

    private void listarResultadosFaseAtual() {
        switch (Campeonato.getFaseAtual()) {
            case QUARTAS:
                Fase oitavas = Campeonato.getResultados().getElementAt(0);
                for(int i = 0; i < oitavas.getDisputas().getSize(); i++) {
                    Disputa disputa = oitavas.getDisputas().getElementAt(i);
                    System.out.println(disputa.getTimes().getElementAt(0).getNome() + " " +
                                       disputa.getGolsPorTime().get(disputa.getTimes().getElementAt(0)) +
                                       " x " +
                                       disputa.getGolsPorTime().get(disputa.getTimes().getElementAt(1)) + " " +
                                       disputa.getTimes().getElementAt(1).getNome());
                }
                System.out.println("Resultados das Oitavas de Final:");
                break;
    
            case SEMI_FINAL:
                System.out.println("Resultados das Quartas de Final:");
                break;
    
            case FINAL:
                System.out.println("Resultados da Semifinal:");
                break;
    
            case FIM:
                System.out.println("Resultados da Final:");
                break;
    
            default:
                System.out.println("Fase desconhecida.");
                return;
        }
    }

}
