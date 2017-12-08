
package bladeofnight.armazenamento;

import bladeofnight.entidades.RankingDezPlayers;
import java.util.ArrayList;

public interface RankingDezPlayersDAO {
    public RankingDezPlayers buscar(RankingDezPlayers rdp);
    
    public boolean inserir(RankingDezPlayers rdp);
    public boolean alterar(RankingDezPlayers rdp);
    public boolean excluir(RankingDezPlayers rdp);
    
    public ArrayList<RankingDezPlayers> getLista();
}
