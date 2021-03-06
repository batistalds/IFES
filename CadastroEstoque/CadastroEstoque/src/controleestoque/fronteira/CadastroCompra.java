package controleestoque.fronteira;

import cadastroestoque.Entidades.Compra;
import cadastroestoque.Entidades.Comprador;
import cadastroestoque.Entidades.Fornecedor;
import cadastroestoque.Entidades.Funcionario;
import cadastroestoque.Entidades.ItemCompra;
import cadastroestoque.armazenamento.ArmazenamentoCompra;
import cadastroestoque.armazenamento.ArmazenamentoFornecedor;
import cadastroestoque.armazenamento.ArmazenamentoFuncionario;
import cadastroestoque.armazenamento.ArmazenamentoItemCompra;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class CadastroCompra extends Cadastro {
    
    @Override
    protected String obterTituloMenu() {
        return "Opções do Cadastro de Compras:";
    }

    @Override
    protected String obterMensagemSairDoMenu() {
        return "Voltar ao Menu Principal";
    }
    
    @Override
    protected void inserir() {
        System.out.println("\nInserir novo registro de Compra\n");
        
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha

        Date data = null;
        boolean dataValida;
        do {
            System.out.print("- Data: ");
            String dataTemp = input.nextLine();
            try {
                data = DateFormat.getDateInstance().parse(dataTemp);
                dataValida = true;
            } catch (ParseException e) {
                System.err.println("Data digitada inválida. A data deve seguir o seguinte formato de informações válidas: Dia/Mês/Ano (dd/mm/aaaa)");
                dataValida = false;
            }
        } while (!dataValida);
        
        Comprador comprador = null;
        while (comprador == null) {
            System.out.print("- Comprador (Código): ");
            long codigoComprador = input.nextLong();
            input.nextLine(); // Consumindo quebra de linha
            Funcionario f = (Funcionario) ArmazenamentoFuncionario.getInstance().buscar(new Funcionario(codigoComprador));
            
            if (f instanceof Comprador) {
                comprador = (Comprador) f;
            }
            
            if (comprador == null) {
                System.err.println("Código inválido para o Comprador digitado.");
            }
        }
        
        Fornecedor fornecedor = null;
        while (fornecedor == null) {
            System.out.print("- Fornecedor (Código): ");
            long codigoFornecedor = input.nextLong();
            input.nextLine(); // Consumindo quebra de linha
            fornecedor = (Fornecedor) ArmazenamentoFornecedor.getInstance().buscar(new Fornecedor(codigoFornecedor));
            if (fornecedor == null) {
                System.err.println("Código inválido para o Fornecedor digitado.");
            }
        }
        
        Compra novaCompra = new Compra(codigo, data, comprador, fornecedor);
        CadastroItemCompra cadastroItemCompra = new CadastroItemCompra(novaCompra);
        cadastroItemCompra.exibirMenu();
        
        // Sempre que um novo item é inserido, o valor total é atualizado automaticamente
        for (Object i : ArmazenamentoItemCompra.getInstance().getLista()) {
            novaCompra.inserirItemCompra((ItemCompra) i);
        }
        
        System.out.println("\nDeseja realmente inserir os dados informados?");
        System.out.println("- Código.....: " + novaCompra.getCodigo());
        System.out.println("- Data.......: " + data);
        System.out.println("- Comprador..: " + comprador.getNome());
        System.out.println("- Fornecedor.: " + fornecedor.getNomeFantasia());
        System.out.println("- Valor Total: " + novaCompra.getValorTotal());
        cadastroItemCompra.listar();
        System.out.print("---> (s = sim / n = não): ");
        char opcaoConfirmacaoFinal = input.nextLine().charAt(0);
        if (opcaoConfirmacaoFinal == 's') {
            ArmazenamentoCompra.getInstance().inserir(novaCompra);
        }
    }
    
    @Override
    protected void listar() {
        System.out.println("\nListagem de compras registradas\n");
        System.out.println("+--------+-------------------------+----------------------+----------------------+");
        System.out.println("| CÓDIGO |       VALOR TOTAL       |       COMPRADOR      |      FORNECEDOR      |");
        System.out.println("+--------+-------------------------+----------------------+----------------------+");
        ArrayList<Compra> lista = ArmazenamentoCompra.getInstance().getLista();
        for (Compra c : lista) {
            System.out.printf("| %6d | %23.2f | %20s | %20s |\n",
                        c.getCodigo(), c.getValorTotal(), c.getComprador().getNome(), c.getForncedor().getNomeFantasia());
        }
        System.out.println("+--------+-------------------------+----------------------+----------------------+");
    }
    
    @Override
    protected void alterar() {
        System.out.println("\nAlterar o registro de compra de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        
        Compra compraParaAlterar = (Compra) ArmazenamentoCompra.getInstance().buscar(new Compra(codigo));
        
        if (compraParaAlterar == null) {
            System.err.println("Compra inexistente/inválida.");
            return;
        }

        DateFormat df = DateFormat.getDateInstance();
        String dataFormatada = df.format(compraParaAlterar.getData());
        System.out.println("\n - DATA: " + dataFormatada);
        System.out.print("---> Deseja alterar a data? (s = sim / n = não): ");
        input.nextLine(); // Consumindo quebra de linha
        char opcaoData = input.nextLine().charAt(0);
        Date data = compraParaAlterar.getData();
        if (opcaoData == 's') {
            boolean dataValida;
            do {
                System.out.print("- Nova Data: ");
                String dataTemp = input.nextLine();
                input.nextLine(); // Consumindo quebra de linha
                try {
                    data = DateFormat.getDateInstance().parse(dataTemp);
                    dataValida = true;
                } catch (ParseException e) {
                    System.err.println("Data digitada inválida. A data deve seguir o seguinte formato de informações válidas: Dia/Mês/Ano (dd/mm/aaaa)");
                    dataValida = false;
                }
            } while (!dataValida);
        }
        
        System.out.println("\n - COMPRADOR: " + compraParaAlterar.getComprador().getNome());
        System.out.print("---> Deseja alterar o Comprador? (s = sim / n = não): ");
        char opcaoComprador = input.nextLine().charAt(0);
        Comprador comprador = compraParaAlterar.getComprador();
        if (opcaoComprador == 's') {
            System.out.print("- Novo Comprador: ");
            comprador.setNome(input.nextLine());
            input.nextLine(); // Consumindo quebra de linha
        }
        
        System.out.println("\n - FORNECEDOR: " + compraParaAlterar.getForncedor().getNomeFantasia());
        System.out.print("---> Deseja alterar o Fornecedor? (s = sim / n = não): ");
        char opcaoFornecedor = input.nextLine().charAt(0);
        Fornecedor fornec = compraParaAlterar.getForncedor();
        if (opcaoFornecedor == 's') {
            System.out.print("- Novo Fornecedor: ");
            fornec.setNomeFantasia(input.nextLine());
            input.nextLine(); // Consumindo quebra de linha
        }
        
        Compra novaCompra = new Compra(codigo, data, comprador, fornec);
        CadastroItemCompra cadastroItemCompra = new CadastroItemCompra(novaCompra);
        cadastroItemCompra.exibirMenu();
        
        // Sempre que um novo item é inserido, o valor total é atualizado automaticamente
        for (Object i : ArmazenamentoItemCompra.getInstance().getLista()) {
            novaCompra.inserirItemCompra((ItemCompra) i);
        }
        
        System.out.println("\nDeseja realmente inserir os dados informados?");
        System.out.println("- Código.....: " + novaCompra.getCodigo());
        System.out.println("- Data.......: " + data);
        System.out.println("- Comprador..: " + comprador.getNome());
        System.out.println("- Fornecedor.: " + fornec.getNomeFantasia());
        System.out.println("- Valor Total: " + novaCompra.getValorTotal());
        cadastroItemCompra.listar();

        char confirmacaoFinal = input.nextLine().charAt(0);
        if (confirmacaoFinal == 's') {
            ArmazenamentoCompra.getInstance().alterar(novaCompra);
        }
    }
    
    @Override
    protected void excluir() {
        System.out.println("\nExcluir o registro de Compra de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        Compra compraParaDeletar = (Compra) ArmazenamentoCompra.getInstance().buscar(new Compra(codigo));
        
        if (compraParaDeletar == null) {
            System.err.println("Compra não encontrada. Código inexistente.");
            return;
        }

        System.out.println("\nDeseja realmente excluir a Compra informada?");
        System.out.println("- Código............: " + compraParaDeletar.getCodigo());
        System.out.println("- Data..............: " + DateFormat.getDateInstance().format(compraParaDeletar.getData()));
        System.out.println("- Valor Total.......: " + compraParaDeletar.getValorTotal());
        System.out.println("- Comprador.........: " + compraParaDeletar.getComprador().getNome());
        System.out.println("- Fornecedor........: " + compraParaDeletar.getForncedor().getNomeFantasia());
        System.out.print("---> (s = sim / n = não): ");

        char confirmacaoFinal = input.nextLine().charAt(0);
        if (confirmacaoFinal == 's') {
            ArmazenamentoCompra.getInstance().excluir(compraParaDeletar);
        }
    }
    
}
