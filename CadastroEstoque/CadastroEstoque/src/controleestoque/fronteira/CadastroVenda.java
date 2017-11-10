
package controleestoque.fronteira;

import cadastroestoque.Entidades.Cliente;
import cadastroestoque.Entidades.ClientePessoaFisica;
import cadastroestoque.Entidades.ClientePessoaJuridica;
import cadastroestoque.Entidades.Funcionario;
import cadastroestoque.Entidades.ItemVenda;
import cadastroestoque.Entidades.Venda;
import cadastroestoque.Entidades.Vendedor;
import cadastroestoque.armazenamento.ArmazenamentoCliente;
import cadastroestoque.armazenamento.ArmazenamentoFuncionario;
import cadastroestoque.armazenamento.ArmazenamentoItemVenda;
import cadastroestoque.armazenamento.ArmazenamentoVenda;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class CadastroVenda extends Cadastro {
    
    @Override
    protected String obterTituloMenu() {
        return "Opções do Cadastro de Venda:";
    }

    @Override
    protected String obterMensagemSairDoMenu() {
        return "Voltar ao Menu Principal";
    }

    @Override
    protected void inserir() {
        System.out.println("\nInserir novo registro de Venda\n");
        
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
        
        Vendedor vendedor = null;
        while (vendedor == null) {
            System.out.print("- Vendedor (Código): ");
            long codigoVendedor = input.nextLong();
            input.nextLine(); // Consumindo quebra de linha
            Funcionario f = (Funcionario) ArmazenamentoFuncionario.getInstance().buscar(new Funcionario(codigoVendedor));
            
            if (f instanceof Vendedor) {
                vendedor = (Vendedor) f;
            }
            
            if (vendedor == null) {
                System.err.println("Código inválido para o Vendedor digitado.");
            }
        }
        
        Cliente cliente = null;
        while (cliente == null) {
            System.out.print("- Cliente (Código): ");
            long codigoCliente = input.nextLong();
            input.nextLine(); // Consumindo quebra de linha
            cliente = (Cliente) ArmazenamentoCliente.getInstance().buscar(new Cliente(codigoCliente));            
            if (cliente == null) {
                System.err.println("Código inválido para o Cliente digitado.");
            }
        }
        
        Venda novaVenda = new Venda(codigo, data, vendedor, cliente);
        CadastroItemVenda cadastroItemVenda = new CadastroItemVenda(novaVenda);
        cadastroItemVenda.exibirMenu();
        
        // Sempre que um novo item é inserido, o valor total é atualizado automaticamente
        for (Object i : ArmazenamentoItemVenda.getInstance().getLista()) {
            novaVenda.inserirItemVenda((ItemVenda) i);
        }
        
        System.out.println("\nDeseja realmente inserir os dados informados?");
        System.out.println("- Código.....: " + novaVenda.getCodigo());
        System.out.println("- Data.......: " + data);
        System.out.println("- Vendedor...: " + vendedor.getNome());
        ClientePessoaFisica cPf = null;
        ClientePessoaJuridica cPj = null;
        if (cliente instanceof ClientePessoaFisica) {
            cPf = new ClientePessoaFisica(cliente.getCodigo());
        } else if (cliente instanceof ClientePessoaJuridica) {
            cPj = new ClientePessoaJuridica(cliente.getCodigo());
        }
        System.out.println("- Cliente....: " + cPf == null ? cPj.getNomeFantasia() : cPf.getNome());
        System.out.println("- Valor Total: " + novaVenda.getValorTotal());
        cadastroItemVenda.listar();
        System.out.print("---> (s = sim / n = não): ");
        char opcaoConfirmacaoFinal = input.nextLine().charAt(0);
        if (opcaoConfirmacaoFinal == 's') {
            ArmazenamentoVenda.getInstance().inserir(novaVenda);
        }
    }
    
    @Override
    protected void listar() {
        System.out.println("\nListagem de vendas registradas\n");
        System.out.println("+--------+-------------------------+---------------------+---------------------+");
        System.out.println("| CÓDIGO |       VALOR TOTAL       |       VENDEDOR      |       CLIENTE       |");
        System.out.println("+--------+-------------------------+---------------------+---------------------+");
        ArrayList<Venda> lista = ArmazenamentoVenda.getInstance().getLista();
        for (Venda v : lista) {
            System.out.printf("| %6d | %23.2f | %19s | %19s |\n",
                        v.getCodigo(), v.getValorTotal(), v.getVendedor().getNome(), v.getCliente().getEmail());
        }
        System.out.println("+--------+-------------------------+---------------------+---------------------+");
    }
    
    @Override
    protected void alterar() {
        System.out.println("\nAlterar o registro de venda de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();

        Venda v = new Venda(codigo);
        Venda vendaParaAlterar = (Venda) ArmazenamentoVenda.getInstance().buscar(v);
        
        if (vendaParaAlterar == null) {
            System.err.println("Venda inexistente/inválida.");
            return;
        }

        DateFormat df = DateFormat.getDateInstance();
        String dataFormatada = df.format(vendaParaAlterar.getData());
        System.out.println("\n - DATA: " + dataFormatada);
        System.out.print("---> Deseja alterar a data? (s = sim / n = não): ");
        input.nextLine(); // Consumindo quebra de linha
        char opcaoData = input.nextLine().charAt(0);
        Date data = vendaParaAlterar.getData();
        if (opcaoData == 's') {
            boolean dataValida;
            do {
                System.out.print("- Nova Data: ");
                String dataTemp = input.nextLine();
                try {
                    data = DateFormat.getDateInstance().parse(dataTemp);
                    dataValida = true;
                } catch (ParseException e) {
                    System.err.println("Data digitada inválida. A data deve seguir o seguinte formato de informações válidas: Dia/Mês/Ano (dd/mm/aaaa)");
                    dataValida = false;
                }
            } while (!dataValida);
        }
        
        System.out.println("\n - VENDEDOR: " + vendaParaAlterar.getVendedor().getNome());
        System.out.print("---> Deseja alterar o Vendedor? (s = sim / n = não): ");
        input.nextLine(); // Consumindo quebra de linha
        char opcaoVendedor = input.nextLine().charAt(0);
        Vendedor vendedor = vendaParaAlterar.getVendedor();
        if (opcaoVendedor == 's') {
            System.out.print("- Novo Vendedor: ");
            vendedor.setNome(input.nextLine());
        }
        
        System.out.println("\n - CLIENTE: " + vendaParaAlterar.getCliente().getEmail());
        System.out.print("---> Deseja alterar o Cliente? (s = sim / n = não): ");
        char opcaoFornecedor = input.nextLine().charAt(0);
        Cliente cliente = vendaParaAlterar.getCliente();
        if (opcaoFornecedor == 's') {
            System.out.print("- Novo Cliente: ");
            cliente.setEmail(input.nextLine());
        }
        
        Venda novaVenda = new Venda(codigo, data, vendedor, cliente);
        CadastroItemVenda cadastroItemVenda = new CadastroItemVenda(novaVenda);
        cadastroItemVenda.exibirMenu();
        
        // Sempre que um novo item é inserido, o valor total é atualizado automaticamente
        for (Object i : ArmazenamentoItemVenda.getInstance().getLista()) {
            novaVenda.inserirItemVenda((ItemVenda) i);
        }
        
        System.out.println("\nDeseja realmente inserir os dados informados?");
        System.out.println("- Código.....: " + novaVenda.getCodigo());
        System.out.println("- Data.......: " + data);
        System.out.println("- Vendedor...: " + vendedor.getNome());
        System.out.println("- Cliente....: " + cliente.getEmail());
        System.out.println("- Valor Total: " + novaVenda.getValorTotal());
        cadastroItemVenda.listar();

        char confirmacaoFinal = input.nextLine().charAt(0);
        if (confirmacaoFinal == 's') {
            ArmazenamentoVenda.getInstance().alterar(novaVenda);
        }
    }
    
    @Override
    protected void excluir() {
        System.out.println("\nExcluir o registro de Venda de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        Venda vendaParaDeletar = (Venda) ArmazenamentoVenda.getInstance().buscar(new Venda(codigo));
        
        if (vendaParaDeletar == null) {
            System.err.println("Venda não encontrada. Código inexistente.");
            return;
        }

        System.out.println("\nDeseja realmente excluir a Venda informada?");
        System.out.println("- Código............: " + vendaParaDeletar.getCodigo());
        System.out.println("- Data..............: " + DateFormat.getDateInstance().format(vendaParaDeletar.getData()));
        System.out.println("- Valor Total.......: " + vendaParaDeletar.getValorTotal());
        System.out.println("- Vendedor..........: " + vendaParaDeletar.getVendedor().getNome());
        System.out.println("- Cliente...........: " + vendaParaDeletar.getCliente().getEmail());
        System.out.print("---> (s = sim / n = não): ");

        char confirmacaoFinal = input.nextLine().charAt(0);
        if (confirmacaoFinal == 's') {
            ArmazenamentoVenda.getInstance().excluir(vendaParaDeletar);
        }
    }
}
