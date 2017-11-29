
package bladeofnight.armazenamento.pg;

import bladeofnight.armazenamento.HudDAO;
import bladeofnight.entidades.HUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PgHudDAO implements HudDAO {
    
    private static final String SCRIPT_BUSCAR = "SELECT codigo, prioridadeImgs, listaTextos, listaNumeros " +
                                                "FROM HUD " +
                                                "WHERE codigo = ?";
    
    private static final String SCRIPT_INSERIR = "INSERT INTO HUD (prioridadeImgs, listaTextos, listaNumeros) VALUES(?, ?, ?)";
    
    private static final String SCRIPT_ALTERAR = "UPDATE HUD SET prioridadeImgs = ?, listaTextos = ?, listaNumeros = ? WHERE codigo = ?";
    
    private static final String SCRIPT_EXCLUIR = "DELETE FROM HUD WHERE codigo = ?";
    
    private static final String SCRIPT_GETLISTA = "SELECT codigo, prioridadeImgs, listaTextos, listaNumeros " +
                                                  "FROM HUD ";
    
    @Override
    public HUD buscar(HUD hud) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_BUSCAR);
            ps.setLong(1, hud.getCodigo());
            // Adquirindo resultado da Query após sua execução
            ResultSet rs = ps.executeQuery();
            // Pulamos para a primeira Linha resultada da Query, se houver alguma coisa, quer dizer que retornamos alguma coisa (não vazio)
            if (rs.next()) {
                long codigoHud = rs.getLong(1);
                int prioridadeImgs = rs.getInt(2);
                int listaTextos = rs.getInt(3);
                int listaNumeros = rs.getInt(4);
                
                HUD hudEncontrado = new HUD(codigoHud, prioridadeImgs, listaTextos, listaNumeros);
                return hudEncontrado;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter registro de HUD", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return null;
    }

    @Override
    public boolean inserir(HUD hud) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_INSERIR);
            ps.setInt(1, hud.getPrioridadeImgs());
            ps.setInt(2, hud.getListaTextos());
            ps.setInt(3, hud.getListaNumeros());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1; // Como acrescenta-se uma linha à tabela, o resultado esperado para sucesso da execução é 1
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao inserir um registro de HUD", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    @Override
    public boolean alterar(HUD hud) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_ALTERAR);
            ps.setInt(1, hud.getPrioridadeImgs());
            ps.setInt(2, hud.getListaTextos());
            ps.setInt(3, hud.getListaNumeros());
            ps.setLong(4, hud.getCodigo());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao alterar um registro de HUD", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    @Override
    public boolean excluir(HUD hud) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_EXCLUIR);
            ps.setLong(1, hud.getCodigo());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir um registro de HUD", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    private ArrayList<HUD> listaHUDs;
    
    @Override
    public ArrayList<HUD> getLista() {
        if (listaHUDs == null) 
            listaHUDs = new ArrayList<>();
        else 
            listaHUDs.clear();
        
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_GETLISTA);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long codigo = rs.getLong(1);
                int prioridadeImgs = rs.getInt(2);
                int listaTextos = rs.getInt(3);
                int listaNumeros = rs.getInt(4);
                
                HUD hud = new HUD(codigo, prioridadeImgs, listaTextos, listaNumeros);
                listaHUDs.add(hud);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter registros de HUDs", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return listaHUDs;
    }
    
}
