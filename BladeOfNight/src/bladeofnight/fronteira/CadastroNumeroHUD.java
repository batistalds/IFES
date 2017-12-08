
package bladeofnight.fronteira;

import bladeofnight.armazenamento.DAOFactory;
import bladeofnight.armazenamento.HudNumeroDAO;
import bladeofnight.entidades.HUDNumero;

public class CadastroNumeroHUD extends Cadastro {
    
    private HudNumeroDAO numDAO;
    
    public CadastroNumeroHUD() {
        numDAO = DAOFactory.getDefaultDAOFactory().getHudNumeroDAO();
    }

    @Override
    protected String obterTituloMenu() {
        return "Cadastro de Número das HUDs ^-^";
    }

    @Override
    protected String obterMensagemSairDoMenu() {
        return "Voltar ao Menu de HUDs";
    }

    @Override
    protected boolean inserir() {
        System.out.println("\nInserindo novo registro de Número para a HUD\n");

        // NÚMERO
        System.out.println("\nInforme o conteúdo desejado\n");
        System.out.print("- Número: ");
        int num = input.nextInt();
        input.nextLine(); // Consumindo quebra de linha
        
        try {
            numDAO.inserir(new HUDNumero(num));
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }

    @Override
    protected void listar() {
        System.out.println("\nListagem de Números registrados\n");
        System.out.println("+--------+--------------+");
        System.out.println("| CÓDIGO |    NÚMERO    |");
        System.out.println("+--------+--------------+");
        for (HUDNumero nn : numDAO.getLista()) {
            System.out.printf("| %6d | %12d |\n", nn.getCodigo(), nn.getNumero());
        }
        System.out.println("+--------+--------------+");
    }

    @Override
    protected boolean alterar() {
        System.out.println("\nAlterar o registro de Número de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        
        HUDNumero numNovo = new HUDNumero(codigo);
        HUDNumero numParaAlterar = numDAO.buscar(numNovo);

        if (numParaAlterar != null) {
            // NÚMERO
            int n = numParaAlterar.getNumero();
            System.out.println("\n - Número atual: " + n);
            System.out.print("---> Deseja alterar? (s = sim / n = não): ");
            char opcaoN = input.nextLine().toUpperCase().charAt(0);
            if (opcaoN == 'S') {
                System.out.print("- Informe a novo número: ");
                n = input.nextInt();
                input.nextLine(); // Consumindo quebra de linha
            } else {
                System.out.println("\nInformações do registro mantidas.");
                return false;
            }
            // CONFIRMAÇÃO FINAL
            System.out.println("\nDeseja realmente modificar os dados informados? (s = sim / n = não)");
            System.out.println("- Código: " + codigo);
            System.out.println("- Número: " + n);
            System.out.print("---> (s = sim / n = não): ");
            char confirmacaoFinal = input.nextLine().toUpperCase().charAt(0);
            if (confirmacaoFinal == 'S') {
                HUDNumero numAlterado = new HUDNumero(codigo, n);
                try {
                    numDAO.alterar(numAlterado);
                } catch (Exception e) {
                    return false;
                }
            } else {
                return false;
            }

        } else {
            System.err.println("\nNúmero não encontrado. Código inexistente.");
            return false;
        }
        
        return true;
    }

    @Override
    protected boolean excluir() {
        System.out.println("\nExcluir o registro do Número de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        
        HUDNumero numParaDeletar = numDAO.buscar(new HUDNumero(codigo));

        if (numParaDeletar == null) {
            System.err.println("Número não encontrado. Código inexistente.");
            return false;
        }

        System.out.println("\nDeseja realmente excluir o Número informado? (s = sim / n = não)");
        System.out.println("- Código: " + codigo);
        System.out.println("- Número: " + numParaDeletar.getNumero());
        System.out.print("---> (s = sim / n = não): ");
        char confirmacaoFinal = input.nextLine().toUpperCase().charAt(0);
        if (confirmacaoFinal == 'S') {
            try {
                numDAO.excluir(numParaDeletar);
            } catch (Exception e) {
                return false;
            }
        } else {
            System.out.println("\nNúmero mantido.");
            return false;
        }
        
        return true;
    }
    
    @Override
    protected boolean buscar() {
        System.out.println("\nBuscar o registro de Número de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        
        HUDNumero numNovo = new HUDNumero(codigo);
        HUDNumero numEncontrado = numDAO.buscar(numNovo);
        
        if (numEncontrado != null) {
            System.out.println("\nNúmero encontrado:\n");
            System.out.println("+--------+--------------+");
            System.out.println("| CÓDIGO |    NÚMERO    |");
            System.out.println("+--------+--------------+");
            System.out.printf("| %6d | %12d |\n", numEncontrado.getCodigo(), numEncontrado.getNumero());
            System.out.println("+--------+--------------+");
        } else {
            System.err.println("\nNúmero não encontrado. Código inexistente.");
            return false;
        }
        
        return true;
    }

    protected boolean buscarComCodigo(long codigo) {
        
        HUDNumero numNovo = new HUDNumero(codigo);
        HUDNumero numEncontrado = numDAO.buscar(numNovo);
        
        if (numEncontrado == null) {
            System.err.println("\nNúmero não encontrado. Código inexistente.");
            return false;
        }
        
        return true;
    }
    
}
