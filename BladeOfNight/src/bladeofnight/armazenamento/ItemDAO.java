
package bladeofnight.armazenamento;

import bladeofnight.entidades.Item;
import java.util.ArrayList;

public interface ItemDAO {
    public Item buscar(Item item);
    
    public boolean inserir(Item item);
    public boolean alterar(Item item);
    public boolean excluir(Item item);
    
    public ArrayList<Item> getLista();
}
