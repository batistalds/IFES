package cadastroestoque;

import cadastroestoque.armazenamento.ArmazenamentoCliente;
import cadastroestoque.armazenamento.ArmazenamentoCompra;
import cadastroestoque.armazenamento.ArmazenamentoFornecedor;
import cadastroestoque.armazenamento.ArmazenamentoFuncionario;
import cadastroestoque.armazenamento.ArmazenamentoProduto;
import cadastroestoque.armazenamento.ArmazenamentoVenda;
import controleestoque.fronteira.MenuPrincipal;

public class CadastroEstoque {

    public static void main(String[] args) {
        // Inicialização de dados
        ArmazenamentoProduto.iniciarLista();
        ArmazenamentoFornecedor.iniciarLista();
        ArmazenamentoCliente.iniciarLista();
        ArmazenamentoFuncionario.iniciarLista();
        ArmazenamentoCompra.iniciarLista();
        ArmazenamentoVenda.iniciarLista();

        MenuPrincipal meuMenu = new MenuPrincipal();
        meuMenu.exibirMenu();
    }
}
