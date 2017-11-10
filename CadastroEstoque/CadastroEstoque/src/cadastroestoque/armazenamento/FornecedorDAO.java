
package cadastroestoque.armazenamento;

import cadastroestoque.Entidades.Fornecedor;
import java.util.ArrayList;

public interface FornecedorDAO {
    public Fornecedor buscar(Fornecedor fornecedor);
    
    public boolean inserir(Fornecedor fornecedor);
    public boolean alterar(Fornecedor fornecedor);
    public boolean excluir(Fornecedor fornecedor);
    
    public ArrayList<Fornecedor> getLista();
}
