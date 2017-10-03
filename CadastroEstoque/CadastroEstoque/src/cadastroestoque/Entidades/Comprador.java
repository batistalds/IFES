package cadastroestoque.Entidades;

public class Comprador extends Funcionario {

    public Comprador(long codigo, String nome, long cpf, String endereco, String telefone, String email) {
        super(codigo, nome, cpf, endereco, telefone, email);
    }
    
    public Comprador(String nome) {
        super(nome);
    }

}
