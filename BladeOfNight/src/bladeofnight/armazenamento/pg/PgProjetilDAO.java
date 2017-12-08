
package bladeofnight.armazenamento.pg;

import bladeofnight.armazenamento.ProjetilDAO;
import bladeofnight.entidades.Projetil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PgProjetilDAO implements ProjetilDAO {
    
    private static final String SCRIPT_BUSCAR = "SELECT *" +
                                                "FROM Projetil " +
                                                "WHERE codigo = ?";
    
    private static final String SCRIPT_INSERIR = "INSERT INTO Projetil (velX, velY, angulo, delay, poder) VALUES(?, ?, ?, ?, ?)";
    
    private static final String SCRIPT_ALTERAR = "UPDATE Projetil SET velX = ?, velY = ?, angulo = ?, delay = ?, poder = ? WHERE codigo = ?";
    
    private static final String SCRIPT_EXCLUIR = "DELETE FROM Projetil WHERE codigo = ?";
    
    private static final String SCRIPT_GETLISTA = "SELECT *" +
                                                  "FROM Projetil ";
    
    @Override
    public Projetil buscar(Projetil projetil) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_BUSCAR);
            ps.setLong(1, projetil.getCodigo());
            // Adquirindo resultado da Query após sua execução
            ResultSet rs = ps.executeQuery();
            // Pulamos para a primeira Linha resultada da Query, se houver alguma coisa, quer dizer que retornamos alguma coisa (não vazio)
            if (rs.next()) {
                long codigoProjetil = rs.getLong(1);
                int velX = rs.getInt(2);
                int velY = rs.getInt(3);
                int angulo = rs.getInt(4);
                double delay = rs.getDouble(5);
                int poder = rs.getInt(6);
                
                Projetil projetilEncontrado = new Projetil(codigoProjetil, velX, velY, angulo, delay, poder);
                return projetilEncontrado;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter registro de projetil", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return null;
    }

    @Override
    public boolean inserir(Projetil projetil) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_INSERIR);
            ps.setInt(1, projetil.getVelX());
            ps.setInt(2, projetil.getVelY());
            ps.setInt(3, projetil.getAngulo());
            ps.setDouble(4, projetil.getDelay());
            ps.setInt(5, projetil.getPoder());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1; // Como acrescenta-se uma linha à tabela, o resultado esperado para sucesso da execução é 1
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao inserir um registro de projetil", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    @Override
    public boolean alterar(Projetil projetil) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_ALTERAR);
            ps.setInt(1, projetil.getVelX());
            ps.setInt(2, projetil.getVelY());
            ps.setInt(3, projetil.getAngulo());
            ps.setDouble(4, projetil.getDelay());
            ps.setInt(5, projetil.getPoder());
            ps.setLong(6, projetil.getCodigo());

            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao alterar um registro de projetil", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    @Override
    public boolean excluir(Projetil projetil) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_EXCLUIR);
            ps.setLong(1, projetil.getCodigo());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir um registro de projetil", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    private ArrayList<Projetil> listaProjeteis;
    
    @Override
    public ArrayList<Projetil> getLista() {
        if (listaProjeteis == null) 
            listaProjeteis = new ArrayList<>();
        else 
            listaProjeteis.clear();
        
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_GETLISTA);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long codigoProjetil = rs.getLong(1);
                int velX = rs.getInt(2);
                int velY = rs.getInt(3);
                int angulo = rs.getInt(4);
                double delay = rs.getDouble(5);
                int poder = rs.getInt(6);
                
                Projetil proj = new Projetil(codigoProjetil, velX, velY, angulo, delay, poder);
                listaProjeteis.add(proj);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter registros de projeteis", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return listaProjeteis;
    }
    
}
