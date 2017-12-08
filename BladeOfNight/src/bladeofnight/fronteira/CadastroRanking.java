
package bladeofnight.fronteira;

import bladeofnight.armazenamento.BackgroundDAO;
import bladeofnight.armazenamento.DAOFactory;
import bladeofnight.armazenamento.HudDAO;
import bladeofnight.armazenamento.RankingDezPlayersDAO;
import bladeofnight.armazenamento.RankingDezScoresDAO;
import bladeofnight.armazenamento.RankingDezDatasDAO;
import bladeofnight.armazenamento.RankingDAO;
import bladeofnight.entidades.Background;
import bladeofnight.entidades.HUD;
import bladeofnight.entidades.RankingDezPlayers;
import bladeofnight.entidades.RankingDezScores;
import bladeofnight.entidades.RankingDezDatas;
import bladeofnight.entidades.Ranking;

public class CadastroRanking extends Cadastro {
    
    private RankingDAO rankDAO;
    private RankingDezPlayersDAO rankDezPlaDAO;
    private RankingDezScoresDAO rankDezScoDAO;
    private RankingDezDatasDAO rankDezDatDAO;
    private BackgroundDAO backDAO;
    private HudDAO hudDAO;
    
    public CadastroRanking() {
        rankDAO = DAOFactory.getDefaultDAOFactory().getRankingDAO();
        rankDezPlaDAO = DAOFactory.getDefaultDAOFactory().getRankingDezPlayersDAO();
        rankDezScoDAO = DAOFactory.getDefaultDAOFactory().getRankingDezScoresDAO();
        rankDezDatDAO = DAOFactory.getDefaultDAOFactory().getRankingDezDatasDAO();
        backDAO = DAOFactory.getDefaultDAOFactory().getBackgroundDAO();
        hudDAO = DAOFactory.getDefaultDAOFactory().getHudDAO();
    }

    @Override
    protected String obterTituloMenu() {
        return "Cadastro de Ranking ^-^";
    }

    @Override
    protected String obterMensagemSairDoMenu() {
        return "Voltar ao Menu de Ranking";
    }

