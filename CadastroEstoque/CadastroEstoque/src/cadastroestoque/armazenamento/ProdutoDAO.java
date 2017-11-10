
package cadastroestoque.armazenamento;

import cadastroestoque.Entidades.Produto;
import java.util.ArrayList;

public interface ProdutoDAO {
    
    public Produto buscar(Produto produto);
    
    public boolean inserir(Produto produto);
    public boolean alterar(Produto produto);
    public boolean excluir(Produto produto);
    
    public ArrayList<Produto> getLista();
}
