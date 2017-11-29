
package bladeofnight.armazenamento.pg;

import bladeofnight.armazenamento.BackgroundDAO;
import bladeofnight.armazenamento.BossDAO;
import bladeofnight.armazenamento.DAOFactory;
import bladeofnight.armazenamento.HudDAO;
import bladeofnight.armazenamento.HudPrioridadeDAO;
import bladeofnight.armazenamento.InimigoDAO;
import bladeofnight.armazenamento.ItemDAO;
import bladeofnight.armazenamento.JogadorDAO;
import bladeofnight.armazenamento.NaveDAO;
import bladeofnight.armazenamento.ProjetilDAO;
import bladeofnight.armazenamento.RankingDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class PostgreSqlDAOFactory extends DAOFactory {
    
    private static Connection con;
    
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/BladeNight";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";
    
    public static Connection getConnection() {
        if (con == null) {
            try {
                Class.forName("org.postgresql.Driver");
                con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível carregar o conector para o PostgreSQL.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        return con;
    }
    
    @Override
    public BackgroundDAO getBackgroundDAO() {
        return new PgBackgroundDAO();
    }
    
    @Override
    public BossDAO getBossDAO() {
        return new PgBossDAO();
    }
    
    @Override
    public HudDAO getHudDAO() {
        return new PgHudDAO();
    }
    
    @Override
    public InimigoDAO getInimigoDAO() {
        return new PgInimigoDAO();
    }
    
    @Override
    public ItemDAO getItemDAO() {
        return new PgItemDAO();
    }
    
    @Override
    public JogadorDAO getJogadorDAO() {
        return new PgJogadorDAO();
    }
    
    @Override
    public NaveDAO getNaveDAO() {
        return new PgNaveDAO();
    }
    
    @Override
    public ProjetilDAO getProjetilDAO() {
        return new PgProjetilDAO();
    }
    
    @Override
    public RankingDAO getRankingDAO() {
        return new PgRankingDAO();
    }

    @Override
    public HudPrioridadeDAO getHudPrioridadeDAO() {
        return new PgHudPrioridadeDAO();
    }
    
}
