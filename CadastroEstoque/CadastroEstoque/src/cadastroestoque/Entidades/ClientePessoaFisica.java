package cadastroestoque.Entidades;

import java.util.Date;

public class ClientePessoaFisica extends Cliente {

    private long cpf;
    private char sexo;
    private String nome;
    private Date dataNascimento;

    public ClientePessoaFisica(long codigo, String endereco, String telefone, String email,
            long cpf, char sexo, String nome, Date dataNascimento) {
        super(codigo, endereco, telefone, email);
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.nome = nome;
        this.sexo = sexo;
    }
    
    public ClientePessoaFisica(long codigo) {
        super(codigo);
    }

    public long getCpf() {
        return cpf;
    }

    public char getSexo() {
        return sexo;
    }

    public String getNome() {
        return nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

}
