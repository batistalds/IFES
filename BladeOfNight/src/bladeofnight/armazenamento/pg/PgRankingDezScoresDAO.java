
package bladeofnight.armazenamento.pg;

import bladeofnight.armazenamento.RankingDezScoresDAO;
import bladeofnight.entidades.RankingDezScores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PgRankingDezScoresDAO implements RankingDezScoresDAO {
    
    private static final String SCRIPT_BUSCAR = "SELECT codigoDezScores, pontuacaoPlayer " +
                                                "FROM Dez_Scores_Ranking " +
                                                "WHERE codigoDezScores = ?";
    
    private static final String SCRIPT_INSERIR = "INSERT INTO Dez_Scores_Ranking (pontuacaoPlayer) VALUES(?)";
    
    private static final String SCRIPT_ALTERAR = "UPDATE Dez_Scores_Ranking SET pontuacaoPlayer = ? WHERE codigoDezScores = ?";
    
    private static final String SCRIPT_EXCLUIR = "DELETE FROM Dez_Scores_Ranking WHERE codigoDezScores = ?";
    
    private static final String SCRIPT_GETLISTA = "SELECT codigoDezScores, pontuacaoPlayer " +
                                                  "FROM Dez_Scores_Ranking ";
    
    @Override
    public RankingDezScores buscar(RankingDezScores rds) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_BUSCAR);
            ps.setLong(1, rds.getCodigo());
            // Adquirindo resultado da Query após sua execução
            ResultSet rs = ps.executeQuery();
            // Pulamos para a primeira Linha resultada da Query, se houver alguma coisa, quer dizer que retornamos alguma coisa (não vazio)
            if (rs.next()) {
                long codigoDezScores = rs.getLong(1);
                long pontuacaoPlayer = rs.getLong(2);
                
                RankingDezScores rdsEncontrado = new RankingDezScores(codigoDezScores, pontuacaoPlayer);
                return rdsEncontrado;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter um registro dos dez primeiros scores (pontuações) do Ranking", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return null;
    }

    @Override
    public boolean inserir(RankingDezScores rds) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_INSERIR);
            ps.setLong(1, rds.getPontuacaoPlayer());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1; // Como acrescenta-se uma linha à tabela, o resultado esperado para sucesso da execução é 1
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao inserir um registro nos dez primeiros scores (pontuações) do Ranking", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    @Override
    public boolean alterar(RankingDezScores rds) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_ALTERAR);
            ps.setLong(1, rds.getPontuacaoPlayer());
            ps.setLong(2, rds.getCodigo());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao alterar um registro dos dez primeiros scores (pontuações) do Ranking", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    @Override
    public boolean excluir(RankingDezScores rds) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_EXCLUIR);
            ps.setLong(1, rds.getCodigo());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir um registro dos dez primeiros scores (pontuações) do Ranking", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    private ArrayList<RankingDezScores> listaRankDezSco;
    
    @Override
    public ArrayList<RankingDezScores> getLista() {
        if (listaRankDezSco == null)
            listaRankDezSco = new ArrayList<>();
        else 
            listaRankDezSco.clear();
        
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_GETLISTA);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long codigoDezScores = rs.getLong(1);
                long pontuacaoPlayer = rs.getLong(2);
                
                RankingDezScores rds = new RankingDezScores(codigoDezScores, pontuacaoPlayer);
                listaRankDezSco.add(rds);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter registros dos dez primeiros scores (pontuações) do Ranking", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return listaRankDezSco;
    }
    
}
