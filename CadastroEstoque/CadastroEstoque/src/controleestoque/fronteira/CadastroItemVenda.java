
package controleestoque.fronteira;

import cadastroestoque.Entidades.ItemVenda;
import cadastroestoque.Entidades.Produto;
import cadastroestoque.Entidades.Venda;
import cadastroestoque.armazenamento.ArmazenamentoItemVenda;
import cadastroestoque.armazenamento.ArmazenamentoProduto;
import java.util.ArrayList;

public class CadastroItemVenda extends Cadastro {
    
    private Venda vendaReferencia;

    public CadastroItemVenda(Venda v) {
        ArmazenamentoItemVenda.getInstance().iniciarLista();
        ArmazenamentoItemVenda.getInstance().getLista().addAll(v.getItensVenda());
        vendaReferencia = v;
    }
    
    @Override
    protected String obterTituloMenu() {
        return "Opções do Cadastro de Item Venda:";
    }

    @Override
    protected String obterMensagemSairDoMenu() {
        return "Voltar ao Menu Anterior";
    }

    @Override    
    protected void inserir() {
        System.out.println("\nInserir novo registro de item de venda de\n");
        
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha

        Produto produto = null;
        do {
            System.out.print("- Produto: ");
            long codigoProduto = input.nextLong();
            input.nextLine(); // Consumindo quebra de linha
            produto = (Produto) ArmazenamentoProduto.getInstance().buscar(new Produto(codigoProduto));
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
        ArmazenamentoItemVenda.getInstance().inserir(novoItemVenda);
    }

    @Override
    protected void listar() {
        System.out.println("\nListagem de itens de venda registrados\n");
        System.out.println("+--------+-----------------------------+----------------+------------+");
        System.out.println("| CÓDIGO |           PRODUTO           |     PREÇO      | QUANTIDADE |");
        System.out.println("+--------+-----------------------------+----------------+------------+");
        ArrayList<ItemVenda> lista = ArmazenamentoItemVenda.getInstance().getLista();
        for (ItemVenda iv : lista) {
            System.out.printf("| %6d | %-27s | %14.2f | %10d |\n",
                        iv.getCodigo(), iv.getProduto().getNome(), iv.getPrecoVenda(), iv.getQuantidade());
        }
        System.out.println("+--------+-----------------------------+----------------+------------+");
    }

    @Override
    protected void alterar() {
        System.out.println("\nAlterar o registro de Item de Venda de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();

        ItemVenda iv = new ItemVenda(codigo);
        ItemVenda itemVendaParaAlterar = (ItemVenda) ArmazenamentoItemVenda.getInstance().buscar(iv);
        
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
                pp = (Produto) ArmazenamentoProduto.getInstance().buscar(new Produto(codigoProduto));
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
            ArmazenamentoItemVenda.getInstance().alterar(itemVendaAlterado);
        }
    }

    @Override
    protected void excluir() {
        System.out.println("\nExcluir o registro do Item de Venda de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        ItemVenda itemVendaParaDeletar = (ItemVenda) ArmazenamentoItemVenda.getInstance().buscar(new ItemVenda(codigo));
        
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
            ArmazenamentoItemVenda.getInstance().excluir(itemVendaParaDeletar);
        }
    }
}
