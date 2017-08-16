
package cadastroestoque;

import cadastroestoque.armazenamento.ArmazenamentoProduto;
import controleestoque.fronteira.MenuPrincipal;

public class CadastroEstoque {
    
    public static void main(String[] args) {
        // Inicialização de dados
        ArmazenamentoProduto.iniciarLista();
        
        MenuPrincipal meuMenu = new MenuPrincipal();
        meuMenu.exibirMenu();
    }    
}
