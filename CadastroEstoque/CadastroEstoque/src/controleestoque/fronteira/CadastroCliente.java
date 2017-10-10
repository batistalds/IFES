package controleestoque.fronteira;

import java.util.Scanner;
import cadastroestoque.Entidades.Cliente;
import cadastroestoque.Entidades.ClientePessoaFisica;
import cadastroestoque.Entidades.ClientePessoaJuridica;
import cadastroestoque.armazenamento.ArmazenamentoCliente;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class CadastroCliente {

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
            System.out.println("\n\nOpções do Cadastro de Clientes:");
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
                if (opcao != OPCAO_VOLTAR_MENU_PRINCIPAL) {
                    System.err.println("Opção inválida/inexistente.");
                }
        }
    }

    private void inserir() {
        System.out.println("\nInserir novo registro de cliente\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha

        // Campos para Pessoa Física
        String nome = "";
        Date dataNascimento = Calendar.getInstance().getTime(); // Hoje (data e hora)
        char sexo = '.';
        long cpf = 0;

        // Campos para Pessoa Jurídica
        String nomeFantasia = "", razaoSocial = "";
        long cnpj = 0, inscricaoEstadual = 0;

        // Campos Extras:
        char tipoPessoa;
        boolean tipoPessoaValido;

        do {
            tipoPessoaValido = true;
            System.out.print("- Pessoa Física (F) ou Jurídica (J)? ");
            tipoPessoa = input.nextLine().toUpperCase().charAt(0);

            switch (tipoPessoa) {
                case 'F':
                    System.out.print("- Nome: ");
                    nome = input.nextLine();

                    boolean dataValida;
                    do {
                        System.out.print("- Data de Nascimento (dd/mm/aaaa): ");
                        String dataTemp = input.nextLine();
                        try {
                            dataNascimento = DateFormat.getDateInstance().parse(dataTemp);
                            dataValida = true;
                        } catch (ParseException e) {
                            System.err.println("Data digitada inválida. A data deve seguir o seguinte formato de informações válidas: Dia/Mês/Ano (dd/mm/aaaa)");
                            dataValida = false;
                        }
                    } while (!dataValida);

                    System.out.print("- Sexo (F = Feminino; M = Masculino): ");
                    sexo = input.nextLine().toUpperCase().charAt(0);

                    System.out.print("- CPF (Somente Números!): ");
                    cpf = input.nextLong();
                    input.nextLine(); // Consumindo quebra de linha

                    break;
                case 'J':
                    System.out.print("- Nome Fantasia: ");
                    nomeFantasia = input.nextLine();

                    System.out.print("- Razão Social: ");
                    razaoSocial = input.nextLine();

                    System.out.print("- CNPJ (Somente Números!): ");
                    cnpj = input.nextLong();
                    input.nextLine(); // Consumindo quebra de linha

                    System.out.print("- Inscrição Estadual (Somente Números!): ");
                    inscricaoEstadual = input.nextLong();
                    input.nextLine(); // Consumindo quebra de linha

                    break;
                default:
                    System.err.println("Tipo de Pessoa inválido. Deve ser F ou J.");
                    tipoPessoaValido = false;
            }
        } while (!tipoPessoaValido);

        System.out.print("- Endereço: ");
        String endereco = input.nextLine();

        System.out.print("- Telefone: ");
        String telefone = input.nextLine();

        System.out.print("- E-mail: ");
        String email = input.nextLine();

        Cliente novoCliente = null;
        switch (tipoPessoa) {
            case 'F':
                novoCliente = new ClientePessoaFisica(codigo, endereco, telefone, email, cpf, sexo, nome, dataNascimento);
                break;
            case 'J':
                novoCliente = new ClientePessoaJuridica(cnpj, inscricaoEstadual, nomeFantasia, razaoSocial, codigo, endereco, telefone, email);
                break;
        }

        if (novoCliente != null) {
            ArmazenamentoCliente.inserir(novoCliente);
        }
    }

    private static String formatarCnpj(long cnpj) {
        String cnpjFormatado = "";
        // XX.XXX.XXX/YYYY-ZZ
        NumberFormat nf = new DecimalFormat("00000000000000");
        StringBuilder sb = new StringBuilder(nf.format(cnpj));
        sb.insert(2, '.');
        sb.insert(6, '.');
        sb.insert(10, '/');
        sb.insert(15, '-');
        cnpjFormatado = sb.toString();

        return cnpjFormatado;
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

    private void listar() {
        System.out.println("\nListagem de clientes registrados\n");
        System.out.println("+--------+--------------------------------+----------------+----------------------+-----------------+");
        System.out.println("| CÓDIGO |       NOME/NOME FANTASIA       | TIPO DE PESSOA |      CPF/CNPJ        |    TELEFONE     |");
        System.out.println("+--------+--------------------------------+----------------+----------------------+-----------------+");
        for (Cliente c : ArmazenamentoCliente.getLista()) {
            if (c instanceof ClientePessoaFisica) {
                ClientePessoaFisica cPF = (ClientePessoaFisica) c;
                System.out.printf("| %6d | %30s | %14s | %20s | %15s |\n",
                        cPF.getCodigo(), cPF.getNome(), "Física", formatarCpf(cPF.getCpf()), cPF.getTelefone());
            } else {
                ClientePessoaJuridica cPJ = (ClientePessoaJuridica) c;
                System.out.printf("| %6d | %30s | %14s | %20s | %15s |\n",
                        cPJ.getCodigo(), cPJ.getNomeFantasia(), "Jurídica", formatarCnpj(cPJ.getCnpj()), cPJ.getTelefone());
            }
        }
        System.out.println("+--------+--------------------------------+----------------+----------------------+-----------------+");
    }

    private void alterar() {
        System.out.println("\nAlterar o registro do cliente de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();

        Cliente c = new Cliente(codigo, "", "", "");
        Cliente clienteParaAlterar = ArmazenamentoCliente.buscar(c);

        // Cliente Pessoa Física
        char opcaoNome, opcaoDataNasc, opcaoSexo, opcaoCpf;
        String nome = "";
        long cpf = 0;
        char sexo = ' ';
        Date dataNascimento = Calendar.getInstance().getTime();

        // Cliente Pessoa Jurídica
        char opcaoNomeFantasia, opcaoRazaoSocial, opcaoCnpj, opcaoInscricaoEstadual;
        long cnpj = 0, inscricaoEstadual = 0;
        String nomeFantasia = "", razaoSocial = "";

        // Cliente
        char opcaoEndereco, opcaoTelefone, opcaoEmail;
        String endereco = "", telefone = "", email = "";

        if (clienteParaAlterar instanceof ClientePessoaFisica) {
            ClientePessoaFisica clientePf = (ClientePessoaFisica) clienteParaAlterar;

            System.out.println("\n - NOME: " + clientePf.getNome());
            System.out.print("---> Deseja alterar o nome? (s = sim / n = não): ");
            input.nextLine(); // Consumindo quebra de linha
            opcaoNome = input.nextLine().charAt(0);
            nome = clientePf.getNome();
            if (opcaoNome == 's') {
                System.out.print("- Novo Nome: ");
                nome = input.nextLine();
            }

            DateFormat df = DateFormat.getDateInstance();
            String dataFormatada = df.format(clientePf.getDataNascimento());
            System.out.println("\n - DATA DE NASCIMENTO: " + dataFormatada);
            System.out.print("---> Deseja alterar a data de nascimento? (s = sim / n = não): ");
            opcaoDataNasc = input.nextLine().charAt(0);
            dataNascimento = clientePf.getDataNascimento();
            if (opcaoDataNasc == 's') {
                System.out.print("- Nova Data de Nascimento: ");

                boolean dataValida;
                do {
                    System.out.print("- Nova Data de Nascimento (dd/mm/aaaa): ");
                    String dataTemp = input.nextLine();
                    try {
                        dataNascimento = DateFormat.getDateInstance().parse(dataTemp);
                        dataValida = true;
                    } catch (ParseException e) {
                        System.err.println("Data digitada inválida. A data deve seguir o seguinte formato de informações válidas: Dia/Mês/Ano (dd/mm/aaaa)");
                        dataValida = false;
                    }
                } while (!dataValida);
            }

            System.out.println("\n - CPF: " + clientePf.getCpf());
            System.out.print("---> Deseja alterar o CPF? (s = sim / n = não): ");
            opcaoCpf = input.nextLine().charAt(0);
            cpf = clientePf.getCpf();
            if (opcaoCpf == 's') {
                System.out.print("- Novo CPF: ");
                cpf = input.nextLong();
                input.nextLine(); // Consumindo quebra de linha
            }

            System.out.println("\n - SEXO: " + clientePf.getSexo());
            System.out.print("---> Deseja alterar o sexo? (s = sim / n = não): ");
            opcaoSexo = input.nextLine().charAt(0);
            sexo = clientePf.getSexo();
            if (opcaoSexo == 's') {
                System.out.print("- Novo Sexo: ");
                sexo = input.nextLine().charAt(0);
            }

        } else if (clienteParaAlterar instanceof ClientePessoaJuridica) {
            ClientePessoaJuridica clientePj = (ClientePessoaJuridica) clienteParaAlterar;

            System.out.println("\n - NOME FANTASIA: " + clientePj.getNomeFantasia());
            System.out.print("---> Deseja alterar o nome fantasia? (s = sim / n = não): ");
            input.nextLine(); // Consumindo quebra de linha
            opcaoNomeFantasia = input.nextLine().charAt(0);
            nomeFantasia = clientePj.getNomeFantasia();
            if (opcaoNomeFantasia == 's') {
                System.out.print("- Novo Nome Fantasia: ");
                nomeFantasia = input.nextLine();
            }

            System.out.println("\n - RAZÃO SOCIAL: " + clientePj.getRazaoSocial());
            System.out.print("---> Deseja alterar a razão social? (s = sim / n = não): ");
            opcaoRazaoSocial = input.nextLine().charAt(0);
            razaoSocial = clientePj.getRazaoSocial();
            if (opcaoRazaoSocial == 's') {
                System.out.print("- Nova Razão Social: ");
                razaoSocial = input.nextLine();
            }

            System.out.println("\n - CNPJ: " + clientePj.getCnpj());
            System.out.print("---> Deseja alterar o CNPJ? (s = sim / n = não): ");
            opcaoCnpj = input.nextLine().charAt(0);
            cnpj = clientePj.getCnpj();
            if (opcaoCnpj == 's') {
                System.out.print("- Novo CNPJ: ");
                cnpj = input.nextLong();
                input.nextLine(); // Consumindo quebra de linha
            }

            System.out.println("\n - Inscrição Estadual: " + clientePj.getInscricaoEstadual());
            System.out.print("---> Deseja alterar a inscrição estadual? (s = sim / n = não): ");
            opcaoInscricaoEstadual = input.nextLine().charAt(0);
            inscricaoEstadual = clientePj.getInscricaoEstadual();
            if (opcaoInscricaoEstadual == 's') {
                System.out.print("- Nova Inscrição Estadual: ");
                inscricaoEstadual = input.nextLong();
                input.nextLine(); // Consumindo quebra de linha
            }

        }

        System.out.println("\n - ENDEREÇO: " + clienteParaAlterar.getEndereco());
        System.out.print("---> Deseja alterar o endereço? (s = sim / n = não): ");
        opcaoEndereco = input.nextLine().charAt(0);
        endereco = clienteParaAlterar.getEndereco();
        if (opcaoEndereco == 's') {
            System.out.print("- Novo Endereço: ");
            endereco = input.nextLine();
        }

        System.out.println("\n - TELEFONE: " + clienteParaAlterar.getTelefone());
        System.out.print("---> Deseja alterar o telefone? (s = sim / n = não): ");
        opcaoTelefone = input.nextLine().charAt(0);
        telefone = clienteParaAlterar.getTelefone();
        if (opcaoTelefone == 's') {
            System.out.print("- Novo Telefone: ");
            telefone = input.nextLine();
        }

        System.out.println("\n - E-MAIL: " + clienteParaAlterar.getEmail());
        System.out.print("---> Deseja alterar o e-mail? (s = sim / n = não): ");
        opcaoEmail = input.nextLine().charAt(0);
        email = clienteParaAlterar.getEmail();
        if (opcaoEmail == 's') {
            System.out.print("- Novo E-mail: ");
            email = input.nextLine();
        }

        System.out.println("\nDeseja realmente modificar os dados informados? (s = sim / n = não)");
        System.out.println("- Código............: " + clienteParaAlterar.getCodigo());

        if (clienteParaAlterar instanceof ClientePessoaFisica) {
            System.out.println("- Nome..............: " + nome);
            System.out.println("- Data de Nascimento: " + DateFormat.getDateInstance().format(dataNascimento));
            System.out.println("- CPF...............: " + cpf);
            System.out.println("- Sexo..............: " + sexo);
        } else if (clienteParaAlterar instanceof ClientePessoaJuridica) {
            System.out.println("- Nome Fantasia.....: " + nomeFantasia);
            System.out.println("- Razão Social......: " + razaoSocial);
            System.out.println("- CNPJ..............: " + cnpj);
            System.out.println("- Inscrição Estadual: " + inscricaoEstadual);
        }

        System.out.println("- Endereço..........: " + endereco);
        System.out.println("- Telefone..........: " + telefone);
        System.out.println("- E-mail............: " + email);

        char confirmacaoFinal = input.nextLine().charAt(0);
        if (confirmacaoFinal == 's') {
            Cliente clienteAlterado = null;
            if (clienteParaAlterar instanceof ClientePessoaFisica) {
                clienteAlterado = new ClientePessoaFisica(codigo, endereco, telefone, email, cpf, sexo, nome, dataNascimento);
            } else {
                clienteAlterado = new ClientePessoaJuridica(cnpj, inscricaoEstadual, nomeFantasia, razaoSocial, codigo, endereco, telefone, email);
            }
            ArmazenamentoCliente.alterar(clienteAlterado);
        }
    }

    private void excluir() {
        System.out.println("\nExcluir o registro do cliente de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        Cliente clienteParaDeletar = ArmazenamentoCliente.buscar(new Cliente(codigo, "", "", ""));

        if (clienteParaDeletar == null) {
            System.err.println("Cliente não encontrado. Código inexistente.");
            return;
        }

        System.out.println("\nDeseja realmente excluir o cliente informado? (s = sim / n = não)");
        System.out.println("- Código............: " + clienteParaDeletar.getCodigo());

        if (clienteParaDeletar instanceof ClientePessoaFisica) {
            ClientePessoaFisica cliPf = (ClientePessoaFisica) clienteParaDeletar;
            System.out.println("- Nome..............: " + cliPf.getNome());
            System.out.println("- Data de Nascimento: " + DateFormat.getDateInstance().format(cliPf.getDataNascimento()));
            System.out.println("- CPF...............: " + cliPf.getCpf());
            System.out.println("- Sexo..............: " + cliPf.getSexo());
        } else if (clienteParaDeletar instanceof ClientePessoaJuridica) {
            ClientePessoaJuridica cliPj = (ClientePessoaJuridica) clienteParaDeletar;
            System.out.println("- Nome Fantasia.....: " + cliPj.getNomeFantasia());
            System.out.println("- Razão Social......: " + cliPj.getRazaoSocial());
            System.out.println("- CNPJ..............: " + cliPj.getCnpj());
            System.out.println("- Inscrição Estadual: " + cliPj.getInscricaoEstadual());
        }

        System.out.println("- Endereço..........: " + clienteParaDeletar.getEndereco());
        System.out.println("- Telefone..........: " + clienteParaDeletar.getTelefone());
        System.out.println("- E-mail............: " + clienteParaDeletar.getEmail());
        System.out.print("---> (s = sim / n = não): ");

        char confirmacaoFinal = input.nextLine().charAt(0);
        if (confirmacaoFinal == 's') {
            ArmazenamentoCliente.excluir(clienteParaDeletar);
        }
    }
}
