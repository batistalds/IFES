
package controleestoque.fronteira;

import cadastroestoque.Entidades.ItemVenda;
import cadastroestoque.Entidades.Produto;
import cadastroestoque.Entidades.Venda;
import cadastroestoque.armazenamento.ArmazenamentoItemVenda;
import cadastroestoque.armazenamento.ArmazenamentoProduto;
import java.util.Scanner;

public class CadastroItemVenda {
        
    private static final short OPCAO_INSERIR = 1;
    private static final short OPCAO_LISTAR = 2;
    private static final short OPCAO_ALTERAR = 3;
    private static final short OPCAO_EXCLUIR = 4;
    private static final short OPCAO_CONCLUIR_CADASTRO = 5;

    private Scanner input;
    
    private Venda vendaReferencia;

    public CadastroItemVenda(Venda v) {
        ArmazenamentoItemVenda.iniciarLista();
        ArmazenamentoItemVenda.getLista().addAll(v.getItensVenda());
        vendaReferencia = v;
    }
    
    public void exibirMenu() {
        input = new Scanner(System.in);
        short opcao = 0;
        while (opcao != OPCAO_CONCLUIR_CADASTRO) {
            System.out.println("\n\nOpções do Cadastro de Itens de Venda:");
            System.out.println("1 - Inserir");
            System.out.println("2 - Listar");
            System.out.println("3 - Alterar");
            System.out.println("4 - Excluir");
            System.out.println("5 - Concluir o cadastro dos itens da venda");
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
        System.out.println("\nInserir novo registro de item de venda de\n");
        
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha

        Produto produto = null;
        do {
            System.out.print("- Produto: ");
            long codigoProduto = input.nextLong();
            input.nextLine(); // Consumindo quebra de linha
            produto = ArmazenamentoProduto.buscar(new Produto(codigoProduto));
            if (produto == null) {
                System.err.println("Produto inválido/inexistente.");
            }
        } while (produto == null);
        
        System.out.print("- Preço: ");
        double precoVenda = input.nextDouble();
        input.nextLine(); // Consumindo quebra de linha
        
        System.out.print("- Quantidade: ");
        int quantidade = input.nextInt();
        input.nextLine(); // Consumindo quebra de linha
        
        ItemVenda novoItemVenda = new ItemVenda(codigo, vendaReferencia, produto, precoVenda, quantidade);
        ArmazenamentoItemVenda.inserir(novoItemVenda);
    }

    protected void listar() {
        System.out.println("\nListagem de itens de venda registrados\n");
        System.out.println("+--------+-----------------------------+----------------+------------+");
        System.out.println("| CÓDIGO |           PRODUTO           |     PREÇO      | QUANTIDADE |");
        System.out.println("+--------+-----------------------------+----------------+------------+");
        for (ItemVenda iv : ArmazenamentoItemVenda.getLista()) {
            System.out.printf("| %6d | %-27s | %14.2f | %10d |\n",
                        iv.getCodigo(), iv.getProduto().getNome(), iv.getPrecoVenda(), iv.getQuantidade());
        }
        System.out.println("+--------+-----------------------------+----------------+------------+");
    }

    private void alterar() {
        System.out.println("\nAlterar o registro de Item de Venda de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();

        ItemVenda iv = new ItemVenda(codigo);
        ItemVenda itemVendaParaAlterar = ArmazenamentoItemVenda.buscar(iv);
        
        if (itemVendaParaAlterar == null) {
            System.err.println("Item de Venda inexistente/inválido.");
            return;
        }
        
        System.out.println("\n - PRODUTO: " + itemVendaParaAlterar.getProduto().getCodigo() + " - " + itemVendaParaAlterar.getProduto().getNome());
        System.out.print("---> Deseja alterar o Produto? (s = sim / n = não): ");
        input.nextLine(); // Consumindo quebra de linha
        char opcaoProduto = input.nextLine().charAt(0);
        Produto produto = itemVendaParaAlterar.getProduto();
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
        
        System.out.println("\n - PREÇO VENDA: " + itemVendaParaAlterar.getPrecoVenda());
        System.out.print("---> Deseja alterar o Preço de Venda? (s = sim / n = não): ");
        input.nextLine(); // Consumindo quebra de linha
        char opcaoPrecoVenda = input.nextLine().charAt(0);
        double precoVenda = itemVendaParaAlterar.getPrecoVenda();
        if (opcaoPrecoVenda == 's') {
            System.out.print("- Novo Preço Venda: ");
            precoVenda = input.nextDouble();
        }
        
        System.out.println("\n - QUANTIDADE: " + itemVendaParaAlterar.getQuantidade());
        System.out.print("---> Deseja alterar a Quantidade? (s = sim / n = não): ");
        input.nextLine(); // Consumindo quebra de linha
        char opcaoQuantidade = input.nextLine().charAt(0);
        int quantidade = itemVendaParaAlterar.getQuantidade();
        if (opcaoQuantidade == 's') {
            System.out.print("- Nova Quantidade: ");
            quantidade = input.nextInt();
        }

        System.out.println("\nDeseja realmente modificar os dados informados?");
        System.out.println("- Código............: " + itemVendaParaAlterar.getCodigo());
        System.out.println("- Produto...........: " + produto.getCodigo() + " - " + produto.getNome());
        System.out.println("- Preço de Venda....: " + precoVenda);
        System.out.println("- Quantidade........: " + quantidade);
        System.out.print("---> (s = sim / n = não): ");

        char confirmacaoFinal = input.nextLine().charAt(0);
        if (confirmacaoFinal == 's') {
            ItemVenda itemVendaAlterado = new ItemVenda(codigo, vendaReferencia, produto, precoVenda, quantidade);
            ArmazenamentoItemVenda.alterar(itemVendaAlterado);
        }
    }

    private void excluir() {
        System.out.println("\nExcluir o registro do Item de Venda de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        ItemVenda itemVendaParaDeletar = ArmazenamentoItemVenda.buscar(new ItemVenda(codigo));
        
        if (itemVendaParaDeletar == null) {
            System.err.println("Item Venda não encontrado. Código inexistente.");
            return;
        }

        System.out.println("\nDeseja realmente excluir o Item Venda informado?");
        System.out.println("- Código............: " + itemVendaParaDeletar.getCodigo());
        System.out.println("- Produto...........: " + itemVendaParaDeletar.getProduto().getCodigo() + " - " + itemVendaParaDeletar.getProduto().getNome());
        System.out.println("- Preço de venda....: " + itemVendaParaDeletar.getPrecoVenda());
        System.out.println("- Quantidade........: " + itemVendaParaDeletar.getQuantidade());
        System.out.print("---> (s = sim / n = não): ");

        char confirmacaoFinal = input.nextLine().charAt(0);
        if (confirmacaoFinal == 's') {
            ArmazenamentoItemVenda.excluir(itemVendaParaDeletar);
        }
    }
}
