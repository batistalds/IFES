
package cadastroestoque.armazenamento;

import cadastroestoque.Entidades.ItemCompra;
import java.util.ArrayList;

public interface ItemCompraDAO {
    public ItemCompra buscar(ItemCompra ic);
    
    public boolean inserir(ItemCompra ic);
    public boolean alterar(ItemCompra ic);
    public boolean excluir(ItemCompra ic);
    
    public ArrayList<ItemCompra> getLista();
}
