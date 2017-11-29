
package bladeofnight.armazenamento.pg;

import bladeofnight.armazenamento.ItemDAO;
import bladeofnight.entidades.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PgItemDAO implements ItemDAO {
    
    private static final String SCRIPT_BUSCAR = "SELECT codigo, nome, efeito, velY" +
                                                "FROM Item " +
                                                "WHERE codigo = ?";
    
    private static final String SCRIPT_INSERIR = "INSERT INTO Item (nome, efeito, velY) VALUES(?, ?, ?)";
    
    private static final String SCRIPT_ALTERAR = "UPDATE Item SET nome = ?, efeito = ?, velY = ? WHERE codigo = ?";
    
    private static final String SCRIPT_EXCLUIR = "DELETE FROM Item WHERE codigo = ?";
    
    private static final String SCRIPT_GETLISTA = "SELECT codigo, nome, efeito, velY " +
                                                  "FROM Item ";
    
    @Override
    public Item buscar(Item item) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_BUSCAR);
            ps.setLong(1, item.getCodigo());            
            // Adquirindo resultado da Query após sua execução
            ResultSet rs = ps.executeQuery();
            // Pulamos para a primeira Linha resultada da Query, se houver alguma coisa, quer dizer que retornamos alguma coisa (não vazio)
            if (rs.next()) {
                long codigoItem = rs.getLong(1);
                String nome = rs.getString(2);
                String efeito = rs.getString(3);
                int velY = rs.getInt(4);
                
                Item itemEncontrado = new Item(codigoItem, nome, efeito, velY);
                return itemEncontrado;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter registro de item", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return null;
    }

    @Override
    public boolean inserir(Item item) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_INSERIR);
            ps.setString(1, item.getNome());
            ps.setString(2, item.getEfeito());
            ps.setInt(3, item.getVelY());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1; // Como acrescenta-se uma linha à tabela, o resultado esperado para sucesso da execução é 1
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao inserir um registro de item", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    @Override
    public boolean alterar(Item item) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_ALTERAR);
            ps.setString(1, item.getNome());
            ps.setString(2, item.getEfeito());
            ps.setInt(3, item.getVelY());
            ps.setLong(4, item.getCodigo());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao alterar um registro de item", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    @Override
    public boolean excluir(Item item) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_EXCLUIR);
            ps.setLong(1, item.getCodigo());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir um registro de item", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    private ArrayList<Item> listaItens;
    
    @Override
    public ArrayList<Item> getLista() {
        if (listaItens == null) 
            listaItens = new ArrayList<>();
        else 
            listaItens.clear();
        
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_GETLISTA);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long codigoItem = rs.getLong(1);
                String nome = rs.getString(2);
                String efeito = rs.getString(3);
                int velY = rs.getInt(4);
                
                Item item = new Item(codigoItem, nome, efeito, velY);
                listaItens.add(item);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter registros de itens", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return listaItens;
    }
    
}
