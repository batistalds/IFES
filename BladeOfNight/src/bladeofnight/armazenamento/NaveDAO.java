
package bladeofnight.armazenamento;

import bladeofnight.entidades.Nave;
import java.util.ArrayList;

public interface NaveDAO {
    public Nave buscar(Nave nave);
    
    public boolean inserir(Nave nave);
    public boolean alterar(Nave nave);
    public boolean excluir(Nave nave);
    
    public ArrayList<Nave> getLista();
}
