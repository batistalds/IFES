package controleestoque.fronteira;

import cadastroestoque.Entidades.Fornecedor;
import cadastroestoque.armazenamento.ArmazenamentoFornecedor;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class CadastroFornecedor extends Cadastro {

    @Override
    protected String obterTituloMenu() {
        return "Opções do Cadastro de Fornecedor:";
    }

    @Override
    protected String obterMensagemSairDoMenu() {
        return "Voltar ao Menu Principal";
    }

    @Override
    protected void inserir() {
        System.out.println("\nInserir novo registro de fornecedor\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        System.out.print("- CNPJ: ");
        long cnpj = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        System.out.print("- Nome Fantasia: ");
        String nomeFantasia = input.nextLine();
        System.out.print("- Razão Social: ");
        String razaoSocial = input.nextLine();
        System.out.print("- Inscrição Estadual: ");
        long inscricaoEstadual = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        System.out.print("- Endereço: ");
        String endereco = input.nextLine();
        System.out.print("- Telefone: ");
        String telefone = input.nextLine();
        System.out.print("- E-mail: ");
        String email = input.nextLine();

        Fornecedor novoFornecedor = new Fornecedor(codigo, nomeFantasia, razaoSocial, endereco, cnpj, inscricaoEstadual, telefone, email);
        ArmazenamentoFornecedor.getInstance().inserir(novoFornecedor);
    }

    @Override
    protected void listar() {
        System.out.println("\nListagem de fornecedores registrados\n");
        System.out.println("+--------+----------------------------+-----------------------------+-------------------------+---------------------+---------------------------+---------------+---------------------+");
        System.out.println("| CÓDIGO |       NOME FANTASIA        |         RAZÃO SOCIAL        |         ENDEREÇO        |         CNPJ        |     INSCRIÇÃO ESTADUAL    |    TELEFONE   |        E-MAIL       |");
        System.out.println("+--------+----------------------------+-----------------------------+-------------------------+---------------------+---------------------------+---------------+---------------------+");
        ArrayList<Fornecedor> lista = ArmazenamentoFornecedor.getInstance().getLista();
        for (Fornecedor f : lista) {
            System.out.printf("| %6d | %26s | %27s | %23s | %17s | %25d | %13s | %19s |\n",
                    f.getCodigo(), f.getNomeFantasia(), f.getRazaoSocial(), f.getEndereco(), formatarCnpj(f.getCnpj()), f.getInscricaoEstadual(), f.getTelefone(), f.getEmail());
        }
        System.out.println("+--------+----------------------------+-----------------------------+-------------------------+---------------------+---------------------------+---------------+---------------------+");
    }

    private static String formatarCnpj(long cnpj) {
        String cnpjFormatado = "";

        NumberFormat nf = new DecimalFormat("00000000000000");
        StringBuilder sb = new StringBuilder(nf.format(cnpj));
        sb.insert(2, '.');
        sb.insert(6, '.');
        sb.insert(10, '/');
        sb.insert(15, '-');
        cnpjFormatado = sb.toString();

        return cnpjFormatado;
    }

    @Override
    protected void alterar() {
        System.out.println("\nAlterar o registro do fornecedor de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        
        Fornecedor fornecedorParaAlterar = (Fornecedor) ArmazenamentoFornecedor.getInstance().buscar(new Fornecedor(codigo));

        if (fornecedorParaAlterar != null) {
            System.out.println("\n - NOME FANTASIA: " + fornecedorParaAlterar.getNomeFantasia());
            System.out.print("---> Deseja alterar o nome fantasia? (s = sim / n = não): ");
            input.nextLine(); // Consumindo quebra de linha            
            char opcaoNomeFantasia = input.nextLine().charAt(0);
            String nomeFantasia = fornecedorParaAlterar.getNomeFantasia();
            if (opcaoNomeFantasia == 's') {
                System.out.print("- Novo Nome Fantasia: ");
                nomeFantasia = input.nextLine();
            }

            System.out.println("\n - RAZÃO SOCIAL: " + fornecedorParaAlterar.getRazaoSocial());
            System.out.print("---> Deseja alterar a razão social? (s = sim / n = não): ");
            char opcaoRazaoSocial = input.nextLine().charAt(0);
            String razaoSocial = fornecedorParaAlterar.getRazaoSocial();
            if (opcaoRazaoSocial == 's') {
                System.out.print("- Nova Razão Social: ");
                razaoSocial = input.nextLine();
            }

            System.out.println("\n - ENDEREÇO: " + fornecedorParaAlterar.getEndereco());
            System.out.print("---> Deseja alterar o endereço? (s = sim / n = não): ");
            char opcaoEndereco = input.nextLine().charAt(0);
            String endereco = fornecedorParaAlterar.getEndereco();
            if (opcaoEndereco == 's') {
                System.out.print("- Novo Endereço: ");
                endereco = input.nextLine();
            }

            System.out.println("\n - CNPJ: " + fornecedorParaAlterar.getCnpj());
            System.out.print("---> Deseja alterar o CNPJ? (s = sim / n = não): ");
            char opcaoCnpj = input.nextLine().charAt(0);
            long cnpj = fornecedorParaAlterar.getCnpj();
            if (opcaoCnpj == 's') {
                System.out.print("- Novo CNPJ: ");
                cnpj = input.nextLong();
                input.nextLine(); // Consumindo quebra de linha
            }

            System.out.println("\n - INSCRIÇÃO ESTADUAL: " + fornecedorParaAlterar.getInscricaoEstadual());
            System.out.print("---> Deseja alterar o CNPJ? (s = sim / n = não): ");
            char opcaoInscricaoEstadual = input.nextLine().charAt(0);
            long inscricaoEstadual = fornecedorParaAlterar.getInscricaoEstadual();
            if (opcaoInscricaoEstadual == 's') {
                System.out.print("- Nova Inscrição Estadual: ");
                inscricaoEstadual = input.nextLong();
                input.nextLine(); // Consumindo quebra de linha
            }

            System.out.println("\n - TELEFONE: " + fornecedorParaAlterar.getTelefone());
            System.out.print("---> Deseja alterar o telefone? (s = sim / n = não): ");
            char opcaoTelefone = input.nextLine().charAt(0);
            String telefone = fornecedorParaAlterar.getTelefone();
            if (opcaoTelefone == 's') {
                System.out.print("- Novo Telefone: ");
                telefone = input.nextLine();
            }

            System.out.println("\n - E-MAIL: " + fornecedorParaAlterar.getEmail());
            System.out.print("---> Deseja alterar o e-mail? (s = sim / n = não): ");
            char opcaoEmail = input.nextLine().charAt(0);
            String email = fornecedorParaAlterar.getEmail();
            if (opcaoEmail == 's') {
                System.out.print("- Novo E-mail: ");
                email = input.nextLine();
            }

            System.out.println("\nDeseja realmente modificar os dados informados? (s = sim / n = não)");
            System.out.println("- Código............: " + fornecedorParaAlterar.getCodigo());
            System.out.println("- Nome Fantasia.....: " + nomeFantasia);
            System.out.println("- Razão Social......: " + razaoSocial);
            System.out.println("- Endereço..........: " + endereco);
            System.out.println("- CNPJ..............: " + cnpj);
            System.out.println("- Inscrição Estadual: " + inscricaoEstadual);
            System.out.println("- Telefone..........: " + telefone);
            System.out.println("- E-mail............: " + email);
            System.out.print("---> (s = sim / n = não): ");
            char confirmacaoFinal = input.nextLine().charAt(0);
            if (confirmacaoFinal == 's') {
                Fornecedor fornecedorAlterado = new Fornecedor(codigo, nomeFantasia, razaoSocial, endereco, cnpj, inscricaoEstadual, telefone, email);
                ArmazenamentoFornecedor.getInstance().alterar(fornecedorAlterado);
            }

        } else {
            System.err.println("\nFornecedor não encontrado. Código inexistente.");
            return;
        }
    }

    @Override
    protected void excluir() {
        System.out.println("\nExcluir o registro do fornecedor de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        Fornecedor fornecedorParaDeletar = (Fornecedor) ArmazenamentoFornecedor.getInstance().buscar(new Fornecedor(codigo));

        if (fornecedorParaDeletar == null) {
            System.err.println("Fornecedor não encontrado. Código inexistente.");
            return;
        }

        System.out.println("\nDeseja realmente excluir o fornecedor informado? (s = sim / n = não)");
        System.out.println("- Código............: " + fornecedorParaDeletar.getCodigo());
        System.out.println("- Nome Fantasia.....: " + fornecedorParaDeletar.getNomeFantasia());
        System.out.println("- Razão Social......: " + fornecedorParaDeletar.getRazaoSocial());
        System.out.println("- Endereço..........: " + fornecedorParaDeletar.getEndereco());
        System.out.println("- CNPJ..............: " + fornecedorParaDeletar.getCnpj());
        System.out.println("- Inscrição Estadual: " + fornecedorParaDeletar.getInscricaoEstadual());
        System.out.println("- Telefone..........: " + fornecedorParaDeletar.getTelefone());
        System.out.println("- E-mail............: " + fornecedorParaDeletar.getEmail());
        System.out.print("---> (s = sim / n = não): ");
        char confirmacaoFinal = input.nextLine().charAt(0);
        if (confirmacaoFinal == 's') {
            ArmazenamentoFornecedor.getInstance().excluir(fornecedorParaDeletar);
        }
    }
}
