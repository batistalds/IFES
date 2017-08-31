
package controleestoque.fronteira;

import cadastroestoque.Entidades.Produto;
import cadastroestoque.armazenamento.ArmazenamentoProduto;
import java.util.Scanner;

public class CadastroProduto {
        
    private static final short OPCAO_INSERIR = 1;
    private static final short OPCAO_LISTAR = 2;
    private static final short OPCAO_ALTERAR = 3;
    private static final short OPCAO_EXCLUIR = 4;
    private static final short OPCAO_VOLTAR_MENU_PRINCIPAL = 5;
    
    private Scanner input;
    
    public void exibirMenu() {
        input = new Scanner(System.in);
        short opcao = 0;
        while (opcao != OPCAO_VOLTAR_MENU_PRINCIPAL) {
            System.out.println("\n\nOpções do Cadastro de Produto:");
            System.out.println("1 - Inserir");
            System.out.println("2 - Listar");
            System.out.println("3 - Alterar");
            System.out.println("4 - Excluir");
            System.out.println("5 - Voltar ao Menu Principal");            
            System.out.print("----> Escolha a sua opção: ");
            
            opcao = input.nextShort();
            ProcessarOpcaoUsuario(opcao);
        }
    }
    
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
                if (opcao != OPCAO_VOLTAR_MENU_PRINCIPAL){
                    System.err.println("Opção inválida/inexistente.");
                }
        }
    }
    
    private void inserir() {
        System.out.println("\nInserir novo registro de produto\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        System.out.print("- Nome: ");
        String nome = input.nextLine();
        System.out.print("- Preço: ");
        double preco = input.nextDouble();
        
        Produto novoProduto = new Produto(codigo, nome, preco);
        ArmazenamentoProduto.inserir(novoProduto);        
    }
    
    private void listar() {
        System.out.println("\nListagem de produtos registrados\n");
        System.out.println("+--------+----------------------------+----------+");
        System.out.println("| CÓDIGO |           NOME             | PREÇO    |");
        System.out.println("+--------+----------------------------+----------+");
        for (Produto p : ArmazenamentoProduto.getLista()) {
            System.out.printf("| %6d | %-26s | %8.2f |\n", p.getCodigo(), p.getNome(), p.getPreco());
        }
        System.out.println("+--------+----------------------------+----------+");
    }
    
    private void alterar() {
        System.out.println("\nAlterar o registro do produto de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        
        Produto p = new Produto(codigo, "", 0);
        Produto produtoParaAlterar = ArmazenamentoProduto.buscar(p);
        
        if (produtoParaAlterar != null) {
            System.out.println("\n - Nome: " + produtoParaAlterar.getNome());
            System.out.print("---> Deseja alterar o nome? (s = sim / n = não): ");            
            input.nextLine(); // Consumindo quebra de linha            
            char opcaoNome = input.nextLine().charAt(0);
            String nome = produtoParaAlterar.getNome();
            if (opcaoNome == 's') {
                System.out.print("- Novo Nome: ");
                nome = input.nextLine();
                input.nextLine(); // Consumindo quebra de linha
            }
            
            System.out.printf("\n - Preço: %.2f", produtoParaAlterar.getPreco());
            System.out.print("---> Deseja alterar o preço? (s = sim / n = não): ");            
            char opcaoPreco = input.nextLine().charAt(0);
            double preco = produtoParaAlterar.getPreco();
            if (opcaoPreco == 's') {
                System.out.print("- Novo Preço: ");
                preco = input.nextDouble();
                input.nextLine(); // Consumindo quebra de linha
            }
            
            System.out.println("\nDeseja realmente modificar os dados informados? (s = sim / n = não)");
            System.out.println("- Código: " + produtoParaAlterar.getCodigo());
            System.out.println("- Nome..: " + nome);
            System.out.printf("- Preço..: %.2f\n", preco);
            System.out.print("---> (s = sim / n = não): ");
            char confirmacaoFinal = input.nextLine().charAt(0);
            if (confirmacaoFinal == 's') {
                Produto produtoAlterado = new Produto(codigo, nome, preco);
                ArmazenamentoProduto.alterar(produtoAlterado);
            }
            
        } else {
            System.err.println("\nProduto não encontrado. Código inexistente.");
            return;
        }
    }
    
    private void excluir() {
        System.out.println("\nExcluir o registro do produto de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        Produto produtoParaDeletar = ArmazenamentoProduto.buscar(new Produto(codigo, "", 0));
        
        if (produtoParaDeletar == null) {
            System.err.println("Produto não encontrado. Código inexistente.");
            return;
        }
        
        System.out.println("\nDeseja realmente excluir o produto informado? (s = sim / n = não)");
        System.out.println("- Código: " + produtoParaDeletar.getCodigo());
        System.out.println("- Nome..: " + produtoParaDeletar.getNome());
        System.out.printf("- Preço..: %.2f\n", produtoParaDeletar.getPreco());
        System.out.print("---> (s = sim / n = não): ");
        char confirmacaoFinal = input.nextLine().charAt(0);
        if (confirmacaoFinal == 's') {
            ArmazenamentoProduto.excluir(produtoParaDeletar);
        }
    }
}
