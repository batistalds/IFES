
package bladeofnight.fronteira;

import java.util.Scanner;

public class MenuPrincipal {
    
    private static final byte OPCAO_CADASTRO_RANKING = 1;
    private static final byte OPCAO_CADASTRO_BACKGROUND = 2;
    private static final byte OPCAO_CADASTRO_HUD = 3;
    private static final byte OPCAO_CADASTRO_NAVE = 4; // Pode ser Inimigo ou Jogador; Inimigo pode ser Boss
    private static final byte OPCAO_CADASTRO_ITEM = 5;
    private static final byte OPCAO_CADASTRO_PROJETIL = 6;
    private static final byte OPCAO_TERMINAR_PROGRAMA = 7;
    
    public void exibirMenu() {
        Scanner input = new Scanner(System.in);
        byte opcao = 0;
        while (opcao != OPCAO_TERMINAR_PROGRAMA) {
            System.out.println("\n\nOpções de Entidades:");
            System.out.println("1 - Ranking");
            System.out.println("2 - Background (Plano de Fundo)");
            System.out.println("3 - HUD (Interface Gráfica)");
            System.out.println("4 - Nave");
            System.out.println("5 - Item");
            System.out.println("6 - Projetil");
            System.out.println("7 - Encerrar Programa");
            System.out.print("----> Escolha a sua opção: ");

            opcao = input.nextByte();
            ProcessarOpcaoUsuario(opcao);
        }
        System.out.println("\nPrograma encerrado.\n");
    }
    
    private void ProcessarOpcaoUsuario(byte opcao) {
        switch (opcao) {
            case OPCAO_CADASTRO_RANKING:
                CadastroRanking rank = new CadastroRanking();
                rank.exibirMenu();
                break;
            case OPCAO_CADASTRO_BACKGROUND:
                CadastroBackground back = new CadastroBackground();
                back.exibirMenu();
                break;
            case OPCAO_CADASTRO_HUD:
                MenuHUD meuMenuHud = new MenuHUD();
                meuMenuHud.exibirMenuHud();
                break;
            case OPCAO_CADASTRO_NAVE:
                CadastroNave nave = new CadastroNave();
                nave.exibirMenu();
                break;
            case OPCAO_CADASTRO_ITEM:
                CadastroItem item = new CadastroItem();
                item.exibirMenu();
                break;
            case OPCAO_CADASTRO_PROJETIL:
                CadastroProjetil projetil = new CadastroProjetil();
                projetil.exibirMenu();
                break;
            default:
                if (opcao != OPCAO_TERMINAR_PROGRAMA) {
                    System.err.println("Opção inválida/inexistente.");
                }
        }
    }
}
