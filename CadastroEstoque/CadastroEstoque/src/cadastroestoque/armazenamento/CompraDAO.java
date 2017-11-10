
package cadastroestoque.armazenamento;

import cadastroestoque.Entidades.Compra;
import java.util.ArrayList;

public interface CompraDAO {
    public Compra buscar(Compra compra);
    
    public boolean inserir(Compra compra);
    public boolean alterar(Compra compra);
    public boolean excluir(Compra compra);
    
    public ArrayList<Compra> getLista();
}
