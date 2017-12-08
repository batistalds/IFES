
package bladeofnight.armazenamento.pg;

import bladeofnight.armazenamento.InimigoDAO;
import bladeofnight.entidades.Inimigo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PgInimigoDAO implements InimigoDAO {
    
    private static final String SCRIPT_BUSCAR = "SELECT codigo, nome, tipo, nave " +
                                                "FROM Inimigo " +
                                                "WHERE codigo = ?";
    
    private static final String SCRIPT_INSERIR = "INSERT INTO Inimigo (nome, tipo, nave) VALUES(?, ?, ?)";
    
    private static final String SCRIPT_ALTERAR = "UPDATE Inimigo SET nome = ?, tipo = ?, nave = ? WHERE codigo = ?";
    
    private static final String SCRIPT_EXCLUIR = "DELETE FROM Inimigo WHERE codigo = ?";
    
    private static final String SCRIPT_GETLISTA = "SELECT * " +
                                                  "FROM Inimigo ";
    
    private static final String SCRIPT_BUSCARCODIGO = "SELECT * FROM Inimigo WHERE nome = ? AND tipo = ? AND nave = ?";
    private static final String SCRIPT_BUSCARCODIGO_NAVE = "SELECT * FROM Inimigo WHERE nave = ?";
    
    @Override
    public Inimigo buscar(Inimigo inimigo) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_BUSCAR);
            ps.setLong(1, inimigo.getCodigo());            
            // Adquirindo resultado da Query após sua execução
            ResultSet rs = ps.executeQuery();
            // Pulamos para a primeira Linha resultada da Query, se houver alguma coisa, quer dizer que retornamos alguma coisa (não vazio)
            if (rs.next()) {
                long codigoInimigo = rs.getLong(1);
                String nome = rs.getString(2);
                char tipo = rs.getString(3).charAt(0);
                long nave = rs.getLong(4);
                
                Inimigo inimigoEncontrado = new Inimigo(codigoInimigo, nave, nome, tipo);
                return inimigoEncontrado;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter registro de inimigo", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return null;
    }
    
    @Override
    public long buscarCodigo(Inimigo inimigo) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_BUSCARCODIGO);
            ps.setString(1, inimigo.getNomeInimigo());
            ps.setString(2, Character.toString(inimigo.getTipoInimigo()));
            ps.setLong(3, inimigo.getNaveId());
            // Adquirindo resultado da Query após sua execução
            ResultSet rs = ps.executeQuery();
            // Pulamos para a primeira Linha resultada da Query, se houver alguma coisa, quer dizer que retornamos alguma coisa (não vazio)
            if (rs.next()) {
                long codigoInimigo = rs.getLong(1);
                return codigoInimigo;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter o código de um registro de inimigo", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return -1;
    }
    
    @Override
    public long buscarCodigoComNave(long codNave) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_BUSCARCODIGO_NAVE);
            ps.setLong(1, codNave);
            // Adquirindo resultado da Query após sua execução
            ResultSet rs = ps.executeQuery();
            // Pulamos para a primeira Linha resultada da Query, se houver alguma coisa, quer dizer que retornamos alguma coisa (não vazio)
            if (rs.next()) {
                long codigoInimigo = rs.getLong(1);
                return codigoInimigo;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter o código de um registro de inimigo", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return -1;
    }

    @Override
    public boolean inserir(Inimigo inimigo) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_INSERIR);
            ps.setString(1, inimigo.getNomeInimigo());
            ps.setString(2, Character.toString(inimigo.getTipoInimigo()));
            ps.setLong(3, inimigo.getNaveId());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1; // Como acrescenta-se uma linha à tabela, o resultado esperado para sucesso da execução é 1
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao inserir um registro de inimigo", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    @Override
    public boolean alterar(Inimigo inimigo) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_ALTERAR);
            ps.setString(1, inimigo.getNomeInimigo());
            ps.setString(2, Character.toString(inimigo.getTipoInimigo()));
            ps.setLong(3, inimigo.getNaveId());
            ps.setLong(4, inimigo.getCodigo());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao alterar um registro de inimigo", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    @Override
    public boolean excluir(Inimigo inimigo) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_EXCLUIR);
            ps.setLong(1, inimigo.getCodigo());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir um registro de inimigo", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    private ArrayList<Inimigo> listaInimigos;
    
    @Override
    public ArrayList<Inimigo> getLista() {
        if (listaInimigos == null) 
            listaInimigos = new ArrayList<>();
        else 
            listaInimigos.clear();
        
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_GETLISTA);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long codigoInimigo = rs.getLong(1);
                String nome = rs.getString(2);
                char tipo = rs.getString(3).charAt(0);
                long nave = rs.getLong(4);
                
                Inimigo inimigo = new Inimigo(codigoInimigo, nave, nome, tipo);
                listaInimigos.add(inimigo);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter registros de inimigos", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return listaInimigos;
    }
    
}
