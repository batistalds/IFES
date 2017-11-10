
package cadastroestoque.armazenamento.pg;

import cadastroestoque.Entidades.Produto;
import cadastroestoque.armazenamento.ProdutoDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PgProdutoDAO implements ProdutoDAO {

    @Override
    public Produto buscar(Produto produto) {
        try {
            PreparedStatement ps = PostgreSqlDAOFactory.getConnection().prepareStatement(
                "SELECT codigo, nome, preco FROM produto WHERE codigo = ?");
            ps.setLong(1, produto.getCodigo());
            // Adquirindo resultado da Query após sua execução
            ResultSet rs = ps.executeQuery();
            // Pulamos para a primeira Linha resultada da Query, se houver alguma coisa, quer dizer que retornamos alguma coisa (não vazio)
            if (rs.next()) {
                long codigo = rs.getLong(1);
                String nome = rs.getString(2);
                double preco = rs.getDouble(3);
                Produto produtoEncontrado = new Produto(codigo, nome, preco);
                return produtoEncontrado;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter registro de produto", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return null;
    }

    @Override
    public boolean inserir(Produto produto) {
        try {
            PreparedStatement ps = PostgreSqlDAOFactory.getConnection().prepareStatement(
                    "INSERT INTO produto (nome, preco) VALUES (?, ?)");
            ps.setString(1, produto.getNome());
            ps.setDouble(2, produto.getPreco());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1; // Nós só estamos acrescentando uma linha à tabela, por isso, o resultado esperado para sucesso é 1
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao inserir um registro de produto", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    @Override
    public boolean alterar(Produto produto) {
        try {
            PreparedStatement ps = PostgreSqlDAOFactory.getConnection().prepareStatement(
                    "UPDATE produto SET nome = ?, preco = ? WHERE codigo = ?");
            ps.setString(1, produto.getNome());
            ps.setDouble(2, produto.getPreco());
            ps.setLong(3, produto.getCodigo());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao alterar um registro de produto", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    @Override
    public boolean excluir(Produto produto) {
        try {
            PreparedStatement ps = PostgreSqlDAOFactory.getConnection().prepareStatement(
                    "DELETE FROM produto WHERE codigo = ?");
            ps.setLong(1, produto.getCodigo());
            
            int resultadoDeLinhasAfetadas = ps.executeUpdate();
            return resultadoDeLinhasAfetadas == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir um registro de produto", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    private ArrayList<Produto> listaProdutos;
    
    @Override
    public ArrayList<Produto> getLista() {
        if (listaProdutos == null) 
            listaProdutos = new ArrayList<>();
        else 
            listaProdutos.clear();
        
        try {
            PreparedStatement ps = PostgreSqlDAOFactory.getConnection().prepareStatement(
                "SELECT codigo, nome, preco FROM produto ORDER BY nome");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long codigo = rs.getLong(1);
                String nome = rs.getString(2);
                double preco = rs.getDouble(3);
                Produto p = new Produto(codigo, nome, preco);
                listaProdutos.add(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter registros de produtos", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return listaProdutos;
    }
    
}
