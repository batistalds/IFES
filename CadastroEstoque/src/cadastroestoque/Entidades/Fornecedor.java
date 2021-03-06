package cadastroestoque.Entidades;

public class Fornecedor {

    private long codigo;
    private String nomeFantasia;
    private String razaoSocial;
    private String endereco;
    private long cnpj;
    private long inscricaoEstadual;
    private String telefone;
    private String email;

    public Fornecedor(long codigo, String nomeFantasia, String razaoSocial, String endereco, long cnpj,
            long inscricaoEstadual, String telefone, String email) {
        this.codigo = codigo;
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.endereco = endereco;
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
        this.telefone = telefone;
        this.email = email;
    }

    public Fornecedor(long codigo) {
        this.codigo = codigo;
    }

    public long getCodigo() {
        return codigo;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getEndereco() {
        return endereco;
    }

    public long getCnpj() {
        return cnpj;
    }

    public long getInscricaoEstadual() {
        return inscricaoEstadual;
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

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setCnpj(long cnpj) {
        this.cnpj = cnpj;
    }

    public void setInscricaoEstadual(long inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        if (email.indexOf("@") != -1 && email.contains(".com")) {
            this.email = email;
        }
    }

}
