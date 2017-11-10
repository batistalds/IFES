
package cadastroestoque.armazenamento.pg;

import cadastroestoque.armazenamento.DAOFactory;
import java.sql.Connection;

public class PgDAOFactory extends DAOFactory {
    private Connection con;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/estoque";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";
}
