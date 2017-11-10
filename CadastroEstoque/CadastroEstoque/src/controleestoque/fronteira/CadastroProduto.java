package controleestoque.fronteira;

import cadastroestoque.Entidades.Produto;
import cadastroestoque.armazenamento.DAOFactory;
import cadastroestoque.armazenamento.ProdutoDAO;

public class CadastroProduto extends Cadastro {
    
    private ProdutoDAO produtoDAO;
    
    public CadastroProduto() {
        produtoDAO = DAOFactory.getDefaultDAOFactory().getProdutoDAO();
    }

    @Override
    protected String obterTituloMenu() {
        return "Opções do Cadastro de Produto:";
    }

    @Override
    protected String obterMensagemSairDoMenu() {
        return "Voltar ao Menu Principal";
    }

    @Override
    protected void inserir() {
        System.out.println("\nInserir novo registro de produto\n");

        System.out.print("- Nome: ");
        String nome = input.nextLine();
        System.out.print("- Preço: ");
        double preco = input.nextDouble();

        Produto novoProduto = new Produto(nome, preco);
        produtoDAO.inserir(novoProduto);
    }

    @Override
    protected void listar() {
        System.out.println("\nListagem de produtos registrados\n");
        System.out.println("+--------+----------------------------+----------+");
        System.out.println("| CÓDIGO |           NOME             | PREÇO    |");
        System.out.println("+--------+----------------------------+----------+");
        for (Produto p : produtoDAO.getLista()) {
            System.out.printf("| %6d | %-26s | %8.2f |\n", p.getCodigo(), p.getNome(), p.getPreco());
        }
        System.out.println("+--------+----------------------------+----------+");
    }

    @Override
    protected void alterar() {
        System.out.println("\nAlterar o registro do produto de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();

        Produto p = new Produto(codigo);
        Produto produtoParaAlterar = produtoDAO.buscar(p);

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

            System.out.printf("\n - Preço: %.2f\n", produtoParaAlterar.getPreco());
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
                produtoDAO.alterar(produtoAlterado);
            }

        } else {
            System.err.println("\nProduto não encontrado. Código inexistente.");
            return;
        }
    }

    @Override
    protected void excluir() {
        System.out.println("\nExcluir o registro do produto de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        Produto produtoParaDeletar = produtoDAO.buscar(new Produto(codigo));

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
            produtoDAO.excluir(produtoParaDeletar);
        }
    }
}