    @Override
    protected boolean inserir() {
        System.out.println("\nInserindo novo registro de Ranking\n");

        // 10 Primeiros Players
        CadastroRankingDezPlayers crdp = new CadastroRankingDezPlayers();
        crdp.listar();
        System.out.print("- Informe o código do Player (Jogador): ");
        int codPlayer = input.nextInt();
        input.nextLine(); // Consumindo quebra de linha
        RankingDezPlayers playerExiste = rankDezPlaDAO.buscar(new RankingDezPlayers(codPlayer));
        if (playerExiste == null) {
            System.err.println("\nPlayer inserido não existe no Ranking de Jogadores. Código inexistente.");
            return false;
        }
        
        // 10 Primeiros Scores
        CadastroRankingDezScores crds = new CadastroRankingDezScores();
        crds.listar();
        System.out.print("- Informe o código do Score (Pontuação): ");
        int codScore = input.nextInt();
        input.nextLine(); // Consumindo quebra de linha
        RankingDezScores scoreExiste = rankDezScoDAO.buscar(new RankingDezScores(codScore));
        if (scoreExiste == null) {
            System.err.println("\nScore inserido não existe no Ranking de Pontuações. Código inexistente.");
            return false;
        }
        
        // 10 Primeiras Datas
        CadastroRankingDezDatas crdd = new CadastroRankingDezDatas();
        crdd.listar();
        System.out.print("- Informe o código da Data: ");
        int codData = input.nextInt();
        input.nextLine(); // Consumindo quebra de linha
        RankingDezDatas dataExiste = rankDezDatDAO.buscar(new RankingDezDatas(codData));
        if (dataExiste == null) {
            System.err.println("\nData inserida não existe no Ranking de Datas. Código inexistente.");
            return false;
        }
        
        // IMAGEM FUNDO
        CadastroBackground cb = new CadastroBackground();
        cb.listar();
        System.out.print("- Informe o código do Background (Plano de Fundo): ");
        int codBack = input.nextInt();
        input.nextLine(); // Consumindo quebra de linha
        Background backExiste = backDAO.buscar(new Background(codBack));
        if (backExiste == null) {
            System.err.println("\nBackground inserido não existe. Código inexistente.");
            return false;
        }
        
        // HUD
        CadastroHUD ch = new CadastroHUD();
        ch.listar();
        System.out.print("- Informe o código da HUD (Interface Gráfica): ");
        int codHud = input.nextInt();
        input.nextLine(); // Consumindo quebra de linha
        HUD hudExiste = hudDAO.buscar(new HUD(codHud));
        if (hudExiste == null) {
            System.err.println("\nHUD inserida não existe. Código inexistente.");
            return false;
        }

        Ranking novoRanking = new Ranking(codPlayer, codScore, codData, codBack, codHud);
        
        try {
            rankDAO.inserir(novoRanking);
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }

    @Override
    protected void listar() {
        System.out.println("\nListagem de Ranking registrados\n");
        System.out.println("+--------+------------------+----------------+---------------+------------+-----+");
        System.out.println("| CÓDIGO | TOP 10 - PLAYERS | TOP 10 - SCORE | TOP 10 - DATA | BACKGROUND | HUD |");
        System.out.println("+--------+------------------+----------------+---------------+------------+-----+");
        for (Ranking rank : rankDAO.getLista()) {
            System.out.printf("| %6d | %16d | %14d | %13d | %10d | %3d |\n", rank.getCodigo(), rank.getDezPrimeirosPlayers(), rank.getDezPrimeirosScores(), 
                                                                            rank.getDezPrimeirasDatas(), rank.getImagemFundo(), rank.getInterfaceHud());
        }
        System.out.println("+--------+------------------+----------------+---------------+------------+-----+");
    }

    @Override
    protected boolean alterar() {
        System.out.println("\nAlterar o registro de Ranking de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        
        Ranking rankNovo = new Ranking(codigo);
        Ranking rankParaAlterar = rankDAO.buscar(rankNovo);

        if (rankParaAlterar != null) {
            // 10 Primeiros Players
            CadastroRankingDezPlayers crdp = new CadastroRankingDezPlayers();
            crdp.listar();
            int codPlayer = rankParaAlterar.getDezPrimeirosPlayers();
            System.out.println("\n - Código do Ranking de Players atual: " + codPlayer);
            System.out.print("---> Deseja alterar? (s = sim / n = não): ");
            char opcaoRp = input.nextLine().toUpperCase().charAt(0);
            if (opcaoRp == 'S') {
                System.out.print("- Informe um novo código de Ranking de Players: ");
                codPlayer = input.nextInt();
                input.nextLine(); // Consumindo quebra de linha
                RankingDezPlayers playerExiste = rankDezPlaDAO.buscar(new RankingDezPlayers(codPlayer));
                if (playerExiste == null) {
                    System.err.println("\nPlayer inserido não existe no Ranking de Jogadores. Código inexistente.");
                    return false;
                }
            }

            // 10 Primeiros Scores
            CadastroRankingDezScores crds = new CadastroRankingDezScores();
            crds.listar();
            int codScore = rankParaAlterar.getDezPrimeirosScores();
            System.out.println("\n - Código do Ranking de Scores atual: " + codScore);
            System.out.print("---> Deseja alterar? (s = sim / n = não): ");
            char opcaoRs = input.nextLine().toUpperCase().charAt(0);
            if (opcaoRs == 'S') {
                System.out.print("- Informe um novo código de Ranking de Scores: ");
                codScore = input.nextInt();
                input.nextLine(); // Consumindo quebra de linha
                RankingDezScores scoreExiste = rankDezScoDAO.buscar(new RankingDezScores(codScore));
                if (scoreExiste == null) {
                    System.err.println("\nScore inserido não existe no Ranking de Pontuações. Código inexistente.");
                    return false;
                }
            }
            
            // 10 Primeiras Datas
            CadastroRankingDezDatas crdd = new CadastroRankingDezDatas();
            crdd.listar();
            int codData = rankParaAlterar.getDezPrimeirasDatas();
            System.out.println("\n - Código do Ranking de Datas atual: " + codData);
            System.out.print("---> Deseja alterar? (s = sim / n = não): ");
            char opcaoRd = input.nextLine().toUpperCase().charAt(0);
            if (opcaoRd == 'S') {
                System.out.print("- Informe um novo código de Ranking de Datas: ");
                codData = input.nextInt();
                input.nextLine(); // Consumindo quebra de linha
                RankingDezDatas dataExiste = rankDezDatDAO.buscar(new RankingDezDatas(codData));
                if (dataExiste == null) {
                    System.err.println("\nData inserida não existe no Ranking de Datas. Código inexistente.");
                    return false;
                }
            }
            
            // IMAGEM FUNDO
            CadastroBackground cb = new CadastroBackground();
            cb.listar();
            int codBack = rankParaAlterar.getImagemFundo();
            System.out.println("\n - Código do Background atual: " + codBack);
            System.out.print("---> Deseja alterar? (s = sim / n = não): ");
            char opcaoBack = input.nextLine().toUpperCase().charAt(0);
            if (opcaoBack == 'S') {
                System.out.print("- Informe um novo código de Background: ");
                codBack = input.nextInt();
                input.nextLine(); // Consumindo quebra de linha
                Background backExiste = backDAO.buscar(new Background(codBack));
                if (backExiste == null) {
                    System.err.println("\nBackground inserido não existe. Código inexistente.");
                    return false;
                }
            }
            
            // HUD
            CadastroHUD ch = new CadastroHUD();
            ch.listar();
            int codHud = rankParaAlterar.getInterfaceHud();
            System.out.println("\n - Código da HUD atual: " + codHud);
            System.out.print("---> Deseja alterar? (s = sim / n = não): ");
            char opcaoHud = input.nextLine().toUpperCase().charAt(0);
            if (opcaoHud == 'S') {
                System.out.print("- Informe um novo código de HUD: ");
                codHud = input.nextInt();
                input.nextLine(); // Consumindo quebra de linha
                HUD hudExiste = hudDAO.buscar(new HUD(codHud));
                if (hudExiste == null) {
                    System.err.println("\nHUD inserida não existe. Código inexistente.");
                    return false;
                }
            }
            
            // CONFIRMAÇÃO FINAL
            System.out.println("\nDeseja realmente modificar os dados informados? (s = sim / n = não)");
            System.out.println("- Código................: " + codigo);
            System.out.println("- Código Ranking Players: " + codPlayer);
            System.out.println("- Código Ranking Scores.: " + codScore);
            System.out.println("- Código Ranking Datas..: " + codData);
            System.out.println("- Código Background.....: " + codBack);
            System.out.println("- Código HUD............: " + codHud);
            System.out.print("---> (s = sim / n = não): ");
            char confirmacaoFinal = input.nextLine().toUpperCase().charAt(0);
            if (confirmacaoFinal == 'S') {
                Ranking rankAlterado = new Ranking(codigo, codPlayer, codScore, codData, codBack, codHud);
                try {
                    rankDAO.alterar(rankAlterado);
                } catch (Exception e) {
                    return false;
                }
            } else {
                System.out.println("\nRanking mantido.");
                return false;
            }

        } else {
            System.err.println("\nRanking não encontrado. Código inexistente.");
            return false;
        }
        
        return true;
    }

    @Override
    protected boolean excluir() {
        System.out.println("\nExcluir o registro do Ranking de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        
        Ranking rankParaDeletar = rankDAO.buscar(new Ranking(codigo));

        if (rankParaDeletar == null) {
            System.err.println("Ranking não encontrado. Código inexistente.");
            return false;
        }

        System.out.println("\nDeseja realmente excluir o Ranking informado? (s = sim / n = não)");
        System.out.println("- Código................: " + codigo);
        System.out.println("- Código Ranking Players: " + rankParaDeletar.getDezPrimeirosPlayers());
        System.out.println("- Código Ranking Scores.: " + rankParaDeletar.getDezPrimeirosScores());
        System.out.println("- Código Ranking Datas..: " + rankParaDeletar.getDezPrimeirasDatas());
        System.out.println("- Código Background.....: " + rankParaDeletar.getImagemFundo());
        System.out.println("- Código HUD............: " + rankParaDeletar.getInterfaceHud());
        System.out.print("---> (s = sim / n = não): ");
        char confirmacaoFinal = input.nextLine().toUpperCase().charAt(0);
        if (confirmacaoFinal == 'S') {
            try {
                rankDAO.excluir(rankParaDeletar);
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
        
        return true;
    }
    
    @Override
    protected boolean buscar() {
        System.out.println("\nBuscar o registro de Ranking de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        
        Ranking rankNovo = new Ranking(codigo);
        Ranking rank = rankDAO.buscar(rankNovo);
        
        if (rank != null) {
            System.out.println("\nRanking encontrado:\n");
            System.out.println("+--------+------------------+----------------+---------------+------------+-----+");
            System.out.println("| CÓDIGO | TOP 10 - PLAYERS | TOP 10 - SCORE | TOP 10 - DATA | BACKGROUND | HUD |");
            System.out.println("+--------+------------------+----------------+---------------+------------+-----+");
            System.out.printf("| %6d | %16d | %14d | %13d | %10d | %3d |\n", rank.getCodigo(), rank.getDezPrimeirosPlayers(), rank.getDezPrimeirosScores(), 
                                                                            rank.getDezPrimeirasDatas(), rank.getImagemFundo(), rank.getInterfaceHud());
            System.out.println("+--------+------------------+----------------+---------------+------------+-----+");
        } else {
            System.err.println("\nRanking não encontrado. Código inexistente.");
            return false;
        }
        
        return true;
    }
    
}
