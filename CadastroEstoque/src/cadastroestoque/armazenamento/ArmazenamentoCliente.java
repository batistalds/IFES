package cadastroestoque.armazenamento;

import cadastroestoque.Entidades.Cliente;
import cadastroestoque.Entidades.ClientePessoaFisica;
import cadastroestoque.Entidades.ClientePessoaJuridica;
import java.util.ArrayList;

public class ArmazenamentoCliente {

    private static ArrayList<Cliente> LISTA_CLIENTE;

    public static ArrayList<Cliente> getLista() {
        return LISTA_CLIENTE;
    }

    public static void iniciarLista() {
        LISTA_CLIENTE = new ArrayList<>();
    }

    public static void inserir(Cliente c) {
        LISTA_CLIENTE.add(c);
    }

    public static boolean alterar(Cliente c) {
        Cliente novoCliente = buscar(c);

        if (novoCliente != null) {
            novoCliente.setEmail(c.getEmail());
            novoCliente.setEndereco(c.getEndereco());
            novoCliente.setTelefone(c.getTelefone());

            if (novoCliente instanceof ClientePessoaFisica) {
                ClientePessoaFisica pessoaFisicaAlterar = (ClientePessoaFisica) novoCliente;
                ClientePessoaFisica pessoaFisicaParametro = (ClientePessoaFisica) c;

                pessoaFisicaAlterar.setNome(pessoaFisicaParametro.getNome());
                pessoaFisicaAlterar.setDataNascimento(pessoaFisicaParametro.getDataNascimento());
                pessoaFisicaAlterar.setSexo(pessoaFisicaParametro.getSexo());
                pessoaFisicaAlterar.setCpf(pessoaFisicaParametro.getCpf());
            } else {
                ClientePessoaJuridica pessoaJuridicaAlterar = (ClientePessoaJuridica) novoCliente;
                ClientePessoaJuridica pessoaJuridicaParametro = (ClientePessoaJuridica) c;

                pessoaJuridicaAlterar.setNomeFantasia(pessoaJuridicaParametro.getNomeFantasia());
                pessoaJuridicaAlterar.setRazaoSocial(pessoaJuridicaParametro.getRazaoSocial());
                pessoaJuridicaAlterar.setCnpj(pessoaJuridicaParametro.getCnpj());
                pessoaJuridicaAlterar.setInscricaoEstadual(pessoaJuridicaParametro.getInscricaoEstadual());
            }

            return true;
        }

        return false;
    }

    public static boolean excluir(Cliente f) {
        Cliente clienteParaExcluir = buscar(f);

        if (clienteParaExcluir != null) {
            LISTA_CLIENTE.remove(clienteParaExcluir);
            return true;
        }

        return false;
    }

    public static Cliente buscar(Cliente f) {
        Cliente clienteProcurado = null;
        for (Cliente cli : LISTA_CLIENTE) {
            if (cli.getCodigo() == f.getCodigo()) {
                clienteProcurado = cli;
                break;
            }
        }
        return clienteProcurado;
    }
}
