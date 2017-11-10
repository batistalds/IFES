
package cadastroestoque.armazenamento;

import cadastroestoque.Entidades.Compra;
import cadastroestoque.Entidades.Entidade;
import cadastroestoque.Entidades.ItemCompra;
import java.util.ArrayList;

public class ArmazenamentoCompra extends Armazenamento {
    
    private static ArmazenamentoCompra INSTANCE;
    
    public static ArmazenamentoCompra getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ArmazenamentoCompra();
        }
        return INSTANCE;
    }
    
    @Override
    public boolean alterar(Entidade e) {
        Compra c = (Compra) e;
        Compra compraAlterar = (Compra) buscar(c);
        
        if (compraAlterar != null) {
            compraAlterar.setComprador(c.getComprador());
            compraAlterar.setData(c.getData());
            compraAlterar.setForncedor(c.getForncedor());
            compraAlterar.setValorTotal(c.getValorTotal());
            ArrayList<ItemCompra> itenCompra = compraAlterar.getItensCompra();
            itenCompra.clear();
            itenCompra.addAll(compraAlterar.getItensCompra());
            return true;
        }
        
        return false;
    }
    
}
