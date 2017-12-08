
package bladeofnight.fronteira;

import bladeofnight.armazenamento.BossDAO;
import bladeofnight.armazenamento.DAOFactory;
import bladeofnight.armazenamento.InimigoDAO;
import bladeofnight.armazenamento.JogadorDAO;
import bladeofnight.armazenamento.NaveDAO;
import bladeofnight.entidades.Boss;
import bladeofnight.entidades.Inimigo;
import bladeofnight.entidades.Jogador;
import bladeofnight.entidades.Nave;

public class CadastroNave extends Cadastro {
    
    private NaveDAO naveDAO;
    private JogadorDAO jogadorDAO;
    private InimigoDAO inimigoDAO;
    private BossDAO bossDAO;
    
    public CadastroNave() {
        naveDAO = DAOFactory.getDefaultDAOFactory().getNaveDAO();
        jogadorDAO = DAOFactory.getDefaultDAOFactory().getJogadorDAO();
        inimigoDAO = DAOFactory.getDefaultDAOFactory().getInimigoDAO();
        bossDAO = DAOFactory.getDefaultDAOFactory().getBossDAO();
    }

    @Override
    protected String obterTituloMenu() {
        return "Cadastro de Nave ^-^";
    }

    @Override
    protected String obterMensagemSairDoMenu() {
        return "Voltar ao Menu Principal";
    }

    @Override
    protected boolean inserir() {
        System.out.println("\nInserindo novo registro de nave\n");
        input.nextLine(); // Consumindo quebra de linha
        // COR
        System.out.print("- Informe a cor da nave: ");
        String cor = input.nextLine();
        
        // TIPO
        System.out.print("- Informe o tipo da nave (J = Jogador / I = Inimigo): ");
        char tipo;
        while (true)
        {
            tipo = input.nextLine().toUpperCase().charAt(0);
            if (tipo == 'J' || tipo == 'I') break;
            System.err.println("Tipo inválido. Nave deve ser: J = Jogador ou I = Inimigo");
            System.out.print("- Informe novamente: ");
        }
        
        // VELOCIDADE
        System.out.print("- Informe o valor da velocidade padrão da nave: ");
        int velocidade = input.nextInt();
        input.nextLine(); // Consumindo quebra de linha
        
        // PODER
        System.out.print("- Informe o poder (força) padrão da nave: ");
        int poder = input.nextInt();
        input.nextLine(); // Consumindo quebra de linha

        Nave novaNave = new Nave(poder, cor, tipo, velocidade, poder);
        
        try {
            naveDAO.inserir(novaNave);
        } catch (Exception e) {
            return false;
        }
        
        // VERIFICANDO SE A NAVE É UM JOGADOR OU INIMIGO PARA TAMBÉM CADASTRÁ-LOS
        if (tipo == 'J') {
            System.out.println("\nNave cadastrada. Associando um novo registro de Jogador a ela\n");
        
            // NOME
            System.out.print("- Informe o nome da nave jogadora: ");
            String nomeJogador = input.nextLine();
            
            // CÓDIGO DA NAVE
            long codigoNave = naveDAO.buscarCodigo(novaNave);

            Jogador novaNaveJogadora = new Jogador(codigoNave, nomeJogador);

            try {
                jogadorDAO.inserir(novaNaveJogadora);
            } catch (Exception e) {
                return false;
            }
            
        } else if (tipo == 'I') {
            System.out.println("\nNave cadastrada. Associando um novo registro de Inimigo a ela\n");
        
            // NOME
            System.out.print("- Informe o nome da nave inimiga: ");
            String nomeInimigo = input.nextLine();
            
            // TIPO
            System.out.print("- Informe o tipo de Inimigo (I = Inimigo Comum / B = Boss): ");
            char tipoInimigo;
            while (true)
            {
                tipoInimigo = input.nextLine().toUpperCase().charAt(0);
                if (tipoInimigo == 'B' || tipoInimigo == 'I') break;
                System.err.println("Tipo inválido. Nave deve ser: I = Inimigo Comum ou B = Boss");
                System.out.print("- Informe novamente: ");
            }
            
            // CÓDIGO DA NAVE
            long codigoNave = naveDAO.buscarCodigo(novaNave);
            
            Inimigo novaNaveInimiga = new Inimigo(codigoNave, nomeInimigo, tipoInimigo);

            try {
                inimigoDAO.inserir(novaNaveInimiga);
            } catch (Exception e) {
                return false;
            }
            
            // VERIFICANDO SE A NAVE INIMIGA TAMBÉM É UM BOSS (CHEFÃO)
            if (tipoInimigo == 'B') {
                System.out.println("\nInimigo cadastrado. Associando um novo registro de Boss a ele\n");
        
                // FASE
                System.out.print("- Informe o número da fase em que aparece o boss: ");
                int fase = input.nextInt();
                input.nextLine(); // Consumindo quebra de linha
                
                // CÓDIGO DO INIMIGO
                long codigoInimigo = inimigoDAO.buscarCodigo(novaNaveInimiga);
                
                Boss novaNaveBoss = new Boss(codigoNave, codigoInimigo, fase);

                try {
                    bossDAO.inserir(novaNaveBoss);
                } catch (Exception e) {
                    return false;
                }
            }
            
        }
        
        return true;
    }

