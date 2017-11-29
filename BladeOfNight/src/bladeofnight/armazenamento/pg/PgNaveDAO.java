
package bladeofnight.armazenamento.pg;

import bladeofnight.armazenamento.NaveDAO;
import bladeofnight.entidades.Nave;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PgNaveDAO implements NaveDAO {
    
    private static final String SCRIPT_BUSCAR = "SELECT codigo, cor, tipo, velocidade, poder" +
                                                "FROM Nave " +
                                                "WHERE codigo = ?";
    
    private static final String SCRIPT_INSERIR = "INSERT INTO Nave (cor, tipo, velocidade, poder) VALUES(?, ?, ?, ?)";
    
    private static final String SCRIPT_ALTERAR = "UPDATE Nave SET cor = ?, tipo = ?, velocidade = ?, poder = ? WHERE codigo = ?";
    
    private static final String SCRIPT_EXCLUIR = "DELETE FROM Nave WHERE codigo = ?";
    
    private static final String SCRIPT_GETLISTA = "SELECT codigo, cor, tipo, velocidade, poder" +
                                                  "FROM Nave ";
    
    @Override
    public Nave buscar(Nave nave) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_BUSCAR);
            ps.setLong(1, nave.getCodigo());            
            // Adquirindo resultado da Query após sua execução
            ResultSet rs = ps.executeQuery();
            // Pulamos para a primeira Linha resultada da Query, se houver alguma coisa, quer dizer que retornamos alguma coisa (não vazio)
            if (rs.next()) {
                long codigoNave = rs.getLong(1);
                String cor = rs.getString(2);
                char tipo = rs.getString(3).charAt(0);
                int velocidade = rs.getInt(4);
                int poder = rs.getInt(5);
                
                Nave naveEncontrada = new Nave(codigoNave, cor, tipo, velocidade, poder);
                return naveEncontrada;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter registro de nave", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return null;
    }

    @Override
    public boolean inserir(Nave nave) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_INSERIR);
            ps.setString(1, nave.getCor());
            ps.setString(2, Character.toString(nave.getTipo()));
            ps.setInt(3, nave.getVelocidade());
            ps.setInt(4, nave.getPoder());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1; // Como acrescenta-se uma linha à tabela, o resultado esperado para sucesso da execução é 1
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao inserir um registro de nave", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    @Override
    public boolean alterar(Nave nave) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_ALTERAR);
            ps.setString(1, nave.getCor());
            ps.setString(2, Character.toString(nave.getTipo()));
            ps.setInt(3, nave.getVelocidade());
            ps.setInt(4, nave.getPoder());
            ps.setLong(5, nave.getCodigo());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao alterar um registro de nave", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    @Override
    public boolean excluir(Nave nave) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_EXCLUIR);
            ps.setLong(1, nave.getCodigo());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir um registro de nave", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    private ArrayList<Nave> listaNaves;
    
    @Override
    public ArrayList<Nave> getLista() {
        if (listaNaves == null) 
            listaNaves = new ArrayList<>();
        else 
            listaNaves.clear();
        
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_GETLISTA);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long codigoNave = rs.getLong(1);
                String cor = rs.getString(2);
                char tipo = rs.getString(3).charAt(0);
                int velocidade = rs.getInt(4);
                int poder = rs.getInt(5);
                
                Nave nave = new Nave(codigoNave, cor, tipo, velocidade, poder);
                listaNaves.add(nave);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter registros de naves", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return listaNaves;
    }
    
}
