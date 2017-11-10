
package cadastroestoque.armazenamento;

import cadastroestoque.Entidades.Funcionario;
import java.util.ArrayList;

public interface FuncionarioDAO {
    public Funcionario buscar(Funcionario funcionario);
    
    public boolean inserir(Funcionario funcionario);
    public boolean alterar(Funcionario funcionario);
    public boolean excluir(Funcionario funcionario);
    
    public ArrayList<Funcionario> getLista();
}