    @Override
    protected void listar() {
        System.out.println("\nListagem de Naves registradas\n");
        System.out.println("+--------+-----------------+------------+------------+-------+");
        System.out.println("| CÓDIGO |       COR       |    TIPO    | VELOCIDADE | PODER |");
        System.out.println("+--------+-----------------+------------+------------+-------+");
        for (Nave nave : naveDAO.getLista()) {
            System.out.printf("| %6d | %15s | %10s | %10d | %5d |\n", nave.getCodigo(), nave.getCor(), nave.getTipo(), nave.getVelocidade(), nave.getPoder());
        }
        System.out.println("+--------+-----------------+------------+------------+-------+");
        
        System.out.println("\nListagem de Inimigos registrados\n");
        System.out.println("+--------+------------------+----------------+------+");
        System.out.println("| CÓDIGO |       NOME       |      TIPO      | NAVE |");
        System.out.println("+--------+------------------+----------------+------+");
        for (Inimigo ini : inimigoDAO.getLista()) {
            System.out.printf("| %6d | %16s | %14s | %4d |\n", ini.getCodigo(), ini.getNomeInimigo(), ini.getTipoInimigo(), ini.getNaveId());
        }
        System.out.println("+--------+------------------+----------------+------+");
        
        System.out.println("\nListagem de Bosses (Chefões) registrados\n");
        System.out.println("+--------+------+---------+");
        System.out.println("| CÓDIGO | FASE | INIMIGO |");
        System.out.println("+--------+------+---------+");
        for (Boss bo : bossDAO.getLista()) {
            System.out.printf("| %6d | %4d | %7d |\n", bo.getCodigo(), bo.getFase(), bo.getInimigoId());
        }
        System.out.println("+--------+------+---------+");
        
        System.out.println("\nListagem de Jogadores registrados\n");
        System.out.println("+--------+------------------+------+");
        System.out.println("| CÓDIGO |       NOME       | NAVE |");
        System.out.println("+--------+------------------+------+");
        for (Jogador jog : jogadorDAO.getLista()) {
            System.out.printf("| %6d | %16s | %4d |\n", jog.getCodigo(), jog.getNomeJogador(), jog.getNaveId());
        }
        System.out.println("+--------+------------------+------+");
    }

