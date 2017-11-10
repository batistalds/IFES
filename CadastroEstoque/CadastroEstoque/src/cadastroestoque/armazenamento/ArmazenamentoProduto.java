package cadastroestoque.armazenamento;

import cadastroestoque.Entidades.Entidade;
import cadastroestoque.Entidades.Produto;

public class ArmazenamentoProduto extends Armazenamento {

    private static ArmazenamentoProduto INSTANCE;
    
    public static ArmazenamentoProduto getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ArmazenamentoProduto();
        }
        return INSTANCE;
    }
    
    @Override
    public boolean alterar(Entidade e) {
        Produto pr = (Produto) e;
        Produto novoProduto = (Produto) buscar(pr);

        if (novoProduto != null) {
            novoProduto.setNome(pr.getNome());
            novoProduto.setPreco(pr.getPreco());
            return true;
        }

        return false;
    }
}
