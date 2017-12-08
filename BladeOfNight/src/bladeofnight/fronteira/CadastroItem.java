
package bladeofnight.fronteira;

import bladeofnight.armazenamento.DAOFactory;
import bladeofnight.armazenamento.ItemDAO;
import bladeofnight.entidades.Item;

public class CadastroItem extends Cadastro {
    
    private ItemDAO itemDAO;
    
    public CadastroItem() {
        itemDAO = DAOFactory.getDefaultDAOFactory().getItemDAO();
    }

    @Override
    protected String obterTituloMenu() {
        return "Cadastro de Item ^-^";
    }

    @Override
    protected String obterMensagemSairDoMenu() {
        return "Voltar ao Menu Principal";
    }

    @Override
    protected boolean inserir() {
        System.out.println("\nInserindo novo registro de item\n");
        input.nextLine(); // Consumindo quebra de linha
        // NOME
        System.out.print("- Informe o nome do Item: ");
        String nome = input.nextLine();
        
        // EFEITO
        System.out.print("- Informe o efeito do Item: ");
        String efeito = input.nextLine();
        
        // VELOCIDADE Y
        System.out.print("- Informe a Velocidade Y (Vertical) do Item na tela (0 = Parado): ");
        int velY = input.nextInt();
        input.nextLine(); // Consumindo quebra de linha

        Item novoItem = new Item(nome, efeito, velY);
        
        try {
            itemDAO.inserir(novoItem);
        } catch (Exception e) {
            return false;
        }
        
        System.out.print("Cadastro executado com sucesso!");
        return true;
    }

    @Override
    protected void listar() {
        System.out.println("\nListagem de Itens registrados\n");
        System.out.println("+--------+------------------+----------------------------+--------------+");
        System.out.println("| CÓDIGO |       NOME       |           EFEITO           | VELOCIDADE Y |");
        System.out.println("+--------+------------------+----------------------------+--------------+");
        for (Item item : itemDAO.getLista()) {
            System.out.printf("| %6d | %16s | %26s | %12d |\n", item.getCodigo(), item.getNome(), item.getEfeito(), item.getVelY());
        }
        System.out.println("+--------+------------------+----------------------------+--------------+");
    }

    @Override
    protected boolean alterar() {
        System.out.println("\nAlterar o registro de item de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha

        Item itemNovo = new Item(codigo);
        Item itemParaAlterar = itemDAO.buscar(itemNovo);

        if (itemParaAlterar != null) {
            // NOME
            String nome = itemParaAlterar.getNome();
            System.out.println("\n - Nome atual: " + nome);
            System.out.print("---> Deseja alterar? (s = sim / n = não): ");
            char opcaoNome = input.nextLine().toUpperCase().charAt(0);
            if (opcaoNome == 'S') {
                System.out.print("- Informe o novo nome do Item: ");
                nome = input.nextLine();
            }

            // EFEITO
            String efeito = itemParaAlterar.getEfeito();
            System.out.println("\n - Efeito atual: " + efeito);
            System.out.print("---> Deseja alterar? (s = sim / n = não): ");
            char opcaoEfeito = input.nextLine().toUpperCase().charAt(0);
            if (opcaoEfeito == 'S') {
                System.out.print("- Informe o novo efeito do Item: ");
                efeito = input.nextLine();
            }

            // VELOCIDADE Y
            int velY = itemParaAlterar.getVelY();
            System.out.println("\n - Velocidade Y atual: " + velY);
            System.out.print("---> Deseja alterar? (s = sim / n = não): ");
            char opcaoVelY = input.nextLine().toUpperCase().charAt(0);
            if (opcaoVelY == 'S') {
                System.out.print("- Informe a nova Velocidade Y (Vertical) do Item na tela (0 = Parado): ");
                velY = input.nextInt();
                input.nextLine(); // Consumindo quebra de linha
            }
            
            // CONFIRMAÇÃO FINAL
            System.out.println("\nDeseja realmente modificar os dados informados? (s = sim / n = não)");
            System.out.println("- Código......: " + codigo);
            System.out.println("- Nome........: " + nome);
            System.out.println("- Efeito......: " + efeito);
            System.out.println("- Velocidade Y: " + velY);
            System.out.print("---> (s = sim / n = não): ");
            char confirmacaoFinal = input.nextLine().toUpperCase().charAt(0);
            if (confirmacaoFinal == 'S') {
                Item itemAlterado = new Item(codigo, nome, efeito, velY);
                try {
                    itemDAO.alterar(itemAlterado);
                } catch (Exception e) {
                    return false;
                }
            } else {
                System.out.println("\nItem mantido.");
                return false;
            }

        } else {
            System.err.println("\nItem não encontrado. Código inexistente.");
            return false;
        }
        
        return true;
    }

    @Override
    protected boolean excluir() {
        System.out.println("\nExcluir o registro do Item de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        
        Item itemParaDeletar = itemDAO.buscar(new Item(codigo));

        if (itemParaDeletar == null) {
            System.err.println("Item não encontrado. Código inexistente.");
            return false;
        }

        System.out.println("\nDeseja realmente excluir o Item informado? (s = sim / n = não)");
        System.out.println("- Código......: " + codigo);
        System.out.println("- Nome........: " + itemParaDeletar.getNome());
        System.out.println("- Efeito......: " + itemParaDeletar.getEfeito());
        System.out.println("- Velocidade Y: " + itemParaDeletar.getVelY());
        System.out.print("---> (s = sim / n = não): ");
        char confirmacaoFinal = input.nextLine().toUpperCase().charAt(0);
        if (confirmacaoFinal == 'S') {
            try {
                itemDAO.excluir(itemParaDeletar);
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
        System.out.println("\nBuscar o registro de Item de\n");
        System.out.print("- Código: ");
        long codigo = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        
        Item itemNovo = new Item(codigo);
        Item itemEncontrado = itemDAO.buscar(itemNovo);
        
        if (itemEncontrado != null) {
            System.out.println("\nItem encontrado:\n");
            System.out.println("+--------+------------------+----------------------------+--------------+");
            System.out.println("| CÓDIGO |       NOME       |           EFEITO           | VELOCIDADE Y |");
            System.out.println("+--------+------------------+----------------------------+--------------+");
            System.out.printf("| %6d | %16s | %26s | %12d |\n", codigo, itemEncontrado.getNome(), itemEncontrado.getEfeito(), itemEncontrado.getVelY());
            System.out.println("+--------+------------------+----------------------------+--------------+");
        } else {
            System.err.println("\nItem não encontrado. Código inexistente.");
            return false;
        }
        
        return true;
    }
    
}
