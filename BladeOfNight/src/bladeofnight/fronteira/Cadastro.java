
package bladeofnight.fronteira;

import java.util.Scanner;

public abstract class Cadastro {
    
    private static final byte OPCAO_INSERIR = 1;
    private static final byte OPCAO_LISTAR = 2;
    private static final byte OPCAO_ALTERAR = 3;
    private static final byte OPCAO_EXCLUIR = 4;
    private static final byte OPCAO_BUSCAR = 5;
    private static final byte OPCAO_VOLTAR_MENU_PRINCIPAL = 6;
    
    protected Scanner input;
    
    public void exibirMenu() {
        input = new Scanner(System.in);
        byte opcao = 0;
        while (opcao != OPCAO_VOLTAR_MENU_PRINCIPAL) {
            System.out.printf("\n\n%s\n", obterTituloMenu());
            System.out.println("1 - Inserir");
            System.out.println("2 - Listar");
            System.out.println("3 - Alterar");
            System.out.println("4 - Excluir");
            System.out.println("5 - Buscar");
            System.out.printf("6 - %s\n", obterMensagemSairDoMenu());
            System.out.print("----> Escolha a sua opção: ");

            opcao = input.nextByte();
            ProcessarOpcaoUsuario(opcao);
        }
    }
    
    protected abstract String obterTituloMenu();
    
    protected abstract String obterMensagemSairDoMenu();

    private void ProcessarOpcaoUsuario(short opcao) {
        switch (opcao) {
            case OPCAO_INSERIR:
                inserir();
                break;
            case OPCAO_LISTAR:
                listar();
                break;
            case OPCAO_ALTERAR:
                alterar();
                break;
            case OPCAO_EXCLUIR:
                excluir();
                break;
            case OPCAO_BUSCAR:
                buscar();
                break;
            default:
                if (opcao != OPCAO_VOLTAR_MENU_PRINCIPAL) {
                    System.err.println("Opção inválida/inexistente.");
                }
        }
    }
    
    protected abstract boolean inserir();
    
    protected abstract boolean alterar();
    
    protected abstract boolean excluir();
    
    protected abstract void listar();
    
    protected abstract boolean buscar();
}
