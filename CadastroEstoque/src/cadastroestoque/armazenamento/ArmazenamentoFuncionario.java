
package cadastroestoque.armazenamento;

import cadastroestoque.Entidades.Funcionario;
import java.util.ArrayList;

public class ArmazenamentoFuncionario {
    private static ArrayList<Funcionario> LISTA_FUNCIONARIO;

    public static ArrayList<Funcionario> getLista() {
        return LISTA_FUNCIONARIO;
    }
    
    public static void iniciarLista() {
        LISTA_FUNCIONARIO = new ArrayList<>();
    }
    
    public static void inserir(Funcionario f) {
        LISTA_FUNCIONARIO.add(f);
    }
    
    public static boolean alterar(Funcionario f) {
        Funcionario novoFuncionario = buscar(f);
        
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
    
    public static boolean excluir(Funcionario f) {
        Funcionario funcionarioParaExcluir = buscar(f);
        
        if (funcionarioParaExcluir != null) {
            LISTA_FUNCIONARIO.remove(funcionarioParaExcluir);
            return true;
        }
        
        return false;
    }
    
    public static Funcionario buscar(Funcionario f) {
        Funcionario funcionarioProcurado = null;
        for (Funcionario forn : LISTA_FUNCIONARIO) {
            if (forn.getCodigo() == f.getCodigo()) {
                funcionarioProcurado = forn;
                break;
            }
        }
        return funcionarioProcurado;
    }
}
