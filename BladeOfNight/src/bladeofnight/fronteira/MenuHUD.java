
package bladeofnight.fronteira;

import java.util.Scanner;

public class MenuHUD {
    
    private static final byte OPCAO_CADASTRO_HUD = 1;
    private static final byte OPCAO_CADASTRO_HUD_PRIORIDADE = 2;
    private static final byte OPCAO_CADASTRO_HUD_TEXTOS = 3;
    private static final byte OPCAO_CADASTRO_HUD_NUMEROS = 4;
    private static final byte OPCAO_VOLTAR_MENU_PRINCIPAL = 5;
    
    public void exibirMenuHud() {
        Scanner input = new Scanner(System.in);
        byte opcao = 0;
        while (opcao != OPCAO_VOLTAR_MENU_PRINCIPAL) {
            System.out.println("\n\nOpções de Entidades:");
            System.out.println("1 - HUD (Interface Gráfica)");
            System.out.println("2 - Prioridades HUD");
            System.out.println("3 - Textos HUD");
            System.out.println("4 - Números HUD");
            System.out.println("5 - Retornar ao Menu Principal");
            System.out.print("----> Escolha a sua opção: ");

            opcao = input.nextByte();
            ProcessarOpcaoUsuario(opcao);
        }
    }
    
    private void ProcessarOpcaoUsuario(byte opcao) {
        switch (opcao) {
            case OPCAO_CADASTRO_HUD:
                CadastroHUD hud = new CadastroHUD();
                hud.exibirMenu();
                break;
            case OPCAO_CADASTRO_HUD_PRIORIDADE:
                CadastroPrioridadeHUD hudP = new CadastroPrioridadeHUD();
                hudP.exibirMenu();
                break;
            case OPCAO_CADASTRO_HUD_TEXTOS:
                CadastroTextoHUD hudT = new CadastroTextoHUD();
                hudT.exibirMenu();
                break;
            case OPCAO_CADASTRO_HUD_NUMEROS:
                CadastroNumeroHUD hudN = new CadastroNumeroHUD();
                hudN.exibirMenu();
                break;
            default:
                if (opcao != OPCAO_VOLTAR_MENU_PRINCIPAL) {
                    System.err.println("Opção inválida/inexistente.");
                }
        }
    }
    
}
