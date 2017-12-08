
package bladeofnight.fronteira;

import bladeofnight.armazenamento.DAOFactory;
import bladeofnight.armazenamento.HudDAO;
import bladeofnight.entidades.HUD;

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
        
        // PRIORIDADE DAS HUDS
        CadastroPrioridadeHUD hudP = new CadastroPrioridadeHUD();
        hudP.listar();
        // ESCOLHENDO UMA DAS PRIORIDADES ACIMA
        System.out.print("- Código Prioridade: ");
        long codPri = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        boolean existeHudP = hudP.buscarComCodigo(codPri);
        
        if (!existeHudP) return false;
        
        // TEXTO DAS HUDS
        CadastroTextoHUD hudT = new CadastroTextoHUD();
        hudT.listar();
        // ESCOLHENDO UM DOS TEXTOS ACIMA
        System.out.print("- Código Texto: ");
        long codTexto = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        boolean existeHudT = hudT.buscarComCodigo(codTexto);
        
        if (!existeHudT) return false;
        
        // NÚMERO DAS HUDS
        CadastroNumeroHUD hudN = new CadastroNumeroHUD();
        hudN.listar();
        // ESCOLHENDO UM DOS NÚMEROS ACIMA
        System.out.print("- Código Número: ");
        long codNum = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        boolean existeHudN = hudN.buscarComCodigo(codNum);
        
        if (!existeHudN) return false;

        HUD novaHud = new HUD(codPri, codTexto, codNum);
        
        try {
            hudDAO.inserir(novaHud);
        } catch (Exception e) {
            return false;
        }
        
        System.out.print("\nRegistro inserido com sucesso!");
        return true;
    }

    @Override
    protected void listar() {
        System.out.println("\nListagem de HUDs registradas\n");
        System.out.println("+--------+------------+-------+--------+");
        System.out.println("| CÓDIGO | PRIORIDADE | TEXTO | NÚMERO |");
        System.out.println("+--------+------------+-------+--------+");
        for (HUD hd : hudDAO.getLista()) {
            System.out.printf("| %6d | %10d | %5d | %6d |\n", hd.getCodigo(), hd.getPrioridadeImgs(), hd.getListaTextos(), hd.getListaNumeros());
        }
        System.out.println("+--------+------------+-------+--------+");
    }

    @Override
    protected boolean alterar() {
        System.out.println("\nAlterar o registro de HUD de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        
        HUD hudNovo = new HUD(codigo);
        HUD hudParaAlterar = hudDAO.buscar(hudNovo);

        if (hudParaAlterar != null) {
            // PRIORIDADE
            // LISTANDO AS OPÇÕES POSSÍVEIS
            CadastroPrioridadeHUD hudP = new CadastroPrioridadeHUD();
            hudP.listar();
            // ALTERAÇÃO
            long codPri = hudParaAlterar.getPrioridadeImgs();
            System.out.println("\n - Código da Prioridade Atual: " + codPri);
            System.out.print("---> Deseja alterar? (s = sim / n = não): ");
            char opcaoPri = input.nextLine().toUpperCase().charAt(0);
            if (opcaoPri == 'S') {
                // ESCOLHENDO UMA DAS PRIORIDADES ACIMA
                System.out.print("- Novo Código Prioridade: ");
                codPri = input.nextLong();
                input.nextLine(); // Consumindo quebra de linha
                boolean existeHudP = hudP.buscarComCodigo(codPri);

                if (!existeHudP) return false;
            }
            // TEXTO
            // LISTANDO AS OPÇÕES POSSÍVEIS
            CadastroTextoHUD hudT = new CadastroTextoHUD();
            hudT.listar();
            // ALTERAÇÃO
            long codTxt = hudParaAlterar.getListaTextos();
            System.out.println("\n - Código do Texto Atual: " + codTxt);
            System.out.print("---> Deseja alterar? (s = sim / n = não): ");
            char opcaoTxt = input.nextLine().toUpperCase().charAt(0);
            if (opcaoTxt == 'S') {
                // ESCOLHENDO UM DOS TEXTOS ACIMA
                System.out.print("- Novo Código Texto: ");
                codTxt = input.nextLong();
                input.nextLine(); // Consumindo quebra de linha
                boolean existeHudT = hudT.buscarComCodigo(codTxt);

                if (!existeHudT) return false;
            }
            // NÚMERO
            // LISTANDO AS OPÇÕES POSSÍVEIS
            CadastroNumeroHUD hudN = new CadastroNumeroHUD();
            hudN.listar();
            // ALTERAÇÃO
            long codNum = hudParaAlterar.getListaNumeros();
            System.out.println("\n - Código do Número Atual: " + codNum);
            System.out.print("---> Deseja alterar? (s = sim / n = não): ");
            char opcaoNum = input.nextLine().toUpperCase().charAt(0);
            if (opcaoNum == 'S') {
                // ESCOLHENDO UM DOS TEXTOS ACIMA
                System.out.print("- Novo Código Número: ");
                codNum = input.nextLong();
                input.nextLine(); // Consumindo quebra de linha
                boolean existeHudN = hudN.buscarComCodigo(codNum);

                if (!existeHudN) return false;
            }
            // CONFIRMAÇÃO FINAL
            System.out.println("\nDeseja realmente modificar os dados informados? (s = sim / n = não)");
            System.out.println("- Código....: " + codigo);
            System.out.println("- Prioridade: " + codPri);
            System.out.println("- Texto.....: " + codTxt);
            System.out.println("- Número....: " + codNum);
            System.out.print("---> (s = sim / n = não): ");
            char confirmacaoFinal = input.nextLine().toUpperCase().charAt(0);
            if (confirmacaoFinal == 'S') {
                HUD hudAlterado = new HUD(codigo, codPri, codTxt, codNum);
                try {
                    hudDAO.alterar(hudAlterado);
                } catch (Exception e) {
                    return false;
                }
            } else {
                return false;
            }

        } else {
            System.err.println("\nHUD não encontrada. Código inexistente.");
            return false;
        }
        
        return true;
    }

    @Override
    protected boolean excluir() {
        System.out.println("\nExcluir o registro da HUD de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        
        HUD hudParaDeletar = hudDAO.buscar(new HUD(codigo));

        if (hudParaDeletar == null) {
            System.err.println("HUD não encontrada. Código inexistente.");
            return false;
        }

        System.out.println("\nDeseja realmente excluir a HUD informada? (s = sim / n = não)");
        System.out.println("- Código....: " + codigo);
        System.out.println("- Prioridade: " + hudParaDeletar.getPrioridadeImgs());
        System.out.println("- Texto.....: " + hudParaDeletar.getListaTextos());
        System.out.println("- Número....: " + hudParaDeletar.getListaNumeros());
        System.out.print("---> (s = sim / n = não): ");
        char confirmacaoFinal = input.nextLine().toUpperCase().charAt(0);
        if (confirmacaoFinal == 'S') {
            try {
                hudDAO.excluir(hudParaDeletar);
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
        System.out.println("\nBuscar o registro de HUD de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        
        HUD hudNovo = new HUD(codigo);
        HUD hudEncontrada = hudDAO.buscar(hudNovo);
        
        if (hudEncontrada != null) {
            System.out.println("\nHUD encontrada:\n");
            System.out.println("+--------+------------+-------+--------+");
            System.out.println("| CÓDIGO | PRIORIDADE | TEXTO | NÚMERO |");
            System.out.println("+--------+------------+-------+--------+");
            System.out.printf("| %6d | %10d | %5d | %6d |\n", hudEncontrada.getCodigo(), hudEncontrada.getPrioridadeImgs(), hudEncontrada.getListaTextos(), hudEncontrada.getListaNumeros());
            System.out.println("+--------+------------+-------+--------+");
        } else {
            System.err.println("\nHUD não encontrada. Código inexistente.");
            return false;
        }
        
        return true;
    }
    
}
