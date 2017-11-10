package cadastroestoque.armazenamento;

import cadastroestoque.Entidades.Entidade;
import cadastroestoque.Entidades.Funcionario;

public class ArmazenamentoFuncionario extends Armazenamento {

    private static ArmazenamentoFuncionario INSTANCE;
    
    public static ArmazenamentoFuncionario getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ArmazenamentoFuncionario();
        }
        return INSTANCE;
    }

    @Override
    public boolean alterar(Entidade e) {
        Funcionario f = (Funcionario) e;
        Funcionario novoFuncionario = (Funcionario) buscar(f);

        if (novoFuncionario != null) {
            novoFuncionario.setNome(f.getNome());
            novoFuncionario.setCpf(f.getCpf());
            novoFuncionario.setEndereco(f.getEndereco());
            novoFuncionario.setTelefone(f.getTelefone());
            novoFuncionario.setEmail(f.getEmail());

            return true;
        }

        return false;
    }
}
