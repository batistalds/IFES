
package cadastroestoque.armazenamento;

import cadastroestoque.Entidades.Compra;
import cadastroestoque.Entidades.ItemCompra;
import java.util.ArrayList;

public class ArmazenamentoCompra {
    
    private static ArrayList<Compra> LISTA_COMPRA;

    public static ArrayList<Compra> getLista() {
        return LISTA_COMPRA;
    }
    
    public static void iniciarLista() {
        LISTA_COMPRA = new ArrayList<>();
    }
    
    public static void inserir(Compra c) {
        LISTA_COMPRA.add(c);
    }
    
    public static boolean alterar(Compra c) {
        Compra compraAlterar = buscar(c);
        
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
    
    public static boolean excluir(Compra c) {
        Compra compraExcluir = buscar(c);
        
        if (compraExcluir != null) {
            LISTA_COMPRA.remove(compraExcluir);
            return true;
        }
        
        return false;
    }
    
    public static Compra buscar(Compra c) {
        for (Compra item : LISTA_COMPRA) {
            if (item.getCodigo() == c.getCodigo()) {
                return item;
            }
        }
        
        return null;
    }
    
}
