package cadastroestoque.armazenamento;

import cadastroestoque.Entidades.Cliente;
import cadastroestoque.Entidades.ClientePessoaFisica;
import cadastroestoque.Entidades.ClientePessoaJuridica;
import cadastroestoque.Entidades.Entidade;

public class ArmazenamentoCliente extends Armazenamento {

    private static ArmazenamentoCliente INSTANCE;
    
    public static ArmazenamentoCliente getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ArmazenamentoCliente();
        }
        return INSTANCE;
    }
    
    @Override
    public boolean alterar(Entidade e) {
        Cliente c = (Cliente) e;
        Cliente novoCliente = (Cliente) buscar(c);

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
}
