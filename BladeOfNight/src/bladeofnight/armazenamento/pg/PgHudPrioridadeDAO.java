
package bladeofnight.armazenamento.pg;

import bladeofnight.armazenamento.HudPrioridadeDAO;
import bladeofnight.entidades.HUDPrioridade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PgHudPrioridadeDAO implements HudPrioridadeDAO {
    
    private static final String SCRIPT_BUSCAR = "SELECT codigoHUDPrioridades, valorPrioridade " +
                                                "FROM HUD_Prioridades " +
                                                "WHERE codigoHUDPrioridades = ?";
    
    private static final String SCRIPT_INSERIR = "INSERT INTO HUD_Prioridades (valorPrioridade) VALUES(?)";
    
    private static final String SCRIPT_ALTERAR = "UPDATE HUD_Prioridades SET valorPrioridade = ? WHERE codigoHUDPrioridades = ?";
    
    private static final String SCRIPT_EXCLUIR = "DELETE FROM HUD_Prioridades WHERE codigoHUDPrioridades = ?";
    
    private static final String SCRIPT_GETLISTA = "SELECT codigoHUDPrioridades, valorPrioridade " +
                                                  "FROM HUD_Prioridades ";
    
    @Override
    public HUDPrioridade buscar(HUDPrioridade hud) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_BUSCAR);
            ps.setLong(1, hud.getCodigo());
            // Adquirindo resultado da Query após sua execução
            ResultSet rs = ps.executeQuery();
            // Pulamos para a primeira Linha resultada da Query, se houver alguma coisa, quer dizer que retornamos alguma coisa (não vazio)
            if (rs.next()) {
                long codigoHUDPrioridades = rs.getLong(1);
                int valorPrioridade = rs.getInt(2);
                
                HUDPrioridade hudPEncontrado = new HUDPrioridade(codigoHUDPrioridades, valorPrioridade);
                return hudPEncontrado;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter registro de Prioridade de uma HUD", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return null;
    }

    @Override
    public boolean inserir(HUDPrioridade hud) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_INSERIR);
            ps.setLong(1, hud.getCodigo());
            ps.setInt(2, hud.getValorPrioridade());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1; // Como acrescenta-se uma linha à tabela, o resultado esperado para sucesso da execução é 1
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao inserir um registro de Prioridade de uma HUD", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    @Override
    public boolean alterar(HUDPrioridade hud) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_ALTERAR);
            ps.setInt(1, hud.getValorPrioridade());
            ps.setLong(2, hud.getCodigo());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao alterar um registro de Prioridade de uma HUD", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    @Override
    public boolean excluir(HUDPrioridade hud) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_EXCLUIR);
            ps.setLong(1, hud.getCodigo());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir um registro de Prioridade de uma HUD", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    private ArrayList<HUDPrioridade> listaPriHUDs;
    
    @Override
    public ArrayList<HUDPrioridade> getLista() {
        if (listaPriHUDs == null)
            listaPriHUDs = new ArrayList<>();
        else 
            listaPriHUDs.clear();
        
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_GETLISTA);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long codigoHUDPrioridades = rs.getLong(1);
                int valorPrioridade = rs.getInt(2);
                
                HUDPrioridade hud = new HUDPrioridade(codigoHUDPrioridades, valorPrioridade);
                listaPriHUDs.add(hud);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter registros de Prioridade de uma HUD", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return listaPriHUDs;
    }
    
}
