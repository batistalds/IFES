
package cadastroestoque.armazenamento;

import cadastroestoque.Entidades.Entidade;
import java.util.ArrayList;

public abstract class Armazenamento {
    
    public ArrayList<Entidade> lista;
    
    public ArrayList getLista() {
        return lista;        
    }
    
    public void iniciarLista() {
        lista = new ArrayList<>();
    }
    
    public void inserir(Entidade e) {
        lista.add(e);
    }
    
    public abstract boolean alterar(Entidade e);
    
    public boolean excluir(Entidade e) {
        Entidade entidadeExcluir = buscar(e);
        if (entidadeExcluir != null) {
            lista.remove(entidadeExcluir);
            return true;
        }
        
        return false;
    }
    
    public Entidade buscar(Entidade e) {
        for (Entidade item : lista) {
            if (item.getCodigo() == e.getCodigo()) {
                return item;
            }
        }
        
        return null;
    }
    
}
