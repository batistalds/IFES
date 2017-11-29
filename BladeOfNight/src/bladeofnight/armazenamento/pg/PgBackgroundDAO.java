
package bladeofnight.armazenamento.pg;

import bladeofnight.armazenamento.BackgroundDAO;
import bladeofnight.entidades.Background;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PgBackgroundDAO implements BackgroundDAO {
    
    private static final String SCRIPT_BUSCAR = "SELECT codigo, movimentoX, movimentoY, velMoviX, velMoviY " +
                                                "FROM Background " +
                                                "WHERE codigo = ?";
    
    private static final String SCRIPT_INSERIR = "INSERT INTO Background (movimentoX, movimentoY, velMoviX, velMoviY) VALUES(?, ?, ?, ?)";
    
    private static final String SCRIPT_ALTERAR = "UPDATE Background SET movimentoX = ?, movimentoY = ?, velMoviX = ?, velMoviY = ? WHERE codigo = ?";
    
    private static final String SCRIPT_EXCLUIR = "DELETE FROM Background WHERE codigo = ?";
    
    private static final String SCRIPT_GETLISTA = "SELECT codigo, movimentoX, movimentoY, velMoviX, velMoviY " +
                                                  "FROM Background ";
    
    @Override
    public Background buscar(Background background) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_BUSCAR);
            ps.setLong(1, background.getCodigo());            
            // Adquirindo resultado da Query após sua execução
            ResultSet rs = ps.executeQuery();
            // Pulamos para a primeira Linha resultada da Query, se houver alguma coisa, quer dizer que retornamos alguma coisa (não vazio)
            if (rs.next()) {
                long codigoBackground = rs.getLong(1);
                boolean movimentoX = rs.getBoolean(2);
                boolean movimentoY = rs.getBoolean(3);
                int velMoviX = rs.getInt(4);
                int velMoviY = rs.getInt(5);
                
                Background backgroundEncontrado = new Background(codigoBackground, movimentoX, movimentoY, velMoviX, velMoviY);
                return backgroundEncontrado;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter registro de background", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return null;
    }

    @Override
    public boolean inserir(Background background) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_INSERIR);
            ps.setBoolean(1, background.isMovimentoX());
            ps.setBoolean(2, background.isMovimentoY());
            ps.setInt(3, background.getVelMoviX());
            ps.setInt(4, background.getVelMoviY());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1; // Como acrescenta-se uma linha à tabela, o resultado esperado para sucesso da execução é 1
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao inserir um registro de background", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    @Override
    public boolean alterar(Background background) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_ALTERAR);
            ps.setBoolean(1, background.isMovimentoX());
            ps.setBoolean(2, background.isMovimentoY());
            ps.setInt(3, background.getVelMoviX());
            ps.setInt(4, background.getVelMoviY());
            ps.setLong(5, background.getCodigo());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao alterar um registro de background", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    @Override
    public boolean excluir(Background background) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_EXCLUIR);
            ps.setLong(1, background.getCodigo());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir um registro de background", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    private ArrayList<Background> listaBackgrounds;
    
    @Override
    public ArrayList<Background> getLista() {
        if (listaBackgrounds == null) 
            listaBackgrounds = new ArrayList<>();
        else 
            listaBackgrounds.clear();
        
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_GETLISTA);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long codigo = rs.getLong(1);
                boolean movimentoX = rs.getBoolean(2);
                boolean movimentoY = rs.getBoolean(3);
                int velMoviX = rs.getInt(4);
                int velMoviY = rs.getInt(5);
                
                Background back = new Background(codigo, movimentoX, movimentoY, velMoviX, velMoviY);
                listaBackgrounds.add(back);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter registros de compras", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return listaBackgrounds;
    }
    
}
