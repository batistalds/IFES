
package bladeofnight.fronteira;

import bladeofnight.armazenamento.DAOFactory;
import bladeofnight.armazenamento.ProjetilDAO;
import bladeofnight.entidades.Projetil;

public class CadastroProjetil extends Cadastro {
    
    private ProjetilDAO projDAO;
    
    public CadastroProjetil() {
        projDAO = DAOFactory.getDefaultDAOFactory().getProjetilDAO();
    }

    @Override
    protected String obterTituloMenu() {
        return "Cadastro de Projétil ^-^";
    }

    @Override
    protected String obterMensagemSairDoMenu() {
        return "Voltar ao Menu Principal";
    }

    @Override
    protected boolean inserir() {
        System.out.println("\nInserindo novo registro de projétil\n");
        input.nextLine(); // Consumindo quebra de linha
        // VELOCIDADE X
        System.out.print("- Informe a Velocidade X (Horizontal) padrão do Projétil: ");
        int velX = input.nextInt();
        input.nextLine(); // Consumindo quebra de linha
        
        // VELOCIDADE Y
        System.out.print("- Informe a Velocidade Y (Vertical) padrão do Projétil: ");
        int velY = input.nextInt();
        input.nextLine(); // Consumindo quebra de linha
        
        // ÂNGULO
        System.out.print("- Informe o ângulo que o Projétil irá seguir: ");
        int angulo = input.nextInt();
        input.nextLine(); // Consumindo quebra de linha
        
        // DELAY
        System.out.print("- Informe o delay entre cada disparo: ");
        double delay = input.nextDouble();
        input.nextLine(); // Consumindo quebra de linha
        
        // PODER
        System.out.print("- Informe o poder (força) de cada disparo: ");
        int poder = input.nextInt();
        input.nextLine(); // Consumindo quebra de linha

        Projetil novoProjetil = new Projetil(velX, velY, angulo, delay, poder);
        
        try {
            projDAO.inserir(novoProjetil);
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }

    @Override
    protected void listar() {
        System.out.println("\nListagem de Projéteis registrados\n");
        System.out.println("+--------+--------------+--------------+--------+-----------+-------+");
        System.out.println("| CÓDIGO | VELOCIDADE X | VELOCIDADE Y | ÂNGULO |   DELAY   | PODER |");
        System.out.println("+--------+--------------+--------------+--------+-----------+-------+");
        for (Projetil pr : projDAO.getLista()) {
            System.out.printf("| %6d | %12d | %12d | %6d | %9.4f | %5d |\n", pr.getCodigo(), pr.getVelX(), pr.getVelY(), pr.getAngulo(), pr.getDelay(), pr.getPoder());
        }
        System.out.println("+--------+--------------+--------------+--------+-----------+-------+");
    }

    @Override
    protected boolean alterar() {
        System.out.println("\nAlterar o registro de Projétil de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha

        Projetil projetilNovo = new Projetil(codigo);
        Projetil projetilParaAlterar = projDAO.buscar(projetilNovo);

        if (projetilParaAlterar != null) {
            // VELOCIDADE X
            int velX = projetilParaAlterar.getVelX();
            System.out.println("\n - Velocidade X atual: " + velX);
            System.out.print("---> Deseja alterar? (s = sim / n = não): ");
            char opcaoVelX = input.nextLine().toUpperCase().charAt(0);
            if (opcaoVelX == 'S') {
                System.out.print("- Informe a nova Velocidade X (Horizontal) padrão do Projétil: ");
                velX = input.nextInt();
                input.nextLine(); // Consumindo quebra de linha
            }

            // VELOCIDADE Y
            int velY = projetilParaAlterar.getVelY();
            System.out.println("\n - Velocidade Y atual: " + velY);
            System.out.print("---> Deseja alterar? (s = sim / n = não): ");
            char opcaoVelY = input.nextLine().toUpperCase().charAt(0);
            if (opcaoVelY == 'S') {
                System.out.print("- Informe a nova Velocidade Y (Vertical) padrão do Projétil: ");
                velY = input.nextInt();
                input.nextLine(); // Consumindo quebra de linha
            }
            
            // ÀNGULO
            int angulo = projetilParaAlterar.getAngulo();
            System.out.println("\n - Ângulo atual: " + angulo);
            System.out.print("---> Deseja alterar? (s = sim / n = não): ");
            char opcaoAngulo = input.nextLine().toUpperCase().charAt(0);
            if (opcaoAngulo == 'S') {
                System.out.print("- Informe o novo ângulo do Projétil: ");
                angulo = input.nextInt();
                input.nextLine(); // Consumindo quebra de linha
            }
            
            // DELAY
            double delay = projetilParaAlterar.getDelay();
            System.out.println("\n - Delay atual: " + delay);
            System.out.print("---> Deseja alterar? (s = sim / n = não): ");
            char opcaoDelay = input.nextLine().toUpperCase().charAt(0);
            if (opcaoDelay == 'S') {
                System.out.print("- Informe o novo delay do Projétil: ");
                delay = input.nextDouble();
                input.nextLine(); // Consumindo quebra de linha
            }
            
            // PODER
            int poder = projetilParaAlterar.getPoder();
            System.out.println("\n - Poder atual: " + poder);
            System.out.print("---> Deseja alterar? (s = sim / n = não): ");
            char opcaoPoder = input.nextLine().toUpperCase().charAt(0);
            if (opcaoPoder == 'S') {
                System.out.print("- Informe o novo poder (força) do Projétil: ");
                poder = input.nextInt();
                input.nextLine(); // Consumindo quebra de linha
            }
            
            // CONFIRMAÇÃO FINAL
            System.out.println("\nDeseja realmente modificar os dados informados? (s = sim / n = não)");
            System.out.println("- Código......: " + codigo);
            System.out.println("- Velocidade X: " + velX);
            System.out.println("- Velocidade Y: " + velY);
            System.out.println("- Ângulo......: " + angulo);
            System.out.println("- Delay.......: " + delay);
            System.out.println("- Poder.......: " + poder);
            System.out.print("---> (s = sim / n = não): ");
            char confirmacaoFinal = input.nextLine().toUpperCase().charAt(0);
            if (confirmacaoFinal == 'S') {
                Projetil projetilAlterado = new Projetil(codigo, velX, velY, angulo, delay, poder);
                try {
                    projDAO.alterar(projetilAlterado);
                } catch (Exception e) {
                    return false;
                }
            } else {
                System.out.println("\nProjétil mantido.");
                return false;
            }

        } else {
            System.err.println("\nProjétil não encontrado. Código inexistente.");
            return false;
        }
        
        return true;
    }

    @Override
    protected boolean excluir() {
        System.out.println("\nExcluir o registro do Projétil de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        
        Projetil projetilParaDeletar = projDAO.buscar(new Projetil(codigo));

        if (projetilParaDeletar == null) {
            System.err.println("Projétil não encontrado. Código inexistente.");
            return false;
        }

        System.out.println("\nDeseja realmente excluir o Projétil informado? (s = sim / n = não)");
        System.out.println("- Código......: " + codigo);
        System.out.println("- Velocidade X: " + projetilParaDeletar.getVelX());
        System.out.println("- Velocidade Y: " + projetilParaDeletar.getVelY());
        System.out.println("- Ângulo......: " + projetilParaDeletar.getAngulo());
        System.out.println("- Delay.......: " + projetilParaDeletar.getDelay());
        System.out.println("- Poder.......: " + projetilParaDeletar.getPoder());
        System.out.print("---> (s = sim / n = não): ");
        char confirmacaoFinal = input.nextLine().toUpperCase().charAt(0);
        if (confirmacaoFinal == 'S') {
            try {
                projDAO.excluir(projetilParaDeletar);
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
        System.out.println("\nBuscar o registro de Projétil de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        
        Projetil projetilNovo = new Projetil(codigo);
        Projetil pr = projDAO.buscar(projetilNovo);
        
        if (pr != null) {
            System.out.println("\nProjétil encontrado:\n");
            System.out.println("+--------+--------------+--------------+--------+-----------+-------+");
            System.out.println("| CÓDIGO | VELOCIDADE X | VELOCIDADE Y | ÂNGULO |   DELAY   | PODER |");
            System.out.println("+--------+--------------+--------------+--------+-----------+-------+");
            System.out.printf("| %6d | %12d | %12d | %6d | %9.4f | %5d |\n", pr.getCodigo(), pr.getVelX(), pr.getVelY(), pr.getAngulo(), pr.getDelay(), pr.getPoder());
            System.out.println("+--------+--------------+--------------+--------+-----------+-------+");
        } else {
            System.err.println("\nProjétil não encontrado. Código inexistente.");
            return false;
        }
        
        return true;
    }
    
}
