
package bladeofnight.fronteira;

import bladeofnight.armazenamento.BackgroundDAO;
import bladeofnight.armazenamento.DAOFactory;
import bladeofnight.entidades.Background;

public class CadastroBackground extends Cadastro {
    
    private BackgroundDAO backgroundDAO;
    
    public CadastroBackground() {
        backgroundDAO = DAOFactory.getDefaultDAOFactory().getBackgroundDAO();
    }

    @Override
    protected String obterTituloMenu() {
        return "Cadastro de Background ^-^";
    }

    @Override
    protected String obterMensagemSairDoMenu() {
        return "Voltar ao Menu Principal";
    }

    @Override
    protected boolean inserir() {
        System.out.println("\nInserindo novo registro de background\n");
        input.nextLine(); // Consumindo quebra de linha
        // MOVIMENTO X
        System.out.print("- Possui movimento no eixo X (horizontal) [S = Sim / N = Não]? ");
        char mX = input.nextLine().toUpperCase().charAt(0);
        boolean movimentoX = mX == 'S';
        // MOVIMENTO Y
        System.out.print("- Possui movimento no eixo Y (vertical) [S = Sim / N = Não]? ");
        char mY = input.nextLine().toUpperCase().charAt(0);
        boolean movimentoY = mY == 'S';
        
        int velMoviX = 0;
        if (movimentoX) {
            // VELOCIDADE X
            System.out.print("- Informe a velocidade do movimento no eixo X (horizontal): ");
            velMoviX = input.nextInt();
            input.nextLine(); // Consumindo quebra de linha
        } else {
            
        }
        
        int velMoviY = 0;
        if (movimentoY) {
            // VELOCIDADE Y
            System.out.print("- Informe a velocidade do movimento no eixo Y (vertical): ");
            velMoviY = input.nextInt();
            input.nextLine(); // Consumindo quebra de linha
        }

        Background novoBack = new Background(movimentoX, movimentoY, velMoviX, velMoviY);
        
        try {
            backgroundDAO.inserir(novoBack);
        } catch (Exception e) {
            return false;
        }
        
        System.out.print("Cadastro executado com sucesso!");
        return true;
    }

    @Override
    protected void listar() {
        System.out.println("\nListagem de Backgrounds registrados\n");
        System.out.println("+--------+-------------+----------------+-------------+----------------+");
        System.out.println("| CÓDIGO | MOVIMENTO X |  VELOCIDADE X  | MOVIMENTO Y |  VELOCIDADE Y  |");
        System.out.println("+--------+-------------+----------------+-------------+----------------+");
        for (Background back : backgroundDAO.getLista()) {
            String temX = back.isMovimentoX() ? "Sim" : "Não";
            String temY = back.isMovimentoY() ? "Sim" : "Não";
            System.out.printf("| %6d | %11s | %14d | %11s | %14d |\n", back.getCodigo(), temX, back.getVelMoviX(), temY, back.getVelMoviY());
        }
        System.out.println("+--------+-------------+----------------+-------------+----------------+");
    }

