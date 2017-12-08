
package bladeofnight.armazenamento.pg;

import bladeofnight.armazenamento.RankingDezDatasDAO;
import bladeofnight.entidades.RankingDezDatas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class PgRankingDezDatasDAO implements RankingDezDatasDAO {
    
    private static final String SCRIPT_BUSCAR = "SELECT codigoDezDatas, dataDoRecorde " +
                                                "FROM Dez_Datas_Ranking " +
                                                "WHERE codigoDezDatas = ?";
    
    private static final String SCRIPT_INSERIR = "INSERT INTO Dez_Datas_Ranking (dataDoRecorde) VALUES(?)";
    
    private static final String SCRIPT_ALTERAR = "UPDATE Dez_Datas_Ranking SET dataDoRecorde = ? WHERE codigoDezDatas = ?";
    
    private static final String SCRIPT_EXCLUIR = "DELETE FROM Dez_Datas_Ranking WHERE codigoDezDatas = ?";
    
    private static final String SCRIPT_GETLISTA = "SELECT codigoDezDatas, dataDoRecorde " +
                                                  "FROM Dez_Datas_Ranking ";
    
    @Override
    public RankingDezDatas buscar(RankingDezDatas rdd) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_BUSCAR);
            ps.setLong(1, rdd.getCodigo());
            // Adquirindo resultado da Query após sua execução
            ResultSet rs = ps.executeQuery();
            // Pulamos para a primeira Linha resultada da Query, se houver alguma coisa, quer dizer que retornamos alguma coisa (não vazio)
            if (rs.next()) {
                long codigoDezDatas = rs.getLong(1);
                Date dataDoRecorde = rs.getDate(2);
                
                RankingDezDatas rddEncontrado = new RankingDezDatas(codigoDezDatas, dataDoRecorde);
                return rddEncontrado;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter um registro das dez primeiras datas do Ranking", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return null;
    }

    @Override
    public boolean inserir(RankingDezDatas rdd) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_INSERIR);
            ps.setDate(1, (java.sql.Date) rdd.getDataDoRecorde());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1; // Como acrescenta-se uma linha à tabela, o resultado esperado para sucesso da execução é 1
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao inserir um registro nas dez primeiras datas do Ranking", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    @Override
    public boolean alterar(RankingDezDatas rdd) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_ALTERAR);
            ps.setDate(1, (java.sql.Date) rdd.getDataDoRecorde());
            ps.setLong(2, rdd.getCodigo());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao alterar um registro das dez primeiras datas do Ranking", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    @Override
    public boolean excluir(RankingDezDatas rdd) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_EXCLUIR);
            ps.setLong(1, rdd.getCodigo());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir um registro das dez primeiras datas do Ranking", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    private ArrayList<RankingDezDatas> listaRankDezDat;
    
    @Override
    public ArrayList<RankingDezDatas> getLista() {
        if (listaRankDezDat == null)
            listaRankDezDat = new ArrayList<>();
        else 
            listaRankDezDat.clear();
        
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_GETLISTA);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long codigoDezDatas = rs.getLong(1);
                Date dataDoRecorde = rs.getDate(2);
                
                RankingDezDatas rdd = new RankingDezDatas(codigoDezDatas, dataDoRecorde);
                listaRankDezDat.add(rdd);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter registros das dez primeiras datas do Ranking", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return listaRankDezDat;
    }
    
}