    @Override
    protected boolean alterar() {
        System.out.println("\nAlterar o registro de Nave de\n");
        System.out.print("- Código: ");
        long codigoNave = input.nextLong();
        input.nextLine(); // Consumindo quebra de linha
        
        Nave naveNova = new Nave(codigoNave);
        Nave naveParaAlterar = naveDAO.buscar(naveNova);

        if (naveParaAlterar != null) {
            // COR
            String cor = naveParaAlterar.getCor();
            System.out.println("\n - Cor atual: " + cor);
            System.out.print("---> Deseja alterar? (s = sim / n = não): ");
            char opcaoCor = input.nextLine().toUpperCase().charAt(0);
            if (opcaoCor == 'S') {
                System.out.print("- Informe a nova cor da nave: ");
                cor = input.nextLine();
            }

            // TIPO
            char tipoNave = naveParaAlterar.getTipo();
            /*System.out.println("\n - Tipo atual: " + (tipoNave == 'J' ? "Jogador" : "Inimigo"));
            System.out.print("---> Deseja alterar? (s = sim / n = não): ");
            char opcaoTipoNave = input.nextLine().toUpperCase().charAt(0);
            if (opcaoTipoNave == 'S') {
                if (tipoNave == 'J')
                    tipoNave = 'I';
                else
                    tipoNave = 'J';
            }*/

            // VELOCIDADE
            int velocidade = naveParaAlterar.getVelocidade();
            System.out.println("\n - Velocidade atual: " + velocidade);
            System.out.print("---> Deseja alterar? (s = sim / n = não): ");
            char opcaoVel = input.nextLine().toUpperCase().charAt(0);
            if (opcaoVel == 'S') {
                System.out.print("- Informe a nova velocidade padrão da nave: ");
                velocidade = input.nextInt();
                input.nextLine(); // Consumindo quebra de linha
            }

            // PODER
            int poder = naveParaAlterar.getPoder();
            System.out.println("\n - Poder atual: " + poder);
            System.out.print("---> Deseja alterar? (s = sim / n = não): ");
            char opcaoPoder = input.nextLine().toUpperCase().charAt(0);
            if (opcaoPoder == 'S') {
                System.out.print("- Informe o novo poder (força) padrão da nave: ");
                poder = input.nextInt();
                input.nextLine(); // Consumindo quebra de linha
            }
            
            // CONFIRMAÇÃO FINAL NAVE
            System.out.println("\nDeseja realmente modificar os dados informados? (s = sim / n = não)");
            System.out.println("- Código....: " + codigoNave);
            System.out.println("- Cor.......: " + cor);
            System.out.println("- Tipo......: " + (tipoNave == 'J' ? "Jogador" : "Inimigo"));
            System.out.println("- Velocidade: " + velocidade);
            System.out.println("- Poder.....: " + poder);
            System.out.print("---> (s = sim / n = não): ");
            char confirmacaoFinalNave = input.nextLine().toUpperCase().charAt(0);
            if (confirmacaoFinalNave == 'S') {
                Nave naveAlterada = new Nave(codigoNave, cor, tipoNave, velocidade, poder);
                try {
                    naveDAO.alterar(naveAlterada);
                } catch (Exception e) {
                    return false;
                }
            } else {
                System.out.println("\nNave mantida.");
            }
            
            // ALTERANDO INIMIGO OU JOGADOR
            if (tipoNave == 'J') {
                System.out.print("\nAlterar também as informações do Jogador associado a esta Nave? (s = sim / n = não): ");
                char opcaoJogador = input.nextLine().toUpperCase().charAt(0);
                if (opcaoJogador == 'S') {
                    System.out.println("\nAlterar o registro do Jogador de\n");
                    System.out.print("- Código: ");
                    long codigoJogador = input.nextLong();
                    input.nextLine(); // Consumindo quebra de linha

                    Jogador jogadorNovo = new Jogador(codigoJogador, naveParaAlterar.getCodigo());
                    Jogador jogadorParaAlterar = jogadorDAO.buscar(jogadorNovo);
                    
                    if (jogadorParaAlterar != null) {
                        // NOME
                        String nome = jogadorParaAlterar.getNomeJogador();
                        System.out.println("\n - Nome atual: " + nome);
                        System.out.print("---> Deseja alterar? (s = sim / n = não): ");
                        char opcaoNome = input.nextLine().toUpperCase().charAt(0);
                        if (opcaoNome == 'S') {
                            System.out.print("- Informe o novo nome da nave jogadora: ");
                            nome = input.nextLine();
                        }
                        
                        // CONFIRMAÇÃO FINAL JOGADOR
                        System.out.println("\nDeseja realmente modificar os dados informados? (s = sim / n = não)");
                        System.out.println("- Código: " + codigoJogador);
                        System.out.println("- Nome..: " + nome);
                        System.out.println("- Nave..: " + jogadorParaAlterar.getNaveId());
                        System.out.print("---> (s = sim / n = não): ");
                        char confirmacaoFinalJogador = input.nextLine().toUpperCase().charAt(0);
                        if (confirmacaoFinalJogador == 'S') {
                            Jogador jogadorAlterado = new Jogador(codigoJogador, jogadorParaAlterar.getNaveId(), nome);
                            try {
                                jogadorDAO.alterar(jogadorAlterado);
                            } catch (Exception e) {
                                return false;
                            }
                        } else {
                            System.out.println("\nJogador mantido.");
                        }
                    }
                }
            } else if (tipoNave == 'I') {
                System.out.print("\nAlterar também as informações do Inimigo associado a esta Nave? (s = sim / n = não): ");
                char opcaoInimigo = input.nextLine().toUpperCase().charAt(0);
                if (opcaoInimigo == 'S') {
                    System.out.println("\nAlterar o registro do Inimigo de\n");
                    System.out.print("- Código: ");
                    long codigoInimigo = input.nextLong();
                    input.nextLine(); // Consumindo quebra de linha

                    Inimigo inimigoNovo = new Inimigo(codigoNave, codigoInimigo);
                    Inimigo inimigoParaAlterar = inimigoDAO.buscar(inimigoNovo);
                    
                    if (inimigoParaAlterar != null) {
                        // NOME
                        String nome = inimigoParaAlterar.getNomeInimigo();
                        System.out.println("\n - Nome atual: " + nome);
                        System.out.print("---> Deseja alterar? (s = sim / n = não): ");
                        char opcaoNome = input.nextLine().toUpperCase().charAt(0);
                        if (opcaoNome == 'S') {
                            System.out.print("- Informe o novo nome da nave inimiga: ");
                            nome = input.nextLine();
                        }
                        
                        // TIPO
                        char tipoInimigo = inimigoParaAlterar.getTipoInimigo();
                        
                        // CONFIRMAÇÃO FINAL INIMIGO
                        System.out.println("\nDeseja realmente modificar os dados informados? (s = sim / n = não)");
                        System.out.println("- Código: " + codigoInimigo);
                        System.out.println("- Nome..: " + nome);
                        System.out.println("- Tipo..: " + (tipoInimigo == 'B' ? "Boss" : "Comum"));
                        System.out.println("- Nave..: " + codigoNave);
                        System.out.print("---> (s = sim / n = não): ");
                        char confirmacaoFinalInimigo = input.nextLine().toUpperCase().charAt(0);
                        if (confirmacaoFinalInimigo == 'S') {
                            Inimigo inimigoAlterado = new Inimigo(codigoInimigo, inimigoParaAlterar.getNaveId(), nome, tipoInimigo);
                            try {
                                inimigoDAO.alterar(inimigoAlterado);
                            } catch (Exception e) {
                                return false;
                            }
                        } else {
                            System.out.println("\nInimigo mantido.");
                        }
                        
                        // CASO ELE SEJA BOSS, PERGUNTAR TAMBÉM SE QUER MODIFICAR OS DADOS DO BOSS
                        if (inimigoParaAlterar.getTipoInimigo() == 'B') {
                            System.out.print("\nAlterar também as informações do Boss associado a este Inimigo? (s = sim / n = não): ");
                            char opcaoBoss = input.nextLine().toUpperCase().charAt(0);
                            if (opcaoBoss == 'S') {
                                System.out.println("\nAlterar o registro do Boss de\n");
                                System.out.print("- Código: ");
                                long codigoBoss = input.nextLong();
                                input.nextLine(); // Consumindo quebra de linha

                                Boss bossNovo = new Boss(codigoNave, codigoInimigo, codigoBoss);
                                Boss bossParaAlterar = bossDAO.buscar(bossNovo);
                                
                                if (bossParaAlterar != null) {
                                    // FASE
                                    int fase = bossParaAlterar.getFase();
                                    System.out.println("\n - Fase atual: " + fase);
                                    System.out.print("---> Deseja alterar? (s = sim / n = não): ");
                                    char opcaoFase = input.nextLine().toUpperCase().charAt(0);
                                    if (opcaoFase == 'S') {
                                        System.out.print("- Informe a nova fase em que o boss aparece: ");
                                        fase = input.nextInt();
                                        input.nextLine(); // Consumindo quebra de linha
                                    }
                                    
                                    // CONFIRMAÇÃO FINAL BOSS
                                    System.out.println("\nDeseja realmente modificar os dados informados? (s = sim / n = não)");
                                    System.out.println("- Código.: " + codigoBoss);
                                    System.out.println("- Fase...: " + fase);
                                    System.out.println("- Inimigo: " + codigoInimigo);
                                    System.out.print("---> (s = sim / n = não): ");
                                    char confirmacaoFinalBoss = input.nextLine().toUpperCase().charAt(0);
                                    if (confirmacaoFinalBoss == 'S') {
                                        Boss bossAlterado = new Boss(codigoNave, codigoInimigo, codigoBoss, fase);
                                        try {
                                            bossDAO.alterar(bossAlterado);
                                        } catch (Exception e) {
                                            return false;
                                        }
                                    } else {
                                        System.out.println("\nBoss mantido.");
                                    }
                                    
                                }
                            }
                        }
                    }
                }
            }
        } else {
            System.err.println("\nNave não encontrada. Código inexistente.");
            return false;
        }
        
        return true;
    }