    @Override
    protected boolean alterar() {
        System.out.println("\nAlterar o registro de Background de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        
        Background backNovo = new Background(codigo);
        Background backParaAlterar = backgroundDAO.buscar(backNovo);

        if (backParaAlterar != null) {
            // MOVIMENTO X
            boolean haMovimentoX = backParaAlterar.isMovimentoX();
            System.out.println("\n - Movimento no eixo X (horizontal) existente?: " + (haMovimentoX ? "Sim" : "Não"));
            System.out.print("---> Deseja alterar? (s = sim / n = não): ");
            char opcaoMovX = input.nextLine().toUpperCase().charAt(0);
            if (opcaoMovX == 'S') {
                // Como só existem duas opções possíveis para esse campo, podemos inverter automaticamente
                haMovimentoX = !haMovimentoX;
                System.out.println("Movimento X alterado para: " + (haMovimentoX ? "Sim" : "Não"));
            }
            // MOVIMENTO Y
            boolean haMovimentoY = backParaAlterar.isMovimentoY();
            System.out.println("\n - Movimento no eixo Y (vertical) existente?: " + (haMovimentoY ? "Sim" : "Não"));
            System.out.print("---> Deseja alterar? (s = sim / n = não): ");
            char opcaoMovY = input.nextLine().toUpperCase().charAt(0);
            if (opcaoMovY == 'S') {
                // Como só existem duas opções possíveis para esse campo, podemos inverter automaticamente
                haMovimentoY = !haMovimentoY;
                System.out.println("Movimento Y alterado para: " + (haMovimentoY ? "Sim" : "Não"));
            }
            // VELOCIDADE X
            int velX = backParaAlterar.getVelMoviX();
            if (haMovimentoX) {
                System.out.println("\n - Velocidade X (horizontal) atual: " + Integer.toString(velX));
                System.out.print("---> Deseja alterar? (s = sim / n = não): ");
                char opcaoVelX = input.nextLine().toUpperCase().charAt(0);
                if (opcaoVelX == 'S') {
                    System.out.print("- Informe a nova Velocidade X: ");
                    velX = input.nextInt();
                    input.nextLine(); // Consumindo quebra de linha
                }
            } else {
                // Se não houver movimento horizontal, velocidade será zerada
                velX = 0;
            }
            // VELOCIDADE Y
            int velY = backParaAlterar.getVelMoviY();
            if (haMovimentoY) {
                System.out.println("\n - Velocidade Y (vertical) atual: " + Integer.toString(velY));
                System.out.print("---> Deseja alterar? (s = sim / n = não): ");
                char opcaoVelY = input.nextLine().toUpperCase().charAt(0);
                if (opcaoVelY == 'S') {
                    System.out.print("- Informe a nova Velocidade Y: ");
                    velY = input.nextInt();
                    input.nextLine(); // Consumindo quebra de linha
                }
            } else {
                // Se não houver movimento vertical, velocidade será zerada
                velY = 0;
            }
            // CONFIRMAÇÃO FINAL
            System.out.println("\nDeseja realmente modificar os dados informados? (s = sim / n = não)");
            System.out.println("- Código.......: " + backParaAlterar.getCodigo());
            System.out.println("- Movimento X..: " + (haMovimentoX ? "Sim" : "Não"));
            System.out.println("- Movimento Y..: " + (haMovimentoY ? "Sim" : "Não"));
            System.out.println("- Velocidade X.: " + velX);
            System.out.println("- Velocidade Y.: " + velY);
            System.out.print("---> (s = sim / n = não): ");
            char confirmacaoFinal = input.nextLine().toUpperCase().charAt(0);
            if (confirmacaoFinal == 'S') {
                Background backAlterado = new Background(codigo, haMovimentoX, haMovimentoY, velX, velY);                
                try {
                    backgroundDAO.alterar(backAlterado);
                } catch (Exception e) {
                    return false;
                }
            } else {
                System.out.println("\nBackground mantido.");
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
        System.out.println("\nExcluir o registro do Background de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        
        Background backParaDeletar = backgroundDAO.buscar(new Background(codigo));

        if (backParaDeletar == null) {
            System.err.println("Background não encontrado. Código inexistente.");
            return false;
        }

        System.out.println("\nDeseja realmente excluir o Background informado? (s = sim / n = não)");
        System.out.println("- Código......: " + backParaDeletar.getCodigo());
        System.out.println("- Movimento X.: " + (backParaDeletar.isMovimentoX() ? "Sim" : "Não"));
        System.out.println("- Movimento Y.: " + (backParaDeletar.isMovimentoY() ? "Sim" : "Não"));
        System.out.println("- Velocidade X: " + backParaDeletar.getVelMoviX());
        System.out.println("- Velocidade Y: " + backParaDeletar.getVelMoviY());
        System.out.print("---> (s = sim / n = não): ");
        char confirmacaoFinal = input.nextLine().toUpperCase().charAt(0);
        if (confirmacaoFinal == 'S') {
            try {
                backgroundDAO.excluir(backParaDeletar);
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
        System.out.println("\nBuscar o registro de Background de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        
        Background backNovo = new Background(codigo);
        Background backEncontrado = backgroundDAO.buscar(backNovo);
        
        if (backEncontrado != null) {
            System.out.print("\nBackground encontrado:\n");
            System.out.println("+--------+-------------+----------------+-------------+----------------+");
            System.out.println("| CÓDIGO | MOVIMENTO X |  VELOCIDADE X  | MOVIMENTO Y |  VELOCIDADE Y  |");
            System.out.println("+--------+-------------+----------------+-------------+----------------+");
            String temX = backEncontrado.isMovimentoX() ? "Sim" : "Não";
            String temY = backEncontrado.isMovimentoY() ? "Sim" : "Não";
            System.out.printf("| %6d | %11s | %14d | %11s | %14d |\n", backEncontrado.getCodigo(), temX, backEncontrado.getVelMoviX(), temY, backEncontrado.getVelMoviY());
            System.out.println("+--------+-------------+----------------+-------------+----------------+");
        } else {
            System.err.println("\nBackground não encontrado. Código inexistente.");
            return false;
        }
        
        return true;
    }
}

