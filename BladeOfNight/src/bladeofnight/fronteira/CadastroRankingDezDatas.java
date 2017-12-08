
package bladeofnight.fronteira;

import bladeofnight.armazenamento.DAOFactory;
import bladeofnight.armazenamento.RankingDezDatasDAO;
import bladeofnight.entidades.RankingDezDatas;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CadastroRankingDezDatas extends Cadastro {
    
    private RankingDezDatasDAO rddDAO;
    
    public CadastroRankingDezDatas() {
        rddDAO = DAOFactory.getDefaultDAOFactory().getRankingDezDatasDAO();
    }

    @Override
    protected String obterTituloMenu() {
        return "Cadastro de Datas para o Top 10 do Ranking ^-^";
    }

    @Override
    protected String obterMensagemSairDoMenu() {
        return "Voltar ao Menu de Ranking";
    }

    @Override
    protected boolean inserir() {
        System.out.println("\nInserindo novo registro de Data para o Top 10 Ranking\n");
        input.nextLine(); // Consumindo quebra de linha

        // DATA
        System.out.println("\nInforme o conteúdo desejado\n");
        System.out.print("- Data da Pontuação: ");
        String dataPontuacao = input.nextLine();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        java.sql.Date data;
        try {
            data = new java.sql.Date(format.parse(dataPontuacao).getTime());
        } catch (ParseException ex) {
            return false;
        }
        
        try {
            rddDAO.inserir(new RankingDezDatas(data));
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }

    @Override
    protected void listar() {
        System.out.println("\nListagem de Datas do Top 10 do Ranking\n");
        System.out.println("+--------+-------------------+");
        System.out.println("| CÓDIGO | DATA DA PONTUAÇÃO |");
        System.out.println("+--------+-------------------+");
        for (RankingDezDatas rdd : rddDAO.getLista()) {
            System.out.printf("| %6d | %17s |\n", rdd.getCodigo(), rdd.getDataDoRecorde());
        }
        System.out.println("+--------+-------------------+");
    }

    @Override
    protected boolean alterar() {
        System.out.println("\nAlterar o registro de Data do Ranking de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        
        RankingDezDatas rddNovo = new RankingDezDatas(codigo);
        RankingDezDatas rddParaAlterar = rddDAO.buscar(rddNovo);

        if (rddParaAlterar != null) {
            // DATA DA PONTUAÇÃO
            Date dataPont = rddParaAlterar.getDataDoRecorde();
            System.out.println("\n - Data da Pontuação atual: " + dataPont);
            System.out.print("---> Deseja alterar? (s = sim / n = não): ");
            char opcaoD = input.nextLine().toUpperCase().charAt(0);
            java.sql.Date data;
            if (opcaoD == 'S') {
                System.out.print("- Informe a nova data: ");
                String dataS = input.nextLine();
                
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");                
                try {
                    data = new java.sql.Date(format.parse(dataS).getTime());
                } catch (ParseException ex) {
                    return false;
                }
            } else {
                System.out.println("\nInformações do registro mantidas.");
                return false;
            }
            // CONFIRMAÇÃO FINAL
            System.out.println("\nDeseja realmente modificar os dados informados? (s = sim / n = não)");
            System.out.println("- Código: " + codigo);
            System.out.println("- Pontuação: " + data);
            System.out.print("---> (s = sim / n = não): ");
            char confirmacaoFinal = input.nextLine().toUpperCase().charAt(0);
            if (confirmacaoFinal == 'S') {
                RankingDezDatas rddAlterado = new RankingDezDatas(codigo, data);
                try {
                    rddDAO.alterar(rddAlterado);
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
        System.out.println("\nExcluir o registro de Data do Ranking de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        
        RankingDezDatas rddParaDeletar = rddDAO.buscar(new RankingDezDatas(codigo));

        if (rddParaDeletar == null) {
            System.err.println("Data não encontrada. Código inexistente.");
            return false;
        }

        System.out.println("\nDeseja realmente excluir a data informada do Ranking? (s = sim / n = não)");
        System.out.println("- Código: " + codigo);
        System.out.println("- Data da Pontuação: " + rddParaDeletar.getDataDoRecorde());
        System.out.print("---> (s = sim / n = não): ");
        char confirmacaoFinal = input.nextLine().toUpperCase().charAt(0);
        if (confirmacaoFinal == 'S') {
            try {
                rddDAO.excluir(rddParaDeletar);
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
        System.out.println("\nBuscar o registro de Data no Top 10 do Ranking de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        
        RankingDezDatas rddNovo = new RankingDezDatas(codigo);
        RankingDezDatas rddEncontrado = rddDAO.buscar(rddNovo);
        
        if (rddEncontrado != null) {
            System.out.println("\nRegistro encontrado:\n");
            System.out.println("+--------+-------------------+");
            System.out.println("| CÓDIGO | DATA DA PONTUAÇÃO |");
            System.out.println("+--------+-------------------+");
            System.out.printf("| %6d | %17s |\n", rddEncontrado.getCodigo(), rddEncontrado.getDataDoRecorde());
            System.out.println("+--------+-------------------+");
        } else {
            System.err.println("\nData não encontrada. Código inexistente.");
            return false;
        }
        
        return true;
    }
    
}
