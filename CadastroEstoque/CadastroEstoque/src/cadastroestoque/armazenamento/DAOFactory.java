
package cadastroestoque.armazenamento;

import cadastroestoque.armazenamento.pg.PostgreSqlDAOFactory;

public abstract class DAOFactory {
    private static final int TIPO_POSTGRE = 1;
    private static final int TIPO_MYSQL = 2;
    private static final int TIPO_ORACLE = 3;
    private static final int TIPO_APP_ENGINE = 4;
    
    public static DAOFactory getDAOFactory(int tipo) {
        switch (tipo) {
            case TIPO_POSTGRE:
                return new PostgreSqlDAOFactory();
            case TIPO_MYSQL:
            case TIPO_ORACLE:
            case TIPO_APP_ENGINE:
            default:
                return null;
        }
    }
    
    public static DAOFactory getDefaultDAOFactory() {
        return getDAOFactory(TIPO_POSTGRE);
    }
    
    public abstract ProdutoDAO getProdutoDAO();
    public abstract ClienteDAO getClienteDAO();
    public abstract FornecedorDAO getFornecedorDAO();
    public abstract FuncionarioDAO getFuncionarioDAO();
    public abstract CompraDAO getCompraDAO();
    public abstract VendaDAO getVendaDAO();
    public abstract ItemCompraDAO getItemCompraDAO();
    public abstract ItemVendaDAO getItemVendaDAO();
    
}
