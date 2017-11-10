
package cadastroestoque.armazenamento;
import cadastroestoque.Entidades.Entidade;
import cadastroestoque.Entidades.ItemVenda;

public class ArmazenamentoItemVenda extends Armazenamento {
    
    private static ArmazenamentoItemCompra INSTANCE;
    
    public static ArmazenamentoItemCompra getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ArmazenamentoItemCompra();
        }
        return INSTANCE;
    }
    
    @Override    
    public boolean alterar(Entidade e) {
        ItemVenda iv = (ItemVenda) e;
        ItemVenda itemVendaAlterar = (ItemVenda) buscar(iv);
        
        if (itemVendaAlterar != null) {
            itemVendaAlterar.setVenda(iv.getVenda());
            itemVendaAlterar.setProduto(iv.getProduto());
            itemVendaAlterar.setPrecoVenda(iv.getPrecoVenda());
            itemVendaAlterar.setQuantidade(iv.getQuantidade());
            return true;
        }
        
        return false;
    }
}
