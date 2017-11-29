
package bladeofnight.armazenamento;

import bladeofnight.armazenamento.pg.PostgreSqlDAOFactory;
import bladeofnight.entidades.Entidade;
import java.util.ArrayList;

public abstract class DAOFactory {
    
    private static final int TIPO_POSTGRE = 1;
    private static final int TIPO_MYSQL = 2;
    private static final int TIPO_ORACLE = 3;
    private static final int TIPO_APP_ENGINE = 4;
    
    public static DAOFactory getDAOFactory(int tipo) {
        switch (tipo) {
            case TIPO_POSTGRE:
                return new PostgreSqlDAOFactory();
            case TIPO_MYSQL:
            case TIPO_ORACLE:
            case TIPO_APP_ENGINE:
            default:
                return null;
        }
    }
    
    public static DAOFactory getDefaultDAOFactory() {
        return getDAOFactory(TIPO_POSTGRE);
    }
    
    public abstract BackgroundDAO getBackgroundDAO();
    public abstract BossDAO getBossDAO();
    public abstract HudDAO getHudDAO();
    public abstract InimigoDAO getInimigoDAO();
    public abstract ItemDAO getItemDAO();
    public abstract JogadorDAO getJogadorDAO();
    public abstract NaveDAO getNaveDAO();
    public abstract ProjetilDAO getProjetilDAO();
    public abstract RankingDAO getRankingDAO();
    public abstract HudPrioridadeDAO getHudPrioridadeDAO();
    
    public ArrayList<Entidade> lista;
    
    public ArrayList getLista() {
        return lista;        
    }
    
    public void iniciarLista() {
        lista = new ArrayList<>();
    }    
}
