
package bladeofnight.fronteira;

import java.util.Scanner;

public class MenuRanking {
    
    private static final byte OPCAO_CADASTRO_RANKING = 1;
    private static final byte OPCAO_CADASTRO_RANKING_PLAYER = 2;
    private static final byte OPCAO_CADASTRO_RANKING_SCORE = 3;
    private static final byte OPCAO_CADASTRO_RANKING_DATA = 4;
    private static final byte OPCAO_VOLTAR_MENU_PRINCIPAL = 5;
    
    public void exibirMenuRanking() {
        Scanner input = new Scanner(System.in);
        byte opcao = 0;
        while (opcao != OPCAO_VOLTAR_MENU_PRINCIPAL) {
            System.out.println("\n\nOpções de Entidades:");
            System.out.println("1 - Rankings");
            System.out.println("2 - Ranking de Players (Jogadores)");
            System.out.println("3 - Ranking de Scores (Pontuações)");
            System.out.println("4 - Ranking de Datas da Pontuação");
            System.out.println("5 - Retornar ao Menu Principal");
            System.out.print("----> Escolha a sua opção: ");

            opcao = input.nextByte();
            ProcessarOpcaoUsuario(opcao);
        }
    }
    
    private void ProcessarOpcaoUsuario(byte opcao) {
        switch (opcao) {
            case OPCAO_CADASTRO_RANKING:
                CadastroRanking r = new CadastroRanking();
                r.exibirMenu();
                break;
            case OPCAO_CADASTRO_RANKING_PLAYER:
                CadastroRankingDezPlayers rdp = new CadastroRankingDezPlayers();
                rdp.exibirMenu();
                break;
            case OPCAO_CADASTRO_RANKING_SCORE:
                CadastroRankingDezScores rds = new CadastroRankingDezScores();
                rds.exibirMenu();
                break;
            case OPCAO_CADASTRO_RANKING_DATA:
                CadastroRankingDezDatas rdd = new CadastroRankingDezDatas();
                rdd.exibirMenu();
                break;
            default:
                if (opcao != OPCAO_VOLTAR_MENU_PRINCIPAL) {
                    System.err.println("Opção inválida/inexistente.");
                }
        }
    }
    
}
