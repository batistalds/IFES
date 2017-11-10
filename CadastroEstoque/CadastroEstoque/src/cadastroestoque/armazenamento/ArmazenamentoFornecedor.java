package cadastroestoque.armazenamento;

import cadastroestoque.Entidades.Entidade;
import cadastroestoque.Entidades.Fornecedor;

public class ArmazenamentoFornecedor extends Armazenamento {

    private static ArmazenamentoFornecedor INSTANCE;
    
    public static ArmazenamentoFornecedor getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ArmazenamentoFornecedor();
        }
        return INSTANCE;
    }

    @Override
    public boolean alterar(Entidade e) {
        Fornecedor f = (Fornecedor) e;
        Fornecedor fornecedorAlterar = (Fornecedor) buscar(f);        

        if (fornecedorAlterar != null) {
            fornecedorAlterar.setCnpj(f.getCnpj());
            fornecedorAlterar.setEmail(f.getEmail());
            fornecedorAlterar.setEndereco(f.getEndereco());
            fornecedorAlterar.setInscricaoEstadual(f.getInscricaoEstadual());
            fornecedorAlterar.setNomeFantasia(f.getNomeFantasia());
            fornecedorAlterar.setRazaoSocial(f.getRazaoSocial());
            fornecedorAlterar.setTelefone(f.getTelefone());
            return true;
        }

        return false;
    }

}
