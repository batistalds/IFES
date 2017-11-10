
package cadastroestoque.armazenamento;

import cadastroestoque.Entidades.Venda;
import java.util.ArrayList;

public interface VendaDAO {
    public Venda buscar(Venda venda);
    
    public boolean inserir(Venda venda);
    public boolean alterar(Venda venda);
    public boolean excluir(Venda venda);
    
    public ArrayList<Venda> getLista();
}
