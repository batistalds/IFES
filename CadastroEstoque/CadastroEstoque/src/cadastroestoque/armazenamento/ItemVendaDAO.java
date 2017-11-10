
package cadastroestoque.armazenamento;

import cadastroestoque.Entidades.ItemVenda;
import java.util.ArrayList;

public interface ItemVendaDAO {
    public ItemVenda buscar(ItemVenda iv);
    
    public boolean inserir(ItemVenda iv);
    public boolean alterar(ItemVenda iv);
    public boolean excluir(ItemVenda iv);
    
    public ArrayList<ItemVenda> getLista();
}
