
package cadastroestoque.armazenamento.pg;

import cadastroestoque.armazenamento.ClienteDAO;
import cadastroestoque.armazenamento.CompraDAO;
import cadastroestoque.armazenamento.DAOFactory;
import cadastroestoque.armazenamento.FornecedorDAO;
import cadastroestoque.armazenamento.FuncionarioDAO;
import cadastroestoque.armazenamento.ItemCompraDAO;
import cadastroestoque.armazenamento.ItemVendaDAO;
import cadastroestoque.armazenamento.ProdutoDAO;
import cadastroestoque.armazenamento.VendaDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class PostgreSqlDAOFactory extends DAOFactory {
    private static Connection con;
    
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Estoque";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";
    
    public static Connection getConnection() {
        if (con == null) {
            try {
                Class.forName("org.postgresql.Driver");
                con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível carregar o conector para o PostgreSQL.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        return con;
    }

    @Override
    public ProdutoDAO getProdutoDAO() {
        return new PgProdutoDAO();
    }

    @Override
    public ClienteDAO getClienteDAO() {
        return new PgClienteDAO();
    }

    @Override
    public FornecedorDAO getFornecedorDAO() {
        return new PgFornecedorDAO();
    }

    @Override
    public FuncionarioDAO getFuncionarioDAO() {
        return new PgFuncionarioDAO();
    }

    @Override
    public CompraDAO getCompraDAO() {
        return new PgCompraDAO();
    }

    @Override
    public VendaDAO getVendaDAO() {
        return new PgVendaDAO();
    }

    @Override
    public ItemCompraDAO getItemCompraDAO() {
        return new PgItemCompraDAO();
    }

    @Override
    public ItemVendaDAO getItemVendaDAO() {
        return new PgItemVendaDAO();
    }
    
}
