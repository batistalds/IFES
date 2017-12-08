
package bladeofnight.fronteira;

import bladeofnight.armazenamento.DAOFactory;
import bladeofnight.armazenamento.HudTextoDAO;
import bladeofnight.entidades.HUDTexto;

public class CadastroTextoHUD extends Cadastro {
    
    private HudTextoDAO txtDAO;
    
    public CadastroTextoHUD() {
        txtDAO = DAOFactory.getDefaultDAOFactory().getHudTextoDAO();
    }

    @Override
    protected String obterTituloMenu() {
        return "Cadastro de Texto das HUDs ^-^";
    }

    @Override
    protected String obterMensagemSairDoMenu() {
        return "Voltar ao Menu de HUDs";
    }

    @Override
    protected boolean inserir() {
        System.out.println("\nInserindo novo registro de Texto para a HUD\n");
        input.nextLine(); // Consumindo quebra de linha

        // TEXTO
        System.out.println("\nInforme o conteúdo desejado\n");
        System.out.print("- Texto: ");
        String texto = input.nextLine();
        
        try {
            txtDAO.inserir(new HUDTexto(texto));
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }

    @Override
    protected void listar() {
        System.out.println("\nListagem de Textos registrados\n");
        System.out.println("+--------+-------------------------------------+");
        System.out.println("| CÓDIGO |                TEXTO                |");
        System.out.println("+--------+-------------------------------------+");
        for (HUDTexto txt : txtDAO.getLista()) {
            System.out.printf("| %6d | %35s |\n", txt.getCodigo(), txt.getTexto());
        }
        System.out.println("+--------+-------------------------------------+");
    }

    @Override
    protected boolean alterar() {
        System.out.println("\nAlterar o registro de Texto de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        
        HUDTexto txtNovo = new HUDTexto(codigo);
        HUDTexto txtParaAlterar = txtDAO.buscar(txtNovo);

        if (txtParaAlterar != null) {
            // TEXTO
            String tx = txtParaAlterar.getTexto();
            System.out.println("\n - Texto atual: " + tx);
            System.out.print("---> Deseja alterar? (s = sim / n = não): ");
            char opcaoT = input.nextLine().toUpperCase().charAt(0);
            if (opcaoT == 'S') {
                System.out.print("- Informe a novo texto: ");
                tx = input.nextLine();
            } else {
                System.out.println("\nInformações do registro mantidas.");
                return false;
            }
            // CONFIRMAÇÃO FINAL
            System.out.println("\nDeseja realmente modificar os dados informados? (s = sim / n = não)");
            System.out.println("- Código: " + codigo);
            System.out.println("- Texto.: " + tx);
            System.out.print("---> (s = sim / n = não): ");
            char confirmacaoFinal = input.nextLine().toUpperCase().charAt(0);
            if (confirmacaoFinal == 'S') {
                HUDTexto txtAlterado = new HUDTexto(codigo, tx);
                try {
                    txtDAO.alterar(txtAlterado);
                } catch (Exception e) {
                    return false;
                }
            } else {
                return false;
            }

        } else {
            System.err.println("\nTexto não encontrado. Código inexistente.");
            return false;
        }
        
        return true;
    }

    @Override
    protected boolean excluir() {
        System.out.println("\nExcluir o registro do Texto de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        
        HUDTexto txtParaDeletar = txtDAO.buscar(new HUDTexto(codigo));

        if (txtParaDeletar == null) {
            System.err.println("Texto não encontrado. Código inexistente.");
            return false;
        }

        System.out.println("\nDeseja realmente excluir o Texto informado? (s = sim / n = não)");
        System.out.println("- Código: " + codigo);
        System.out.println("- Texto.: " + txtParaDeletar.getTexto());
        System.out.print("---> (s = sim / n = não): ");
        char confirmacaoFinal = input.nextLine().toUpperCase().charAt(0);
        if (confirmacaoFinal == 'S') {
            try {
                txtDAO.excluir(txtParaDeletar);
            } catch (Exception e) {
                return false;
            }
        } else {
            System.out.println("\nTexto mantido.");
            return false;
        }
        
        return true;
    }
    
    @Override
    protected boolean buscar() {
        System.out.println("\nBuscar o registro de Texto de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        
        HUDTexto txtNovo = new HUDTexto(codigo);
        HUDTexto txtEncontrado = txtDAO.buscar(txtNovo);
        
        if (txtEncontrado != null) {
            System.out.println("\nTexto encontrado:\n");
            System.out.println("+--------+-------------------------------------+");
            System.out.println("| CÓDIGO |                TEXTO                |");
            System.out.println("+--------+-------------------------------------+");
            System.out.printf("| %6d | %35s |\n", txtEncontrado.getCodigo(), txtEncontrado.getTexto());
            System.out.println("+--------+-------------------------------------+");
        } else {
            System.err.println("\nTexto não encontrado. Código inexistente.");
            return false;
        }
        
        return true;
    }
    
    protected boolean buscarComCodigo(long codigo) {
        
        HUDTexto txtNovo = new HUDTexto(codigo);
        HUDTexto txtEncontrado = txtDAO.buscar(txtNovo);
        
        if (txtEncontrado == null) {
            System.err.println("\nTexto não encontrado. Código inexistente.");
            return false;
        }
        
        return true;
    }
    
}
