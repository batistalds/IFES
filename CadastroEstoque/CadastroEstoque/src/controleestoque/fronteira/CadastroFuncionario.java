package controleestoque.fronteira;

import cadastroestoque.Entidades.Comprador;
import cadastroestoque.Entidades.Funcionario;
import cadastroestoque.Entidades.Vendedor;
import cadastroestoque.armazenamento.ArmazenamentoFuncionario;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class CadastroFuncionario extends Cadastro {

    @Override
    protected String obterTituloMenu() {
        return "Opções do Cadastro de Funcionário:";
    }

    @Override
    protected String obterMensagemSairDoMenu() {
        return "Voltar ao Menu Principal";
    }

    @Override
    protected void inserir() {
        System.out.println("\nInserir novo registro de funcionário\n");

        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha

        System.out.print("- Nome: ");
        String nome = input.nextLine();

        System.out.print("- CPF: ");
        long cpf = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha

        System.out.print("- Endereço: ");
        String endereco = input.nextLine();

        System.out.print("- Telefone: ");
        String telefone = input.nextLine();

        System.out.print("- E-mail: ");
        String email = input.nextLine();

        Funcionario novoFuncionario = null;

        boolean codigoConfirmaValido;
        do {
            codigoConfirmaValido = true;

            System.out.print("\nTrata-se de um Vendedor ou Comprador? (V = Vendedor | C = Comprador): ");
            char confirmaVOuC = input.nextLine().toUpperCase().charAt(0);

            switch (confirmaVOuC) {
                case 'V':
                    novoFuncionario = new Vendedor(codigo, nome, cpf, endereco, telefone, email);
                    break;
                case 'C':
                    novoFuncionario = new Comprador(codigo, nome, cpf, endereco, telefone, email);
                    break;
                default:
                    System.err.println("\nCódigo de Confirmação do tipo de Funcionário inexistente/inválido. Digite novamente:");
                    codigoConfirmaValido = false;
                    break;
            }
        } while (!codigoConfirmaValido);

        ArmazenamentoFuncionario.getInstance().inserir(novoFuncionario);
    }

    private static String formatarCpf(long cpf) {
        String cpfFormatado = "";
        // XXX.XXX.XXX-YY
        NumberFormat nf = new DecimalFormat("00000000000");
        StringBuilder sb = new StringBuilder(nf.format(cpf));
        sb.insert(3, '.');
        sb.insert(7, '.');
        sb.insert(11, '-');
        cpfFormatado = sb.toString();

        return cpfFormatado;
    }

    @Override
    protected void listar() {
        System.out.println("\nListagem de funcionários registrados\n");
        System.out.println("+--------+--------------------------+--------------------+-------------------------+---------------+---------------------+-----------+");
        System.out.println("| CÓDIGO |          NOME            |        CPF         |         ENDEREÇO        |    TELEFONE   |        E-MAIL       |    TIPO   |");
        System.out.println("+--------+--------------------------+--------------------+-------------------------+---------------+---------------------+-----------+");
        ArrayList<Funcionario> lista = ArmazenamentoFuncionario.getInstance().getLista();
        for (Funcionario f : lista) {
            System.out.printf("| %6d | %24s | %18s | %23s | %13s | %19s | %7s |\n",
                    f.getCodigo(), f.getNome(), formatarCpf(f.getCpf()), f.getEndereco(), f.getTelefone(), f.getEmail(), (f instanceof Vendedor ? "Vendedor " : "Comprador"));
        }
        System.out.println("+--------+--------------------------+--------------------+-------------------------+---------------+---------------------+-----------+");
    }

    @Override
    protected void alterar() {
        System.out.println("\nAlterar o registro do funcionário de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();

        Funcionario f = new Funcionario(codigo);
        Funcionario funcionarioParaAlterar = (Funcionario) ArmazenamentoFuncionario.getInstance().buscar(f);

        if (funcionarioParaAlterar != null) {
            System.out.println("\n - NOME: " + funcionarioParaAlterar.getNome());
            System.out.print("---> Deseja alterar o nome? (s = sim / n = não): ");
            input.nextLine(); // Consumindo quebra de linha
            char opcaoNome = input.nextLine().charAt(0);
            String nome = funcionarioParaAlterar.getNome();
            if (opcaoNome == 's') {
                System.out.print("- Novo Nome: ");
                nome = input.nextLine();
            }

            System.out.println("\n - CPF: " + funcionarioParaAlterar.getCpf());
            System.out.print("---> Deseja alterar o CPF? (s = sim / n = não): ");
            char opcaoCpf = input.nextLine().charAt(0);
            long cpf = funcionarioParaAlterar.getCpf();
            if (opcaoCpf == 's') {
                System.out.print("- Novo CPF: ");
                cpf = input.nextLong();
            }

            System.out.println("\n - ENDEREÇO: " + funcionarioParaAlterar.getEndereco());
            System.out.print("---> Deseja alterar o endereço? (s = sim / n = não): ");
            input.nextLine(); // Consumindo quebra de linha
            char opcaoEndereco = input.nextLine().charAt(0);
            String endereco = funcionarioParaAlterar.getEndereco();
            if (opcaoEndereco == 's') {
                System.out.print("- Novo Endereço: ");
                endereco = input.nextLine();
            }

            System.out.println("\n - TELEFONE: " + funcionarioParaAlterar.getTelefone());
            System.out.print("---> Deseja alterar o telefone? (s = sim / n = não): ");
            char opcaoTelefone = input.nextLine().charAt(0);
            String telefone = funcionarioParaAlterar.getTelefone();
            if (opcaoTelefone == 's') {
                System.out.print("- Novo Telefone: ");
                telefone = input.nextLine();
            }

            System.out.println("\n - E-MAIL: " + funcionarioParaAlterar.getEmail());
            System.out.print("---> Deseja alterar o e-mail? (s = sim / n = não): ");
            char opcaoEmail = input.nextLine().charAt(0);
            String email = funcionarioParaAlterar.getEmail();
            if (opcaoEmail == 's') {
                System.out.print("- Novo E-mail: ");
                email = input.nextLine();
            }

            System.out.println("\nDeseja realmente modificar os dados informados? (s = sim / n = não)");
            System.out.println("- Código............: " + funcionarioParaAlterar.getCodigo());
            System.out.println("- Nome..............: " + nome);
            System.out.println("- CPF...............: " + cpf);
            System.out.println("- Endereço..........: " + endereco);
            System.out.println("- Telefone..........: " + telefone);
            System.out.println("- E-mail............: " + email);
            System.out.print("---> (s = sim / n = não): ");
            char confirmacaoFinal = input.nextLine().charAt(0);
            if (confirmacaoFinal == 's') {
                Funcionario funcionarioAlterado = null;

                if (funcionarioParaAlterar instanceof Vendedor) {
                    funcionarioAlterado = new Vendedor(codigo, nome, cpf, endereco, telefone, email);
                } else {
                    funcionarioAlterado = new Comprador(codigo, nome, cpf, endereco, telefone, email);
                }

                ArmazenamentoFuncionario.getInstance().alterar(funcionarioAlterado);
            }

        } else {
            System.err.println("\nFuncionário não encontrado. Código inexistente.");
            return;
        }
    }

    @Override
    protected void excluir() {
        System.out.println("\nExcluir o registro do funcionário de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        Funcionario funcionarioParaDeletar = (Funcionario) ArmazenamentoFuncionario.getInstance().buscar(new Funcionario(codigo));

        if (funcionarioParaDeletar == null) {
            System.err.println("Funcionário não encontrado. Código inexistente.");
            return;
        }

        System.out.println("\nDeseja realmente excluir o funcionário informado? (s = sim / n = não)");
        System.out.println("- Código............: " + funcionarioParaDeletar.getCodigo());
        System.out.println("- Nome..............: " + funcionarioParaDeletar.getNome());
        System.out.println("- CPF...............: " + funcionarioParaDeletar.getCpf());
        System.out.println("- Endereço..........: " + funcionarioParaDeletar.getEndereco());
        System.out.println("- Telefone..........: " + funcionarioParaDeletar.getTelefone());
        System.out.println("- E-mail............: " + funcionarioParaDeletar.getEmail());
        System.out.print("---> (s = sim / n = não): ");
        char confirmacaoFinal = input.nextLine().charAt(0);
        if (confirmacaoFinal == 's') {
            ArmazenamentoFuncionario.getInstance().excluir(funcionarioParaDeletar);
        }
    }
}