    @Override
    protected boolean excluir() {
        System.out.println("\nExcluir um registro de?");
        System.out.println("1 - Nave Geral");
        System.out.println("2 - Nave Jogadora");
        System.out.println("3 - Nave Inimiga");
        System.out.println("4 - Nave Boss");
        System.out.print("---> ");
        byte confirmacaoOpcaoExcluir = input.nextByte();
        input.nextLine(); // Consumindo quebra de linha
        
        if (confirmacaoOpcaoExcluir == 1) {
            
            System.out.println("\nExcluir o registro da Nave de\n");
            System.out.print("- Código: ");
            long codigo = input.nextLong();
            input.nextLine(); // Consumindo quebra de linha

            Nave naveParaDeletar = naveDAO.buscar(new Nave(codigo));

            if (naveParaDeletar == null) {
                System.err.println("\nNave não encontrada. Código inexistente.");
                return false;
            }
            
            if (naveParaDeletar.getTipo()== 'J') {
                long codjogadorAindaExiste = jogadorDAO.buscarCodigoComNave(codigo);
                if (codjogadorAindaExiste != -1) {
                    System.out.println("\nAVISO: Esta Nave está associada a um Jogador. Para deletá-la, remova anteriormente o Jogador associado a ela.");
                    return false;
                }
            } else {
                long codInimigoAindaExiste = inimigoDAO.buscarCodigoComNave(codigo);
                if (codInimigoAindaExiste != -1) {
                    System.out.println("\nAVISO: Esta Nave está associada a um Inimigo. Para deletá-la, remova anteriormente o Inimigo associado a ela.");
                    return false;
                }
            }
            
            System.out.println("\nDeseja realmente excluir a Nave informada? (s = sim / n = não)");
            System.out.println("- Código....: " + codigo);
            System.out.println("- Cor.......: " + naveParaDeletar.getCor());
            System.out.println("- Tipo......: " + (naveParaDeletar.getTipo() == 'J' ? "Jogador" : "Inimigo"));
            System.out.println("- Velocidade: " + naveParaDeletar.getVelocidade());
            System.out.println("- Poder.....: " + naveParaDeletar.getPoder());
            System.out.print("---> (s = sim / n = não): ");
            char confirmacaoFinal = input.nextLine().toUpperCase().charAt(0);
            if (confirmacaoFinal == 'S') {
                try {
                    naveDAO.excluir(naveParaDeletar);
                } catch (Exception e) {
                    return false;
                }
            } else {
                return false;
            }
            
        } else if (confirmacaoOpcaoExcluir == 2) {
            
            System.out.println("\nExcluir o registro da Nave Jogadora de\n");
            System.out.print("- Código: ");
            long codigo = input.nextLong();
            input.nextLine(); // Consumindo quebra de linha

            Jogador jogadorParaDeletar = jogadorDAO.buscar(new Jogador(codigo, codigo));

            if (jogadorParaDeletar == null) {
                System.err.println("\nNave Jogadora não encontrada. Código inexistente.");
                return false;
            }
            
            System.out.println("\nDeseja realmente excluir a Nave Jogadora informada? (s = sim / n = não)");
            System.out.println("- Código: " + codigo);
            System.out.println("- Nome..: " + jogadorParaDeletar.getNomeJogador());
            System.out.println("- Nave..: " + jogadorParaDeletar.getNaveId());
            System.out.print("---> (s = sim / n = não): ");
            char confirmacaoFinal = input.nextLine().toUpperCase().charAt(0);
            if (confirmacaoFinal == 'S') {
                try {
                    jogadorDAO.excluir(jogadorParaDeletar);
                } catch (Exception e) {
                    return false;
                }
            } else {
                return false;
            }
                        
        } else if (confirmacaoOpcaoExcluir == 3) {
            
            System.out.println("\nExcluir o registro da Nave Inimiga de\n");
            System.out.print("- Código: ");
            long codigo = input.nextLong();
            input.nextLine(); // Consumindo quebra de linha

            Inimigo inimigoParaDeletar = inimigoDAO.buscar(new Inimigo(codigo, codigo));

            if (inimigoParaDeletar == null) {
                System.err.println("\nNave Inimiga não encontrada. Código inexistente.");
                return false;
            }
            
            if (inimigoParaDeletar.getTipoInimigo() == 'B') {
                long codBossAindaExiste = bossDAO.buscarCodigoComInimigo(codigo);
                if (codBossAindaExiste != -1) {
                    System.out.println("\nAVISO: Esta Nave Inimiga está associada a um Boss. Para deletá-la, remova anteriormente o Boss associado a ela.");
                    return false;
                }
            }
            
            System.out.println("\nDeseja realmente excluir a Nave Inimiga informada? (s = sim / n = não)");
            System.out.println("- Código: " + codigo);
            System.out.println("- Nome..: " + inimigoParaDeletar.getNomeInimigo());
            System.out.println("- Tipo..: " + (inimigoParaDeletar.getTipoInimigo() == 'B' ? "Boss" : "Comum"));
            System.out.println("- Nave..: " + inimigoParaDeletar.getNaveId());
            System.out.print("---> (s = sim / n = não): ");
            char confirmacaoFinal = input.nextLine().toUpperCase().charAt(0);
            if (confirmacaoFinal == 'S') {
                try {
                    inimigoDAO.excluir(inimigoParaDeletar);
                } catch (Exception e) {
                    return false;
                }
            } else {
                return false;
            }
                        
        } else if (confirmacaoOpcaoExcluir == 4) {
            
            System.out.println("\nExcluir o registro da Nave Inimiga Boss de\n");
            System.out.print("- Código: ");
            long codigo = input.nextLong();
            input.nextLine(); // Consumindo quebra de linha

            Boss bossParaDeletar = bossDAO.buscar(new Boss(codigo, codigo, codigo));

            if (bossParaDeletar == null) {
                System.err.println("\nNave Inimiga Boss não encontrada. Código inexistente.");
                return false;
            }
            
            System.out.println("\nDeseja realmente excluir a Nave Inimiga Boss informada? (s = sim / n = não)");
            System.out.println("- Código: " + codigo);
            System.out.println("- Fase...: " + bossParaDeletar.getFase());
            System.out.println("- Inimigo: " + bossParaDeletar.getInimigoId());
            System.out.print("---> (s = sim / n = não): ");
            char confirmacaoFinal = input.nextLine().toUpperCase().charAt(0);
            if (confirmacaoFinal == 'S') {
                try {
                    bossDAO.excluir(bossParaDeletar);
                } catch (Exception e) {
                    return false;
                }
            } else {
                return false;
            }
                        
        } else {            
            System.err.println("\nOpção inválida.");
            return false;
        }
        
        return true;
    }
    
