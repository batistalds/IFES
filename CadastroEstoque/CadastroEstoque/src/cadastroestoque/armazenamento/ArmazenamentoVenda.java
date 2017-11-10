
package cadastroestoque.armazenamento;

import cadastroestoque.Entidades.Entidade;
import cadastroestoque.Entidades.ItemVenda;
import cadastroestoque.Entidades.Venda;
import java.util.ArrayList;

public class ArmazenamentoVenda extends Armazenamento {
    
    private static ArmazenamentoVenda INSTANCE;
    
    public static ArmazenamentoVenda getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ArmazenamentoVenda();
        }
        return INSTANCE;
    }
    
    @Override
    public boolean alterar(Entidade e) {
        Venda v = (Venda) e;
        Venda vendaAlterar = (Venda) buscar(v);
        
        if (vendaAlterar != null) {
            vendaAlterar.setCliente(v.getCliente());
            vendaAlterar.setData(v.getData());
            vendaAlterar.setValorTotal(v.getValorTotal());
            vendaAlterar.setVendedor(v.getVendedor());
            ArrayList<ItemVenda> itensVenda = vendaAlterar.getItensVenda();
            itensVenda.clear();
            itensVenda.addAll(vendaAlterar.getItensVenda());
            return true;
        }
        
        return false;
    }
}
