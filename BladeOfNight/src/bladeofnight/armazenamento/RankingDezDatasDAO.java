
package bladeofnight.armazenamento;

import bladeofnight.entidades.RankingDezDatas;
import java.util.ArrayList;

public interface RankingDezDatasDAO {
    public RankingDezDatas buscar(RankingDezDatas rdd);
    
    public boolean inserir(RankingDezDatas rdd);
    public boolean alterar(RankingDezDatas rdd);
    public boolean excluir(RankingDezDatas rdd);
    
    public ArrayList<RankingDezDatas> getLista();
}
