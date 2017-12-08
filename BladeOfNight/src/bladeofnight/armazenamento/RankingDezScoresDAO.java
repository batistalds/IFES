
package bladeofnight.armazenamento;

import bladeofnight.entidades.RankingDezScores;
import java.util.ArrayList;

public interface RankingDezScoresDAO {
    public RankingDezScores buscar(RankingDezScores rds);
    
    public boolean inserir(RankingDezScores rds);
    public boolean alterar(RankingDezScores rds);
    public boolean excluir(RankingDezScores rds);
    
    public ArrayList<RankingDezScores> getLista();
}
