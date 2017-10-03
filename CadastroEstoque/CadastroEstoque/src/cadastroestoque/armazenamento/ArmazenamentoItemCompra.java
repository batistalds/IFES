
package cadastroestoque.armazenamento;

import cadastroestoque.Entidades.ItemCompra;
import java.util.ArrayList;

public class ArmazenamentoItemCompra {
    
    private static ArrayList<ItemCompra> LISTA_ITEM_COMPRA;

    public static ArrayList<ItemCompra> getLista() {
        return LISTA_ITEM_COMPRA;
    }
    
    public static void iniciarLista() {
        if (LISTA_ITEM_COMPRA == null) {
            LISTA_ITEM_COMPRA = new ArrayList<>();    
        } else {
            LISTA_ITEM_COMPRA.clear();
        }        
    }
    
    public static void inserir(ItemCompra ic) {
        LISTA_ITEM_COMPRA.add(ic);
    }
    
    public static boolean alterar(ItemCompra ic) {
        ItemCompra itemCompraAlterar = buscar(ic);
        
        if (itemCompraAlterar != null) {
            itemCompraAlterar.setCompra(ic.getCompra());
            itemCompraAlterar.setPrecoCompra(ic.getPrecoCompra());
            itemCompraAlterar.setProduto(ic.getProduto());
            itemCompraAlterar.setQuantidade(ic.getQuantidade());
            return true;
        }
        
        return false;
    }
    
    public static boolean excluir(ItemCompra ic) {
        ItemCompra itemCompraExcluir = buscar(ic);
        
        if (itemCompraExcluir != null) {
            LISTA_ITEM_COMPRA.remove(itemCompraExcluir);
            return true;
        }
        
        return false;
    }
    
    public static ItemCompra buscar(ItemCompra ic) {
        for (ItemCompra item : LISTA_ITEM_COMPRA) {
            if (item.getCodigo() == ic.getCodigo()) {
                return item;
            }
        }
        
        return null;
    }
}
