
package bladeofnight.fronteira;

import bladeofnight.armazenamento.DAOFactory;
import bladeofnight.armazenamento.HudPrioridadeDAO;
import bladeofnight.entidades.HUDPrioridade;
import java.util.ArrayList;

public class CadastroPrioridadeHUD extends Cadastro {
    
    private HudPrioridadeDAO prioridadeDAO;
    
    public CadastroPrioridadeHUD() {
        prioridadeDAO = DAOFactory.getDefaultDAOFactory().getHudPrioridadeDAO();
    }

    @Override
    protected String obterTituloMenu() {
        return "Cadastro de Prioridade das HUDs ^-^";
    }

    @Override
    protected String obterMensagemSairDoMenu() {
        return "Voltar ao Menu de HUDs";
    }

    @Override
    protected boolean inserir() {
        System.out.println("\nInserindo novo registro de prioridade\n");
        
        // VALOR DA PRIORIDADE
        // Único valor a ser adicionado a esta classe. Ele deve ser único, não pode repetir. Portanto será preenchido automaticamente.
        System.out.println("\nInforme o valor do registro de prioridade desejado\n");
        System.out.print("- Valor da Prioridade: ");
        int valorPrioridade = input.nextInt();
        input.nextLine(); // Consumindo quebra de linha
        ArrayList<HUDPrioridade> hP = prioridadeDAO.getLista();
        
        for (HUDPrioridade item : hP) {
            if (item.getValorPrioridade() == valorPrioridade) {
                System.err.println("\nValor de prioridade informado já existe. Ele deve ser único.");
                return false;
            }
        }
        
        try {
            prioridadeDAO.inserir(new HUDPrioridade(valorPrioridade));
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }

    @Override
    protected void listar() {
        System.out.println("\nListagem de Prioridades registradas\n");
        System.out.println("+--------+------------------+");
        System.out.println("| CÓDIGO | VALOR PRIORIDADE |");
        System.out.println("+--------+------------------+");
        for (HUDPrioridade pri : prioridadeDAO.getLista()) {
            System.out.printf("| %6d | %16d |\n", pri.getCodigo(), pri.getValorPrioridade());
        }
        System.out.println("+--------+------------------+");
    }

    @Override
    protected boolean alterar() {
        System.out.println("\nAlterar o registro de Prioridade de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        
        HUDPrioridade priNovo = new HUDPrioridade(codigo);
        HUDPrioridade priParaAlterar = prioridadeDAO.buscar(priNovo);

        if (priParaAlterar != null) {
            // VALOR DA PRIORIDADE
            int vP = priParaAlterar.getValorPrioridade();
            System.out.println("\n - Valor da Prioridade atual: " + Integer.toString(vP));
            System.out.print("---> Deseja alterar? (s = sim / n = não): ");
            char opcaoVP = input.nextLine().toUpperCase().charAt(0);
            if (opcaoVP == 'S') {
                System.out.print("- Informe a novo valor da prioridade: ");
                vP = input.nextInt();
                input.nextLine(); // Consumindo quebra de linha
                
                ArrayList<HUDPrioridade> hP = prioridadeDAO.getLista();
                for (HUDPrioridade item : hP) {
                    if (item.getValorPrioridade() == vP) {
                        System.err.println("\nValor de prioridade informado já existe. Ele deve ser único.");
                        return false;
                    }
                }
            } else {
                System.out.println("\nInformações do registro mantidas.");
                return false;
            }
            // CONFIRMAÇÃO FINAL
            System.out.println("\nDeseja realmente modificar os dados informados? (s = sim / n = não)");
            System.out.println("- Código.............: " + priParaAlterar.getCodigo());
            System.out.println("- Valor da Prioridade: " + vP);
            System.out.print("---> (s = sim / n = não): ");
            char confirmacaoFinal = input.nextLine().toUpperCase().charAt(0);
            if (confirmacaoFinal == 'S') {
                HUDPrioridade priAlterado = new HUDPrioridade(codigo, vP);
                try {
                    prioridadeDAO.alterar(priAlterado);
                } catch (Exception e) {
                    return false;
                }
            } else {
                return false;
            }

        } else {
            System.err.println("\nBackground não encontrado. Código inexistente.");
            return false;
        }
        
        return true;
    }

    @Override
    protected boolean excluir() {
        System.out.println("\nExcluir o registro do Prioridade de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        
        HUDPrioridade priParaDeletar = prioridadeDAO.buscar(new HUDPrioridade(codigo));

        if (priParaDeletar == null) {
            System.err.println("Prioridade não encontrada. Código inexistente.");
            return false;
        }

        System.out.println("\nDeseja realmente excluir a Prioridade informada? (s = sim / n = não)");
        System.out.println("- Código.............: " + codigo);
        System.out.println("- Valor da Prioridade: " + priParaDeletar.getValorPrioridade());
        System.out.print("---> (s = sim / n = não): ");
        char confirmacaoFinal = input.nextLine().toUpperCase().charAt(0);
        if (confirmacaoFinal == 'S') {
            try {
                prioridadeDAO.excluir(priParaDeletar);
            } catch (Exception e) {
                return false;
            }
        } else {
            System.out.println("\nPrioridade mantida.");
            return false;
        }
        
        return true;
    }
    
    @Override
    protected boolean buscar() {
        System.out.println("\nBuscar o registro de Prioridade de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        
        HUDPrioridade priNovo = new HUDPrioridade(codigo);
        HUDPrioridade priEncontrado = prioridadeDAO.buscar(priNovo);
        
        if (priEncontrado != null) {
            System.out.println("\nPrioridade encontrada:\n");
            System.out.println("+--------+------------------+");
            System.out.println("| CÓDIGO | VALOR PRIORIDADE |");
            System.out.println("+--------+------------------+");
            System.out.printf("| %6d | %16s |\n", priEncontrado.getCodigo(), priEncontrado.getValorPrioridade());
            System.out.println("+--------+------------------+");
        } else {
            System.err.println("\nPrioridade não encontrada. Código inexistente.");
            return false;
        }
        
        return true;
    }
    
    protected boolean buscarComCodigo(long codigo) {
        
        HUDPrioridade priNovo = new HUDPrioridade(codigo);
        HUDPrioridade priEncontrado = prioridadeDAO.buscar(priNovo);
        
        if (priEncontrado == null) {
            System.err.println("\nPrioridade não encontrada. Código inexistente.");
            return false;
        }
        
        return true;
    }
    
}
