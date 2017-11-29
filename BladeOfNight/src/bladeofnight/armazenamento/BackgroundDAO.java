
package bladeofnight.armazenamento;

import bladeofnight.entidades.Background;
import java.util.ArrayList;

public interface BackgroundDAO {
    public Background buscar(Background background);
    
    public boolean inserir(Background background);
    public boolean alterar(Background background);
    public boolean excluir(Background background);
    
    public ArrayList<Background> getLista();
}
