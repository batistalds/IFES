
package controleestoque.fronteira;

import cadastroestoque.Entidades.Compra;
import cadastroestoque.Entidades.ItemCompra;
import cadastroestoque.Entidades.Produto;
import cadastroestoque.armazenamento.ArmazenamentoItemCompra;
import cadastroestoque.armazenamento.ArmazenamentoProduto;
import java.util.ArrayList;

public class CadastroItemCompra extends Cadastro {
    
    private Compra compraReferencia;

    public CadastroItemCompra(Compra c) {
        ArmazenamentoItemCompra.getInstance().iniciarLista();
        ArmazenamentoItemCompra.getInstance().getLista().addAll(c.getItensCompra());
        compraReferencia = c;
    }

    @Override
    protected String obterTituloMenu() {
        return "Opções do Cadastro de Item Compra:";
    }

    @Override
    protected String obterMensagemSairDoMenu() {
        return "Voltar ao Menu Anterior";
    }

    @Override
    protected void inserir() {
        System.out.println("\nInserir novo registro de item de compra\n");
        
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha

        Produto produto = null;
        do {
            System.out.print("- Produto (Código): ");
            long codigoProduto = input.nextLong();
            input.nextLine(); // Consumindo quebra de linha
            produto = (Produto) ArmazenamentoProduto.getInstance().buscar(new Produto(codigoProduto));
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
        ArmazenamentoItemCompra.getInstance().inserir(novoItemCompra);
    }

    @Override
    protected void listar() {
        System.out.println("\nListagem de itens de compra registrados\n");
        System.out.println("+--------+-----------------------------+----------------+------------+");
        System.out.println("| CÓDIGO |           PRODUTO           |     PREÇO      | QUANTIDADE |");
        System.out.println("+--------+-----------------------------+----------------+------------+");
        ArrayList<ItemCompra> lista = ArmazenamentoItemCompra.getInstance().getLista();
        for (ItemCompra ic : lista) {
            System.out.printf("| %6d | %-27s | %14.2f | %10d |\n",
                        ic.getCodigo(), ic.getProduto().getNome(), ic.getPrecoCompra(), ic.getQuantidade());
        }
        System.out.println("+--------+-----------------------------+----------------+------------+");
    }

    @Override
    protected void alterar() {
        System.out.println("\nAlterar o registro de Item de Compra de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();

        ItemCompra ic = new ItemCompra(codigo);
        ItemCompra itemCompraParaAlterar = (ItemCompra) ArmazenamentoItemCompra.getInstance().buscar(ic);
        
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
                pp = (Produto) ArmazenamentoProduto.getInstance().buscar(new Produto(codigoProduto));
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
            ArmazenamentoItemCompra.getInstance().alterar(itemCompraAlterado);
        }
    }

    @Override
    protected void excluir() {
        System.out.println("\nExcluir o registro do Item de Compra de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        ItemCompra itemCompraParaDeletar = (ItemCompra) ArmazenamentoItemCompra.getInstance().buscar(new ItemCompra(codigo));
        
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
            ArmazenamentoItemCompra.getInstance().excluir(itemCompraParaDeletar);
        }
    }
    
}
