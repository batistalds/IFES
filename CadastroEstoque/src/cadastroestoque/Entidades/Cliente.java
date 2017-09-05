package cadastroestoque.Entidades;

public class Cliente {

    private long codigo;
    private String endereco;
    private String telefone;
    private String email;

    public Cliente(long codigo, String endereco, String telefone, String email) {
        this.codigo = codigo;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }

    public long getCodigo() {
        return codigo;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
