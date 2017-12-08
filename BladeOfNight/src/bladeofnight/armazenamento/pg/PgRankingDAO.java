
package bladeofnight.armazenamento.pg;

import bladeofnight.armazenamento.RankingDAO;
import bladeofnight.entidades.Ranking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PgRankingDAO implements RankingDAO {
    
    private static final String SCRIPT_BUSCAR = "SELECT codigo, dezPrimeirosPlayers, dezPrimeirosScores, dezPrimeirasDatas, imagemFundo, interfaceHud" +
                                                "FROM Ranking " +
                                                "WHERE codigo = ?";
    
    private static final String SCRIPT_INSERIR = "INSERT INTO Ranking (dezPrimeirosPlayers, dezPrimeirosScores, dezPrimeirasDatas, imagemFundo, interfaceHud) VALUES(?, ?, ?, ?, ?)";
    
    private static final String SCRIPT_ALTERAR = "UPDATE Ranking SET dezPrimeirosPlayers = ?, dezPrimeirosScores = ?, dezPrimeirasDatas = ?, imagemFundo = ?, interfaceHud = ? WHERE codigo = ?";
    
    private static final String SCRIPT_EXCLUIR = "DELETE FROM Ranking WHERE codigo = ?";
    
    private static final String SCRIPT_GETLISTA = "SELECT codigo, dezPrimeirosPlayers, dezPrimeirosScores, dezPrimeirasDatas, imagemFundo, interfaceHud" +
                                                  "FROM Ranking ";
    
    @Override
    public Ranking buscar(Ranking ranking) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_BUSCAR);
            ps.setLong(1, ranking.getCodigo());            
            // Adquirindo resultado da Query após sua execução
            ResultSet rs = ps.executeQuery();
            // Pulamos para a primeira Linha resultada da Query, se houver alguma coisa, quer dizer que retornamos alguma coisa (não vazio)
            if (rs.next()) {
                long codigoRanking = rs.getLong(1);
                int dezPrimeirosPlayers = rs.getInt(2);
                int dezPrimeirosScores = rs.getInt(3);
                int dezPrimeirasDatas = rs.getInt(4);
                int imagemFundo = rs.getInt(5);
                int interfaceHud = rs.getInt(6);
                
                Ranking rankingEncontrado = new Ranking(codigoRanking, dezPrimeirosPlayers, dezPrimeirosScores, dezPrimeirasDatas, imagemFundo, interfaceHud);
                return rankingEncontrado;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter registro de ranking", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return null;
    }

    @Override
    public boolean inserir(Ranking ranking) {
        try {
           Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_INSERIR);
            ps.setInt(1, ranking.getDezPrimeirosPlayers());
            ps.setInt(2, ranking.getDezPrimeirosScores());
            ps.setInt(3, ranking.getDezPrimeirasDatas());
            ps.setInt(4, ranking.getImagemFundo());
            ps.setInt(5, ranking.getInterfaceHud());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1; // Como acrescenta-se uma linha à tabela, o resultado esperado para sucesso da execução é 1
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao inserir um registro de ranking", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    @Override
    public boolean alterar(Ranking ranking) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_ALTERAR);
            ps.setInt(1, ranking.getDezPrimeirosPlayers());
            ps.setInt(2, ranking.getDezPrimeirosScores());
            ps.setInt(3, ranking.getDezPrimeirasDatas());
            ps.setInt(4, ranking.getImagemFundo());
            ps.setInt(5, ranking.getInterfaceHud());
            ps.setLong(6, ranking.getCodigo());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao alterar um registro de ranking", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    @Override
    public boolean excluir(Ranking ranking) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_EXCLUIR);
            ps.setLong(1, ranking.getCodigo());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir um registro de ranking", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    private ArrayList<Ranking> listaRakings;
    
    @Override
    public ArrayList<Ranking> getLista() {
        if (listaRakings == null) 
            listaRakings = new ArrayList<>();
        else 
            listaRakings.clear();
        
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_GETLISTA);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long codigoRanking = rs.getLong(1);
                int dezPrimeirosPlayers = rs.getInt(2);
                int dezPrimeirosScores = rs.getInt(3);
                int dezPrimeirasDatas = rs.getInt(4);
                int imagemFundo = rs.getInt(5);
                int interfaceHud = rs.getInt(6);
                
                Ranking rank = new Ranking(codigoRanking, dezPrimeirosPlayers, dezPrimeirosScores, dezPrimeirasDatas, imagemFundo, interfaceHud);
                listaRakings.add(rank);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter registros de rankings", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return listaRakings;
    }
    
}
