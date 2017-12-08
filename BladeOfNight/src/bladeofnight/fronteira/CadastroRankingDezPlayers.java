
package bladeofnight.fronteira;

import bladeofnight.armazenamento.DAOFactory;
import bladeofnight.armazenamento.RankingDezPlayersDAO;
import bladeofnight.entidades.RankingDezPlayers;

public class CadastroRankingDezPlayers extends Cadastro {
    
    private RankingDezPlayersDAO rdpDAO;
    
    public CadastroRankingDezPlayers() {
        rdpDAO = DAOFactory.getDefaultDAOFactory().getRankingDezPlayersDAO();
    }

    @Override
    protected String obterTituloMenu() {
        return "Cadastro de Player para o Top 10 do Ranking ^-^";
    }

    @Override
    protected String obterMensagemSairDoMenu() {
        return "Voltar ao Menu de Ranking";
    }

    @Override
    protected boolean inserir() {
        System.out.println("\nInserindo novo registro de Player para o Top 10 Ranking\n");
        input.nextLine(); // Consumindo quebra de linha
        
        // NOME PLAYER
        System.out.println("\nInforme o conteúdo desejado\n");
        System.out.print("- Nome do Jogador: ");
        String nomePlayer = input.nextLine();
        
        try {
            rdpDAO.inserir(new RankingDezPlayers(nomePlayer));
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }

    @Override
    protected void listar() {
        System.out.println("\nListagem de Players do Top 10 do Ranking\n");
        System.out.println("+--------+------------------+");
        System.out.println("| CÓDIGO |   NOME JOGADOR   |");
        System.out.println("+--------+------------------+");
        for (RankingDezPlayers rdp : rdpDAO.getLista()) {
            System.out.printf("| %6d | %16s |\n", rdp.getCodigo(), rdp.getNomePlayer());
        }
        System.out.println("+--------+------------------+");
    }

    @Override
    protected boolean alterar() {
        System.out.println("\nAlterar o registro do Player do Ranking de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        
        RankingDezPlayers rdpNovo = new RankingDezPlayers(codigo);
        RankingDezPlayers rdpParaAlterar = rdpDAO.buscar(rdpNovo);

        if (rdpParaAlterar != null) {
            // NOME
            String nP = rdpParaAlterar.getNomePlayer();
            System.out.println("\n - Nome atual: " + nP);
            System.out.print("---> Deseja alterar? (s = sim / n = não): ");
            char opcaoN = input.nextLine().toUpperCase().charAt(0);
            if (opcaoN == 'S') {
                System.out.print("- Informe o novo Player: ");
                nP = input.nextLine();
            } else {
                System.out.println("\nInformações do registro mantidas.");
                return false;
            }
            // CONFIRMAÇÃO FINAL
            System.out.println("\nDeseja realmente modificar os dados informados? (s = sim / n = não)");
            System.out.println("- Código: " + codigo);
            System.out.println("- Nome: " + nP);
            System.out.print("---> (s = sim / n = não): ");
            char confirmacaoFinal = input.nextLine().toUpperCase().charAt(0);
            if (confirmacaoFinal == 'S') {
                RankingDezPlayers rdpAlterado = new RankingDezPlayers(codigo, nP);
                try {
                    rdpDAO.alterar(rdpAlterado);
                } catch (Exception e) {
                    return false;
                }
            } else {
                return false;
            }

        } else {
            System.err.println("\nRegistro não encontrado. Código inexistente.");
            return false;
        }
        
        return true;
    }

    @Override
    protected boolean excluir() {
        System.out.println("\nExcluir o registro do Player do Ranking de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        
        RankingDezPlayers rdpParaDeletar = rdpDAO.buscar(new RankingDezPlayers(codigo));

        if (rdpParaDeletar == null) {
            System.err.println("Player não encontrado. Código inexistente.");
            return false;
        }

        System.out.println("\nDeseja realmente excluir o jogador informado do Ranking? (s = sim / n = não)");
        System.out.println("- Código: " + codigo);
        System.out.println("- Nome: " + rdpParaDeletar.getNomePlayer());
        System.out.print("---> (s = sim / n = não): ");
        char confirmacaoFinal = input.nextLine().toUpperCase().charAt(0);
        if (confirmacaoFinal == 'S') {
            try {
                rdpDAO.excluir(rdpParaDeletar);
            } catch (Exception e) {
                return false;
            }
        } else {
            System.out.println("\nRegistro mantido.");
            return false;
        }
        
        return true;
    }
    
    @Override
    protected boolean buscar() {
        System.out.println("\nBuscar o registro do Player no Top 10 do Ranking de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        
        RankingDezPlayers rdpNovo = new RankingDezPlayers(codigo);
        RankingDezPlayers rdpEncontrado = rdpDAO.buscar(rdpNovo);
        
        if (rdpEncontrado != null) {
            System.out.println("\nRegistro encontrado:\n");
            System.out.println("+--------+------------------+");
            System.out.println("| CÓDIGO |   NOME JOGADOR   |");
            System.out.println("+--------+------------------+");
            System.out.printf("| %6d | %16s |\n", rdpEncontrado.getCodigo(), rdpEncontrado.getNomePlayer());
            System.out.println("+--------+------------------+");
        } else {
            System.err.println("\nPlayer não encontrado. Código inexistente.");
            return false;
        }
        
        return true;
    }
    
}
