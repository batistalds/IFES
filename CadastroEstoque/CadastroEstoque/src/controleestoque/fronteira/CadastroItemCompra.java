
package controleestoque.fronteira;

import cadastroestoque.Entidades.Compra;
import cadastroestoque.Entidades.ItemCompra;
import cadastroestoque.Entidades.Produto;
import cadastroestoque.armazenamento.ArmazenamentoItemCompra;
import cadastroestoque.armazenamento.ArmazenamentoProduto;
import java.util.Scanner;

public class CadastroItemCompra {
    
    private static final short OPCAO_INSERIR = 1;
    private static final short OPCAO_LISTAR = 2;
    private static final short OPCAO_ALTERAR = 3;
    private static final short OPCAO_EXCLUIR = 4;
    private static final short OPCAO_CONCLUIR_CADASTRO = 5;

    private Scanner input;
    
    private Compra compraReferencia;

    public CadastroItemCompra(Compra c) {
        ArmazenamentoItemCompra.iniciarLista();
        ArmazenamentoItemCompra.getLista().addAll(c.getItensCompra());
        compraReferencia = c;
    }
    
    public void exibirMenu() {
        input = new Scanner(System.in);
        short opcao = 0;
        while (opcao != OPCAO_CONCLUIR_CADASTRO) {
            System.out.println("\n\nOpções do Cadastro de Itens da Compras:");
            System.out.println("1 - Inserir");
            System.out.println("2 - Listar");
            System.out.println("3 - Alterar");
            System.out.println("4 - Excluir");
            System.out.println("5 - Concluir o cadastro dos itens da compra");
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
                if (opcao != OPCAO_CONCLUIR_CADASTRO) {
                    System.err.println("Opção inválida/inexistente.");
                }
        }
    }
    
    private void inserir() {
        System.out.println("\nInserir novo registro de item de compra\n");
        
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha

        Produto produto = null;
        do {
            System.out.print("- Produto (Código): ");
            long codigoProduto = input.nextLong();
            input.nextLine(); // Consumindo quebra de linha
            produto = ArmazenamentoProduto.buscar(new Produto(codigoProduto));
            if (produto == null) {
                System.err.println("Produto inválido/inexistente.");
            }
        } while (produto == null);
        
        System.out.print("- Preço: ");
        double precoCompra = input.nextDouble();
        input.nextLine(); // Consumindo quebra de linha
        
        System.out.print("- Quantidade: ");
        int quantidade = input.nextInt();
        input.nextLine(); // Consumindo quebra de linha
        
        ItemCompra novoItemCompra = new ItemCompra(codigo, compraReferencia, produto, precoCompra, quantidade);
        ArmazenamentoItemCompra.inserir(novoItemCompra);
    }

    protected void listar() {
        System.out.println("\nListagem de itens de compra registrados\n");
        System.out.println("+--------+-----------------------------+----------------+------------+");
        System.out.println("| CÓDIGO |           PRODUTO           |     PREÇO      | QUANTIDADE |");
        System.out.println("+--------+-----------------------------+----------------+------------+");
        for (ItemCompra ic : ArmazenamentoItemCompra.getLista()) {
            System.out.printf("| %6d | %-27s | %14.2f | %10d |\n",
                        ic.getCodigo(), ic.getProduto().getNome(), ic.getPrecoCompra(), ic.getQuantidade());
        }
        System.out.println("+--------+-----------------------------+----------------+------------+");
    }

    private void alterar() {
        System.out.println("\nAlterar o registro de Item de Compra de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();

        ItemCompra ic = new ItemCompra(codigo);
        ItemCompra itemCompraParaAlterar = ArmazenamentoItemCompra.buscar(ic);
        
        if (itemCompraParaAlterar == null) {
            System.err.println("Item de Compra inexistente/inválido.");
            return;
        }
        
        System.out.println("\n - PRODUTO: " + itemCompraParaAlterar.getProduto().getCodigo() + " - " + itemCompraParaAlterar.getProduto().getNome());
        System.out.print("---> Deseja alterar o Produto? (s = sim / n = não): ");
        input.nextLine(); // Consumindo quebra de linha
        char opcaoProduto = input.nextLine().charAt(0);
        Produto produto = itemCompraParaAlterar.getProduto();
        if (opcaoProduto == 's') {
            Produto pp = null;
            do {
                System.out.print("- Novo Produto (Código): ");
                long codigoProduto = input.nextLong();
                input.nextLine(); // Consumindo quebra de linha
                pp = ArmazenamentoProduto.buscar(new Produto(codigoProduto));
                if (pp == null) {
                    System.err.println("Produto inválido/inexistente.");
                } else {
                    produto = pp;
                }
            } while (pp == null);            
        }
        
        System.out.println("\n - PREÇO COMPRA: " + itemCompraParaAlterar.getPrecoCompra());
        System.out.print("---> Deseja alterar o Preço de Compra? (s = sim / n = não): ");
        char opcaoPrecoCompra = input.nextLine().charAt(0);
        double precoCompra = itemCompraParaAlterar.getPrecoCompra();
        if (opcaoPrecoCompra == 's') {
            System.out.print("- Novo Preço Compra: ");
            precoCompra = input.nextDouble();
        }
        
        System.out.println("\n - QUANTIDADE: " + itemCompraParaAlterar.getQuantidade());
        System.out.print("---> Deseja alterar a Quantidade? (s = sim / n = não): ");
        input.nextLine(); // Consumindo quebra de linha
        char opcaoQuantidade = input.nextLine().charAt(0);
        int quantidade = itemCompraParaAlterar.getQuantidade();
        if (opcaoQuantidade == 's') {
            System.out.print("- Nova Quantidade: ");
            quantidade = input.nextInt();
            input.nextLine(); // Consumindo quebra de linha
        }

        System.out.println("\nDeseja realmente modificar os dados informados?");
        System.out.println("- Código............: " + itemCompraParaAlterar.getCodigo());
        System.out.println("- Produto...........: " + produto.getCodigo() + " - " + produto.getNome());
        System.out.println("- Preço de Compra...: " + precoCompra);
        System.out.println("- Quantidade........: " + quantidade);
        System.out.print("---> (s = sim / n = não): ");

        char confirmacaoFinal = input.nextLine().charAt(0);
        if (confirmacaoFinal == 's') {
            ItemCompra itemCompraAlterado = new ItemCompra(codigo, compraReferencia, produto, precoCompra, quantidade);
            ArmazenamentoItemCompra.alterar(itemCompraAlterado);
        }
    }

    private void excluir() {
        System.out.println("\nExcluir o registro do Item de Compra de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        ItemCompra itemCompraParaDeletar = ArmazenamentoItemCompra.buscar(new ItemCompra(codigo));
        
        if (itemCompraParaDeletar == null) {
            System.err.println("Item Compra não encontrado. Código inexistente.");
            return;
        }

        System.out.println("\nDeseja realmente excluir o Item Compra informado?");
        System.out.println("- Código............: " + itemCompraParaDeletar.getCodigo());
        System.out.println("- Produto...........: " + itemCompraParaDeletar.getProduto().getCodigo() + " - " + itemCompraParaDeletar.getProduto().getNome());
        System.out.println("- Preço de Compra...: " + itemCompraParaDeletar.getPrecoCompra());
        System.out.println("- Quantidade........: " + itemCompraParaDeletar.getQuantidade());
        System.out.print("---> (s = sim / n = não): ");

        char confirmacaoFinal = input.nextLine().charAt(0);
        if (confirmacaoFinal == 's') {
            ArmazenamentoItemCompra.excluir(itemCompraParaDeletar);
        }
    }
    
}
