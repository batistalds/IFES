
package controleestoque.fronteira;

import java.util.Scanner;

public abstract class Cadastro {
    
    private static final short OPCAO_INSERIR = 1;
    private static final short OPCAO_LISTAR = 2;
    private static final short OPCAO_ALTERAR = 3;
    private static final short OPCAO_EXCLUIR = 4;
    private static final short OPCAO_VOLTAR_MENU_PRINCIPAL = 5;

    protected Scanner input;
    
    public void exibirMenu() {
        input = new Scanner(System.in);
        short opcao = 0;
        while (opcao != OPCAO_VOLTAR_MENU_PRINCIPAL) {
            System.out.printf("\n\n%s\n", obterTituloMenu());
            System.out.println("1 - Inserir");
            System.out.println("2 - Listar");
            System.out.println("3 - Alterar");
            System.out.println("4 - Excluir");
            System.out.printf("5 - %s\n", obterMensagemSairDoMenu());
            System.out.print("----> Escolha a sua opção: ");

            opcao = input.nextShort();
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
            default:
                if (opcao != OPCAO_VOLTAR_MENU_PRINCIPAL) {
                    System.err.println("Opção inválida/inexistente.");
                }
        }
    }
    
    protected abstract void inserir();
    
    protected abstract void listar();
    
    protected abstract void alterar();
    
    protected abstract void excluir();
    
}
