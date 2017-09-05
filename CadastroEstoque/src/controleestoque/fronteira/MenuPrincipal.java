package controleestoque.fronteira;

import java.util.Scanner;

public class MenuPrincipal {

    private static final short OPCAO_CADASTRO_CLIENTE = 1;
    private static final short OPCAO_CADASTRO_FORNECEDOR = 2;
    private static final short OPCAO_CADASTRO_PRODUTO = 3;
    private static final short OPCAO_CADASTRO_FUNCIONARIO = 4;
    private static final short OPCAO_CADASTRO_COMPRA = 5;
    private static final short OPCAO_CADASTRO_VENDA = 6;
    private static final short OPCAO_TERMINAR_PROGRAMA = 7;

    public void exibirMenu() {
        Scanner input = new Scanner(System.in);
        short opcao = 0;
        while (opcao != OPCAO_TERMINAR_PROGRAMA) {
            System.out.println("\n\nOpções:");
            System.out.println("1 - Cliente");
            System.out.println("2 - Fornecedor");
            System.out.println("3 - Produto");
            System.out.println("4 - Funcionario");
            System.out.println("5 - Compra");
            System.out.println("6 - Venda");
            System.out.println("7 - Encerrar Programa");
            System.out.print("----> Escolha a sua opção: ");

            opcao = input.nextShort();
            ProcessarOpcaoUsuario(opcao);
        }
        System.out.println("\nPrograma encerrado.\n");
    }

    private void ProcessarOpcaoUsuario(short opcao) {
        switch (opcao) {
            case OPCAO_CADASTRO_CLIENTE:
                CadastroCliente meuCliente = new CadastroCliente();
                meuCliente.exibirMenu();
                break;
            case OPCAO_CADASTRO_FORNECEDOR:
                CadastroFornecedor meuFornecedor = new CadastroFornecedor();
                meuFornecedor.exibirMenu();
                break;
            case OPCAO_CADASTRO_PRODUTO:
                CadastroProduto meuProduto = new CadastroProduto();
                meuProduto.exibirMenu();
                break;
            case OPCAO_CADASTRO_FUNCIONARIO:
                CadastroFuncionario meuFuncionario = new CadastroFuncionario();
                meuFuncionario.exibirMenu();
                break;
            case OPCAO_CADASTRO_COMPRA:
                break;
            case OPCAO_CADASTRO_VENDA:
                break;
            default:
                if (opcao != OPCAO_TERMINAR_PROGRAMA) {
                    System.err.println("Opção inválida/inexistente.");
                }
        }
    }
}
