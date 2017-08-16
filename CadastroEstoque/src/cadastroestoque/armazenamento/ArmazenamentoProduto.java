
package cadastroestoque.armazenamento;

import cadastroestoque.Entidades.Produto;
import java.util.ArrayList;

public class ArmazenamentoProduto {
    private static ArrayList<Produto> LISTA_PRODUTO;

    public static ArrayList<Produto> getLista() {
        return LISTA_PRODUTO;
    }
    
    public static void iniciarLista() {
        LISTA_PRODUTO = new ArrayList<>();
    }
    
    public static void inserir(Produto p) {
        LISTA_PRODUTO.add(p);
    }
    
    public static boolean alterar(Produto p) {
        Produto novoProduto = buscar(p);
        
        if (novoProduto != null) {
            novoProduto.setNome(p.getNome());
            novoProduto.setPreco(p.getPreco());
            return true;
        }
        
        return false;
    }
    
    public static boolean excluir(Produto p) {
        Produto produtoParaExcluir = buscar(p);
        
        if (produtoParaExcluir != null) {
            LISTA_PRODUTO.remove(produtoParaExcluir);
            return true;
        }
        
        return false;
    }
    
    public static Produto buscar(Produto p) {
        Produto produtoProcurado = null;
        for (Produto prod : LISTA_PRODUTO) {
            if (prod.getCodigo() == p.getCodigo()) {
                produtoProcurado = prod;
                break;
            }
        }
        return produtoProcurado;
    }
}
