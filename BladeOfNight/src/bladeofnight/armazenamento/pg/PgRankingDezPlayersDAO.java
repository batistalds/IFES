
package bladeofnight.armazenamento.pg;

import bladeofnight.armazenamento.RankingDezPlayersDAO;
import bladeofnight.entidades.RankingDezPlayers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PgRankingDezPlayersDAO implements RankingDezPlayersDAO {
    
    private static final String SCRIPT_BUSCAR = "SELECT codigoDezPlayers, nomePlayer " +
                                                "FROM Dez_Players_Ranking " +
                                                "WHERE codigoDezPlayers = ?";
    
    private static final String SCRIPT_INSERIR = "INSERT INTO Dez_Players_Ranking (nomePlayer) VALUES(?)";
    
    private static final String SCRIPT_ALTERAR = "UPDATE Dez_Players_Ranking SET nomePlayer = ? WHERE codigoDezPlayers = ?";
    
    private static final String SCRIPT_EXCLUIR = "DELETE FROM Dez_Players_Ranking WHERE codigoDezPlayers = ?";
    
    private static final String SCRIPT_GETLISTA = "SELECT codigoDezPlayers, nomePlayer " +
                                                  "FROM Dez_Players_Ranking ";
    
    @Override
    public RankingDezPlayers buscar(RankingDezPlayers rdp) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_BUSCAR);
            ps.setLong(1, rdp.getCodigo());
            // Adquirindo resultado da Query após sua execução
            ResultSet rs = ps.executeQuery();
            // Pulamos para a primeira Linha resultada da Query, se houver alguma coisa, quer dizer que retornamos alguma coisa (não vazio)
            if (rs.next()) {
                long codigoDezPlayers = rs.getLong(1);
                String nomePlayer = rs.getString(2);
                
                RankingDezPlayers rdpEncontrado = new RankingDezPlayers(codigoDezPlayers, nomePlayer);
                return rdpEncontrado;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter um registro dos dez primeiros players do Ranking", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return null;
    }

    @Override
    public boolean inserir(RankingDezPlayers rdp) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_INSERIR);
            ps.setString(1, rdp.getNomePlayer());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1; // Como acrescenta-se uma linha à tabela, o resultado esperado para sucesso da execução é 1
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao inserir um registro nos dez primeiros players do Ranking", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    @Override
    public boolean alterar(RankingDezPlayers rdp) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_ALTERAR);
            ps.setString(1, rdp.getNomePlayer());
            ps.setLong(2, rdp.getCodigo());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao alterar um registro dos dez primeiros players do Ranking", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    @Override
    public boolean excluir(RankingDezPlayers rdp) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_EXCLUIR);
            ps.setLong(1, rdp.getCodigo());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir um registro dos dez primeiros players do Ranking", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    private ArrayList<RankingDezPlayers> listaRankDezPla;
    
    @Override
    public ArrayList<RankingDezPlayers> getLista() {
        if (listaRankDezPla == null)
            listaRankDezPla = new ArrayList<>();
        else 
            listaRankDezPla.clear();
        
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_GETLISTA);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long codigoDezPlayers = rs.getLong(1);
                String nomePlayer = rs.getString(2);
                
                RankingDezPlayers rdp = new RankingDezPlayers(codigoDezPlayers, nomePlayer);
                listaRankDezPla.add(rdp);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter registros dos dez primeiros players do Ranking", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return listaRankDezPla;
    }
    
}
