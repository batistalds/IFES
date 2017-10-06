
package cadastroestoque.armazenamento;

import cadastroestoque.Entidades.ItemVenda;
import cadastroestoque.Entidades.Venda;
import java.util.ArrayList;

public class ArmazenamentoVenda {
    private static ArrayList<Venda> LISTA_VENDA;

    public static ArrayList<Venda> getLista() {
        return LISTA_VENDA;
    }
    
    public static void iniciarLista() {
        LISTA_VENDA = new ArrayList<>();
    }
    
    public static void inserir(Venda v) {
        LISTA_VENDA.add(v);
    }
    
    public static boolean alterar(Venda v) {
        Venda vendaAlterar = buscar(v);
        
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
    
    public static boolean excluir(Venda v) {
        Venda vendaExcluir = buscar(v);
        
        if (vendaExcluir != null) {
            LISTA_VENDA.remove(vendaExcluir);
            return true;
        }
        
        return false;
    }
    
    public static Venda buscar(Venda v) {
        for (Venda item : LISTA_VENDA) {
            if (item.getCodigo() == v.getCodigo()) {
                return item;
            }
        }
        
        return null;
    }
}
