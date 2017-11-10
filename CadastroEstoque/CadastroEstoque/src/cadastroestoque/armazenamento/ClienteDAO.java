
package cadastroestoque.armazenamento;

import cadastroestoque.Entidades.Cliente;
import java.util.ArrayList;

public interface ClienteDAO {
    public Cliente buscar(Cliente cliente);
    
    public boolean inserir(Cliente cliente);
    public boolean alterar(Cliente cliente);
    public boolean excluir(Cliente cliente);
    
    public ArrayList<Cliente> getLista();
}
