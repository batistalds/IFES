
package bladeofnight.armazenamento.pg;

import bladeofnight.armazenamento.HudTextoDAO;
import bladeofnight.entidades.HUDTexto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PgHudTextoDAO implements HudTextoDAO {
    
    private static final String SCRIPT_BUSCAR = "SELECT codigoHUDTextos, texto " +
                                                "FROM HUD_Textos " +
                                                "WHERE codigoHUDTextos = ?";
    
    private static final String SCRIPT_INSERIR = "INSERT INTO HUD_Textos (texto) VALUES(?)";
    
    private static final String SCRIPT_ALTERAR = "UPDATE HUD_Textos SET texto = ? WHERE codigoHUDTextos = ?";
    
    private static final String SCRIPT_EXCLUIR = "DELETE FROM HUD_Textos WHERE codigoHUDTextos = ?";
    
    private static final String SCRIPT_GETLISTA = "SELECT codigoHUDTextos, texto " +
                                                  "FROM HUD_Textos ";
    
    @Override
    public HUDTexto buscar(HUDTexto hud) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_BUSCAR);
            ps.setLong(1, hud.getCodigo());
            // Adquirindo resultado da Query após sua execução
            ResultSet rs = ps.executeQuery();
            // Pulamos para a primeira Linha resultada da Query, se houver alguma coisa, quer dizer que retornamos alguma coisa (não vazio)
            if (rs.next()) {
                long codigoHUDTextos = rs.getLong(1);
                String texto = rs.getString(2);
                
                HUDTexto hudTEncontrado = new HUDTexto(codigoHUDTextos, texto);
                return hudTEncontrado;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter registro de Texto de uma HUD", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return null;
    }

    @Override
    public boolean inserir(HUDTexto hud) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_INSERIR);
            ps.setString(1, hud.getTexto());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1; // Como acrescenta-se uma linha à tabela, o resultado esperado para sucesso da execução é 1
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao inserir um registro de Texto de uma HUD", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    @Override
    public boolean alterar(HUDTexto hud) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_ALTERAR);
            ps.setString(1, hud.getTexto());
            ps.setLong(2, hud.getCodigo());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao alterar um registro de Texto de uma HUD", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    @Override
    public boolean excluir(HUDTexto hud) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_EXCLUIR);
            ps.setLong(1, hud.getCodigo());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir um registro de Texto de uma HUD", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    private ArrayList<HUDTexto> listaTxtHUDs;
    
    @Override
    public ArrayList<HUDTexto> getLista() {
        if (listaTxtHUDs == null)
            listaTxtHUDs = new ArrayList<>();
        else 
            listaTxtHUDs.clear();
        
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_GETLISTA);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long codigoHUDTextos = rs.getLong(1);
                String texto = rs.getString(2);
                
                HUDTexto hud = new HUDTexto(codigoHUDTextos, texto);
                listaTxtHUDs.add(hud);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter registros de Texto de uma HUD", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return listaTxtHUDs;
    }
    
}
