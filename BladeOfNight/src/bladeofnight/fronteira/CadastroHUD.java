
package bladeofnight.fronteira;

import bladeofnight.armazenamento.DAOFactory;
import bladeofnight.armazenamento.HudDAO;

public class CadastroHUD extends Cadastro {
    
    private HudDAO hudDAO;
    
    public CadastroHUD() {
        hudDAO = DAOFactory.getDefaultDAOFactory().getHudDAO();
    }

    @Override
    protected String obterTituloMenu() {
        return "Cadastro de HUD (Interface Gráfica) ^-^";
    }

    @Override
    protected String obterMensagemSairDoMenu() {
        return "Voltar ao Menu Principal";
    }

    @Override
    protected boolean inserir() {
        System.out.println("\nInserindo novo registro de HUD\n");
        
        // PRIORIDADE DAS IMAGENS
        System.out.println("Lista de Prioridades de Imagens cadastradas: ");
        CadastroPrioridadeHUD hudP = new CadastroPrioridadeHUD();
        hudP.listar();
        // ESCOLHENDO UMA DAS PRIORIDADES ACIMA
        System.out.print("- Informe o código do registro de Prioridade Imagem que deseja inserir nesta HUD: ");
        int codigoPrioImg = input.nextInt();
        input.nextLine(); // Consumindo quebra de linha
        boolean existeHudP = hudP.buscar(codigoPrioImg);
        
        if (!existeHudP) {
            System.out.print("Prioridade HUD não encontrada. Código inexistente ou inválido.");
            return false;
        }
        
        // MOVIMENTO Y
        System.out.print("- Possui movimento no eixo Y (vertical) [S = Sim / N = Não]? ");
        char mY = input.nextLine().toUpperCase().charAt(0);
        boolean movimentoY = mY == 'S';
        
        if (movimentoX) {
            // VELOCIDADE X
            System.out.print("- Informe a velocidade do movimento no eixo X (horizontal): ");
            int velMoviX = input.nextInt();
            input.nextLine(); // Consumindo quebra de linha
        }
        
        if (movimentoY) {
            // VELOCIDADE Y
            System.out.print("- Informe a velocidade do movimento no eixo Y (vertical): ");
            int velMoviY = input.nextInt();
            input.nextLine(); // Consumindo quebra de linha
        }

        Background novoBack = new Background(mY, movimentoX, movimentoY, mX, mY);
        
        try {
            hudDAO.inserir(novoBack);
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }

    @Override
    protected void listar() {
        System.out.println("\nListagem de Backgrounds registrados\n");
        System.out.println("+--------+-------------+----------------+-------------+----------------+");
        System.out.println("| CÓDIGO | MOVIMENTO X |  VELOCIDADE X  | MOVIMENTO Y |  VELOCIDADE Y  |");
        System.out.println("+--------+-------------+----------------+-------------+----------------+");
        for (Background back : hudDAO.getLista()) {
            String temX = back.isMovimentoX() ? "Sim" : "Não";
            String temY = back.isMovimentoY() ? "Sim" : "Não";
            System.out.printf("| %6d | %11s | %12d | %11s | %12d |\n", back.getCodigo(), temX, back.getVelMoviX(), temY, back.getVelMoviY());
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
        Background backParaAlterar = hudDAO.buscar(backNovo);

        if (backParaAlterar != null) {
            // MOVIMENTO X
            boolean haMovimentoX = backParaAlterar.isMovimentoX();
            System.out.println("\n - Movimento no eixo X (horizontal) existente?: " + (haMovimentoX ? "Sim" : "Não"));
            System.out.print("---> Deseja alterar? (s = sim / n = não): ");
            char opcaoMovX = input.nextLine().toUpperCase().charAt(0);
            if (opcaoMovX == 'S') {
                // Como só existem duas opções possíveis para esse campo, podemos inverter automaticamente
                haMovimentoX = !haMovimentoX;
                System.out.print("Movimento X alterado para: " + (haMovimentoX ? "Sim" : "Não"));
            }
            // MOVIMENTO Y
            boolean haMovimentoY = backParaAlterar.isMovimentoY();
            System.out.println("\n - Movimento no eixo Y (vertical) existente?: " + (haMovimentoY ? "Sim" : "Não"));
            System.out.print("---> Deseja alterar? (s = sim / n = não): ");
            char opcaoMovY = input.nextLine().toUpperCase().charAt(0);
            if (opcaoMovY == 'S') {
                // Como só existem duas opções possíveis para esse campo, podemos inverter automaticamente
                haMovimentoY = !haMovimentoY;
                System.out.print("Movimento Y alterado para: " + (haMovimentoY ? "Sim" : "Não"));
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
                    hudDAO.alterar(backAlterado);
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
        System.out.println("\nExcluir o registro do Background de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        
        Background backParaDeletar = hudDAO.buscar(new Background(codigo));

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
                hudDAO.excluir(backParaDeletar);
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
        
        return true;
    }
    
    @Override
    protected void buscar() {
        System.out.println("\nBuscar o registro de Background de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        
        Background backNovo = new Background(codigo);
        Background backEncontrado = hudDAO.buscar(backNovo);
        
        if (backEncontrado != null) {
            System.out.println("\nBackground encontrado:\n");
            System.out.println("+--------+-------------+----------------+-------------+----------------+");
            System.out.println("| CÓDIGO | MOVIMENTO X |  VELOCIDADE X  | MOVIMENTO Y |  VELOCIDADE Y  |");
            System.out.println("+--------+-------------+----------------+-------------+----------------+");
            String temX = backEncontrado.isMovimentoX() ? "Sim" : "Não";
            String temY = backEncontrado.isMovimentoY() ? "Sim" : "Não";
            System.out.printf("| %6d | %11s | %12d | %11s | %12d |\n", backEncontrado.getCodigo(), temX, backEncontrado.getVelMoviX(), temY, backEncontrado.getVelMoviY());
            System.out.println("+--------+-------------+----------------+-------------+----------------+");
        } else {
            System.err.println("\nBackground não encontrado. Código inexistente.");
        }        
    }
    
}
