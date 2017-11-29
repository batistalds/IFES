
package bladeofnight.armazenamento;

import bladeofnight.entidades.Ranking;
import java.util.ArrayList;

public interface RankingDAO {
    public Ranking buscar(Ranking ranking);
    
    public boolean inserir(Ranking ranking);
    public boolean alterar(Ranking ranking);
    public boolean excluir(Ranking ranking);
    
    public ArrayList<Ranking> getLista();
}
