
package cadastroestoque.armazenamento;

import cadastroestoque.Entidades.Entidade;
import cadastroestoque.Entidades.ItemCompra;

public class ArmazenamentoItemCompra extends Armazenamento {
    
    private static ArmazenamentoItemCompra INSTANCE;
    
    public static ArmazenamentoItemCompra getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ArmazenamentoItemCompra();
        }
        return INSTANCE;
    }
    
    @Override
    public boolean alterar(Entidade e) {
        ItemCompra ic = (ItemCompra) e;
        ItemCompra itemCompraAlterar = (ItemCompra) buscar(ic);
        
        if (itemCompraAlterar != null) {
            itemCompraAlterar.setCompra(ic.getCompra());
            itemCompraAlterar.setPrecoCompra(ic.getPrecoCompra());
            itemCompraAlterar.setProduto(ic.getProduto());
            itemCompraAlterar.setQuantidade(ic.getQuantidade());
            return true;
        }
        
        return false;
    }
}
