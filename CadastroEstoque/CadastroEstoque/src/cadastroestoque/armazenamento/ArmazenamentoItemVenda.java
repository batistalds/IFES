
package cadastroestoque.armazenamento;
import cadastroestoque.Entidades.ItemVenda;

import java.util.ArrayList;

public class ArmazenamentoItemVenda {
    private static ArrayList<ItemVenda> LISTA_ITEM_VENDA;

    public static ArrayList<ItemVenda> getLista() {
        return LISTA_ITEM_VENDA;
    }
    
    public static void iniciarLista() {
        if (LISTA_ITEM_VENDA == null) {
            LISTA_ITEM_VENDA = new ArrayList<>();    
        } else {
            LISTA_ITEM_VENDA.clear();
        }        
    }
    
    public static void inserir(ItemVenda iv) {
        LISTA_ITEM_VENDA.add(iv);
    }
    
    public static boolean alterar(ItemVenda iv) {
        ItemVenda itemVendaAlterar = buscar(iv);
        
        if (itemVendaAlterar != null) {
            itemVendaAlterar.setVenda(iv.getVenda());
            itemVendaAlterar.setProduto(iv.getProduto());
            itemVendaAlterar.setPrecoVenda(iv.getPrecoVenda());
            itemVendaAlterar.setQuantidade(iv.getQuantidade());
            return true;
        }
        
        return false;
    }
    
    public static boolean excluir(ItemVenda iv) {
        ItemVenda itemVendaExcluir = buscar(iv);
        
        if (itemVendaExcluir != null) {
            LISTA_ITEM_VENDA.remove(itemVendaExcluir);
            return true;
        }
        
        return false;
    }
    
    public static ItemVenda buscar(ItemVenda iv) {
        for (ItemVenda item : LISTA_ITEM_VENDA) {
            if (item.getCodigo() == iv.getCodigo()) {
                return item;
            }
        }
        
        return null;
    }
}
