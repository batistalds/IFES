
package bladeofnight.armazenamento.pg;

import bladeofnight.armazenamento.HudNumeroDAO;
import bladeofnight.entidades.HUDNumero;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PgHudNumeroDAO implements HudNumeroDAO {
    
    private static final String SCRIPT_BUSCAR = "SELECT codigoHUDNumeros, numero " +
                                                "FROM HUD_Numeros " +
                                                "WHERE codigoHUDNumeros = ?";
    
    private static final String SCRIPT_INSERIR = "INSERT INTO HUD_Numeros (numero) VALUES(?)";
    
    private static final String SCRIPT_ALTERAR = "UPDATE HUD_Numeros SET numero = ? WHERE codigoHUDNumeros = ?";
    
    private static final String SCRIPT_EXCLUIR = "DELETE FROM HUD_Numeros WHERE codigoHUDNumeros = ?";
    
    private static final String SCRIPT_GETLISTA = "SELECT codigoHUDNumeros, numero " +
                                                  "FROM HUD_Numeros ";
    
    @Override
    public HUDNumero buscar(HUDNumero hud) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_BUSCAR);
            ps.setLong(1, hud.getCodigo());
            // Adquirindo resultado da Query após sua execução
            ResultSet rs = ps.executeQuery();
            // Pulamos para a primeira Linha resultada da Query, se houver alguma coisa, quer dizer que retornamos alguma coisa (não vazio)
            if (rs.next()) {
                long codigoHUDNumeros = rs.getLong(1);
                int numero = rs.getInt(2);
                
                HUDNumero hudNEncontrado = new HUDNumero(codigoHUDNumeros, numero);
                return hudNEncontrado;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter registro de Número de uma HUD", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return null;
    }

    @Override
    public boolean inserir(HUDNumero hud) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_INSERIR);
            ps.setInt(1, hud.getNumero());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1; // Como acrescenta-se uma linha à tabela, o resultado esperado para sucesso da execução é 1
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao inserir um registro de Número de uma HUD", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    @Override
    public boolean alterar(HUDNumero hud) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_ALTERAR);
            ps.setInt(1, hud.getNumero());
            ps.setLong(2, hud.getCodigo());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao alterar um registro de Número de uma HUD", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    @Override
    public boolean excluir(HUDNumero hud) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_EXCLUIR);
            ps.setLong(1, hud.getCodigo());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir um registro de Número de uma HUD", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    private ArrayList<HUDNumero> listaNumHUDs;
    
    @Override
    public ArrayList<HUDNumero> getLista() {
        if (listaNumHUDs == null)
            listaNumHUDs = new ArrayList<>();
        else 
            listaNumHUDs.clear();
        
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_GETLISTA);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long codigoHUDNumeros = rs.getLong(1);
                int numero = rs.getInt(2);
                
                HUDNumero hud = new HUDNumero(codigoHUDNumeros, numero);
                listaNumHUDs.add(hud);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter registros de Número de uma HUD", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return listaNumHUDs;
    }
    
}