    @Override
    protected boolean buscar() {
        System.out.println("\nBuscar o registro de?");
        System.out.println("1 - Nave Geral");
        System.out.println("2 - Nave Jogadora");
        System.out.println("3 - Nave Inimiga");
        System.out.println("4 - Nave Boss");
        System.out.print("---> ");
        byte confirmacaoOpcaoBuscar = input.nextByte();
        input.nextLine(); // Consumindo quebra de linha
        
        if (confirmacaoOpcaoBuscar == 1) {
            // Nave Geral
            System.out.println("\nBuscar o registro de Nave de\n");
            System.out.print("- Código: ");
            long codigo = input.nextLong();
            input.nextLine(); // Consumindo quebra de linha

            Nave naveNova = new Nave(codigo);
            Nave nave = naveDAO.buscar(naveNova);
            
            if (nave != null) {
                System.out.println("\nNave encontrada:\n");
                System.out.println("+--------+-----------------+------------+------------+-------+");
                System.out.println("| CÓDIGO |       COR       |    TIPO    | VELOCIDADE | PODER |");
                System.out.println("+--------+-----------------+------------+------------+-------+");
                System.out.printf("| %6d | %15s | %10s | %10d | %5d |\n", nave.getCodigo(), nave.getCor(), nave.getTipo(), nave.getVelocidade(), nave.getPoder());
                System.out.println("+--------+-----------------+------------+------------+-------+");
            } else {
                System.err.println("\nNave não encontrada. Código inexistente.");
                return false;
            }
        } else if (confirmacaoOpcaoBuscar == 2) {
            // Nave Jogadora
            System.out.println("\nBuscar o registro de Nave Jogadora de\n");
            System.out.print("- Código: ");
            long codigo = input.nextLong();
            input.nextLine(); // Consumindo quebra de linha

            Jogador naveJogadoraNova = new Jogador(codigo, codigo);
            Jogador naveJog = jogadorDAO.buscar(naveJogadoraNova);
            
            if (naveJog != null) {
                System.out.println("\nNave Jogadora encontrada:\n");
                System.out.println("+--------+------------------+------+");
                System.out.println("| CÓDIGO |       NOME       | NAVE |");
                System.out.println("+--------+------------------+------+");
                System.out.printf("| %6d | %16s | %4d |\n", naveJog.getCodigo(), naveJog.getNomeJogador(), naveJog.getNaveId());
                System.out.println("+--------+------------------+------+");
            } else {
                System.err.println("\nNave Jogadora não encontrada. Código inexistente.");
                return false;
            }
        } else if (confirmacaoOpcaoBuscar == 3) {
            // Nave Inimiga
            System.out.println("\nBuscar o registro de Nave Inimiga de\n");
            System.out.print("- Código: ");
            long codigo = input.nextLong();
            input.nextLine(); // Consumindo quebra de linha

            Inimigo naveIniNova = new Inimigo(codigo, codigo);
            Inimigo naveIni = inimigoDAO.buscar(naveIniNova);
            
            if (naveIni != null) {
                System.out.println("\nNave Inimiga encontrada:\n");
                System.out.println("+--------+------------------+----------------+------+");
                System.out.println("| CÓDIGO |       NOME       |      TIPO      | NAVE |");
                System.out.println("+--------+------------------+----------------+------+");
                System.out.printf("| %6d | %16s | %14s | %4d |\n", naveIni.getCodigo(), naveIni.getNomeInimigo(), naveIni.getTipoInimigo(), naveIni.getNaveId());
                System.out.println("+--------+------------------+----------------+------+");
            } else {
                System.err.println("\nNave Inimiga não encontrada. Código inexistente.");
                return false;
            }
        } else if (confirmacaoOpcaoBuscar == 4) {
            // Nave Boss
            System.out.println("\nBuscar o registro de Nave Inimiga Boss de\n");
            System.out.print("- Código: ");
            long codigo = input.nextLong();
            input.nextLine(); // Consumindo quebra de linha

            Boss naveBossNova = new Boss(codigo, codigo, codigo);
            Boss naveBoss = bossDAO.buscar(naveBossNova);
            
            if (naveBoss != null) {
                System.out.println("\nNave Inimiga Boss encontrada:\n");
                System.out.println("+--------+------+---------+");
                System.out.println("| CÓDIGO | FASE | INIMIGO |");
                System.out.println("+--------+------+---------+");
                System.out.printf("| %6d | %4d | %7d |\n", naveBoss.getCodigo(), naveBoss.getFase(), naveBoss.getInimigoId());
                System.out.println("+--------+------+---------+");
            } else {
                System.err.println("\nNave Inimiga Boss não encontrada. Código inexistente.");
                return false;
            }
        } else {
            System.err.println("\nOpção inválida.");
            return false;
        }
        
        return true;
    }
    
}
