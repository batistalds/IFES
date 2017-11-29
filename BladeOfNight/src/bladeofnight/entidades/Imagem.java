
package bladeofnight.entidades;

public abstract class Imagem {
    
    protected long codigoImagem;
    protected String formato;
    protected String nome;
    protected double peso;

    public long getCodigoImagem() {
        return codigoImagem;
    }

    public String getFormato() {
        return formato;
    }

    public String getNome() {
        return nome;
    }

    public double getPeso() {
        return peso;
    }

    public void setCodigoImagem(long codigoImagem) {
        this.codigoImagem = codigoImagem;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

}
