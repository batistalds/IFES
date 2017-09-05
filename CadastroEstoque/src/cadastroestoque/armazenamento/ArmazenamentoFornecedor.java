package cadastroestoque.armazenamento;

import cadastroestoque.Entidades.Fornecedor;
import java.util.ArrayList;

public class ArmazenamentoFornecedor {

    private static ArrayList<Fornecedor> LISTA_FORNECEDOR;

    public static ArrayList<Fornecedor> getLista() {
        return LISTA_FORNECEDOR;
    }

    public static void iniciarLista() {
        LISTA_FORNECEDOR = new ArrayList<>();
    }

    public static void inserir(Fornecedor f) {
        LISTA_FORNECEDOR.add(f);
    }

    public static boolean alterar(Fornecedor f) {
        Fornecedor novoFornecedor = buscar(f);

        if (novoFornecedor != null) {
            novoFornecedor.setCnpj(f.getCnpj());
            novoFornecedor.setEmail(f.getEmail());
            novoFornecedor.setEndereco(f.getEndereco());
            novoFornecedor.setInscricaoEstadual(f.getInscricaoEstadual());
            novoFornecedor.setNomeFantasia(f.getNomeFantasia());
            novoFornecedor.setRazaoSocial(f.getRazaoSocial());
            novoFornecedor.setTelefone(f.getTelefone());
            return true;
        }

        return false;
    }

    public static boolean excluir(Fornecedor f) {
        Fornecedor fornecedorParaExcluir = buscar(f);

        if (fornecedorParaExcluir != null) {
            LISTA_FORNECEDOR.remove(fornecedorParaExcluir);
            return true;
        }

        return false;
    }

    public static Fornecedor buscar(Fornecedor f) {
        Fornecedor fornecedorProcurado = null;
        for (Fornecedor forn : LISTA_FORNECEDOR) {
            if (forn.getCodigo() == f.getCodigo()) {
                fornecedorProcurado = forn;
                break;
            }
        }
        return fornecedorProcurado;
    }
}
