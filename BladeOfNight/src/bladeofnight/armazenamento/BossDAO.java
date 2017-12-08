
package bladeofnight.armazenamento;

import bladeofnight.entidades.Boss;
import java.util.ArrayList;

public interface BossDAO {
    public Boss buscar(Boss boss);
    public long buscarCodigoComInimigo(long codInimigo);
    
    public boolean inserir(Boss boss);
    public boolean alterar(Boss boss);
    public boolean excluir(Boss boss);
    
    public ArrayList<Boss> getLista();
}
