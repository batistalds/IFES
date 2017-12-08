
package bladeofnight.armazenamento.pg;

import bladeofnight.armazenamento.BossDAO;
import bladeofnight.entidades.Boss;
import bladeofnight.entidades.Inimigo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PgBossDAO implements BossDAO {
    
    private static final String SCRIPT_BUSCAR = "SELECT codigo, fase, inimigo " +
                                                "FROM Boss " +
                                                "WHERE codigo = ?";
    
    private static final String SCRIPT_INSERIR = "INSERT INTO Boss (fase, inimigo) VALUES(?, ?)";
    
    private static final String SCRIPT_ALTERAR = "UPDATE Boss SET fase = ?, inimigo = ? WHERE codigo = ?";
    
    private static final String SCRIPT_EXCLUIR = "DELETE FROM Boss WHERE codigo = ?";
    
    private static final String SCRIPT_GETLISTA = "SELECT * " +
                                                  "FROM Boss ";
    
    private static final String SCRIPT_BUSCARCODIGO_NAVE = "SELECT * FROM Boss WHERE inimigo = ?";
    
    @Override
    public Boss buscar(Boss boss) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_BUSCAR);
            ps.setLong(1, boss.getCodigo());            
            // Adquirindo resultado da Query após sua execução
            ResultSet rs = ps.executeQuery();
            // Pulamos para a primeira Linha resultada da Query, se houver alguma coisa, quer dizer que retornamos alguma coisa (não vazio)
            if (rs.next()) {
                long codigoBoss = rs.getLong(1);
                int fase = rs.getInt(2);
                long inimigo = rs.getLong(3);
                
                Boss bossEncontrado = new Boss(0, inimigo, codigoBoss, fase);
                return bossEncontrado;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter registro de boss", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return null;
    }
    
    @Override
    public long buscarCodigoComInimigo(long codInimigo) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_BUSCARCODIGO_NAVE);
            ps.setLong(1, codInimigo);
            // Adquirindo resultado da Query após sua execução
            ResultSet rs = ps.executeQuery();
            // Pulamos para a primeira Linha resultada da Query, se houver alguma coisa, quer dizer que retornamos alguma coisa (não vazio)
            if (rs.next()) {
                long codigoBoss = rs.getLong(1);
                return codigoBoss;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter o código de um registro de boss", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return -1;
    }

    @Override
    public boolean inserir(Boss boss) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_INSERIR);
            ps.setInt(1, boss.getFase());
            ps.setLong(2, boss.getInimigoId());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1; // Como acrescenta-se uma linha à tabela, o resultado esperado para sucesso da execução é 1
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao inserir um registro de boss", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    @Override
    public boolean alterar(Boss boss) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_ALTERAR);
            ps.setInt(1, boss.getFase());
            ps.setLong(2, boss.getInimigoId());
            ps.setLong(3, boss.getCodigo());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao alterar um registro de boss", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    @Override
    public boolean excluir(Boss boss) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_EXCLUIR);
            ps.setLong(1, boss.getCodigo());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir um registro de boss", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    private ArrayList<Boss> listaBosses;
    
    @Override
    public ArrayList<Boss> getLista() {
        if (listaBosses == null) 
            listaBosses = new ArrayList<>();
        else 
            listaBosses.clear();
        
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(SCRIPT_GETLISTA);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long codigoBoss = rs.getLong(1);
                int fase = rs.getInt(2);
                long inimigo = rs.getLong(3);
                
                Boss boss = new Boss(0, inimigo, codigoBoss, fase);
                listaBosses.add(boss);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter registros de bosses", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return listaBosses;
    }
    
}
