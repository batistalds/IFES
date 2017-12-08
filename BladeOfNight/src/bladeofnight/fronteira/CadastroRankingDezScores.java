
package bladeofnight.fronteira;

import bladeofnight.armazenamento.DAOFactory;
import bladeofnight.armazenamento.RankingDezScoresDAO;
import bladeofnight.entidades.RankingDezScores;

public class CadastroRankingDezScores extends Cadastro {
    
    private RankingDezScoresDAO rdsDAO;
    
    public CadastroRankingDezScores() {
        rdsDAO = DAOFactory.getDefaultDAOFactory().getRankingDezScoresDAO();
    }

    @Override
    protected String obterTituloMenu() {
        return "Cadastro de Scores (Pontuações) para o Top 10 do Ranking ^-^";
    }

    @Override
    protected String obterMensagemSairDoMenu() {
        return "Voltar ao Menu de Ranking";
    }

    @Override
    protected boolean inserir() {
        System.out.println("\nInserindo novo registro de Scores (Pontuações) para o Top 10 Ranking\n");

        // NOME PLAYER
        System.out.println("\nInforme o conteúdo desejado\n");
        System.out.print("- Pontuação do Jogador: ");
        long pontuacaoPlayer = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        
        try {
            rdsDAO.inserir(new RankingDezScores(0, pontuacaoPlayer));
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }

    @Override
    protected void listar() {
        System.out.println("\nListagem de Scores (Pontuações) do Top 10 do Ranking\n");
        System.out.println("+--------+-----------------------+");
        System.out.println("| CÓDIGO |   PONTUAÇÃO JOGADOR   |");
        System.out.println("+--------+-----------------------+");
        for (RankingDezScores rds : rdsDAO.getLista()) {
            System.out.printf("| %6d | %21d |\n", rds.getCodigo(), rds.getPontuacaoPlayer());
        }
        System.out.println("+--------+-----------------------+");
    }

    @Override
    protected boolean alterar() {
        System.out.println("\nAlterar o registro de Scores (Pontuações) do Ranking de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        
        RankingDezScores rdsNovo = new RankingDezScores(codigo);
        RankingDezScores rdsParaAlterar = rdsDAO.buscar(rdsNovo);

        if (rdsParaAlterar != null) {
            // PONTUAÇÃO
            long pontuacao = rdsParaAlterar.getPontuacaoPlayer();
            System.out.println("\n - Pontuação atual: " + pontuacao);
            System.out.print("---> Deseja alterar? (s = sim / n = não): ");
            char opcaoPont = input.nextLine().toUpperCase().charAt(0);
            if (opcaoPont == 'S') {
                System.out.print("- Informe a nova pontução: ");
                pontuacao = input.nextLong();
                input.nextLine(); // Consumindo quebra de linha
            } else {
                System.out.println("\nInformações do registro mantidas.");
                return false;
            }
            // CONFIRMAÇÃO FINAL
            System.out.println("\nDeseja realmente modificar os dados informados? (s = sim / n = não)");
            System.out.println("- Código: " + codigo);
            System.out.println("- Pontuação: " + pontuacao);
            System.out.print("---> (s = sim / n = não): ");
            char confirmacaoFinal = input.nextLine().toUpperCase().charAt(0);
            if (confirmacaoFinal == 'S') {
                RankingDezScores rdsAlterado = new RankingDezScores(codigo, pontuacao);
                try {
                    rdsDAO.alterar(rdsAlterado);
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
        System.out.println("\nExcluir o registro do Scores (Pontuações) do Ranking de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        
        RankingDezScores rdsParaDeletar = rdsDAO.buscar(new RankingDezScores(codigo));

        if (rdsParaDeletar == null) {
            System.err.println("Score não encontrado. Código inexistente.");
            return false;
        }

        System.out.println("\nDeseja realmente excluir o score informado do Ranking? (s = sim / n = não)");
        System.out.println("- Código: " + codigo);
        System.out.println("- Pontuação: " + rdsParaDeletar.getPontuacaoPlayer());
        System.out.print("---> (s = sim / n = não): ");
        char confirmacaoFinal = input.nextLine().toUpperCase().charAt(0);
        if (confirmacaoFinal == 'S') {
            try {
                rdsDAO.excluir(rdsParaDeletar);
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
        System.out.println("\nBuscar o registro de Score (Pontuação) no Top 10 do Ranking de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        
        RankingDezScores rddNovo = new RankingDezScores(codigo);
        RankingDezScores rddEncontrado = rdsDAO.buscar(rddNovo);
        
        if (rddEncontrado != null) {
            System.out.println("\nRegistro encontrado:\n");
            System.out.println("+--------+-----------------------+");
            System.out.println("| CÓDIGO |   PONTUAÇÃO JOGADOR   |");
            System.out.println("+--------+-----------------------+");
            System.out.printf("| %6d | %21d |\n", rddEncontrado.getCodigo(), rddEncontrado.getPontuacaoPlayer());
            System.out.println("+--------+-----------------------+");
        } else {
            System.err.println("\nScore não encontrado. Código inexistente.");
            return false;
        }
        
        return true;
    }
    
}
