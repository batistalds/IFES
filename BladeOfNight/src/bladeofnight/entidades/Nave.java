
package bladeofnight.entidades;

public class Nave extends Imagem implements Entidade {
    
    private long codigoNave;
    private String cor;
    private char tipo;
    private int velocidade;
    private int poder;

    public Nave(long codigoNave, String cor, char tipo, int velocidade, int poder) {
        this.codigoNave = codigoNave;
        this.cor = cor;
        this.tipo = tipo;
        this.velocidade = velocidade;
        this.poder = poder;
    }

    public Nave(long codigoNave) {
        this.codigoNave = codigoNave;
    }

    @Override
    public long getCodigo() {
        return codigoNave;
    }

    public String getCor() {
        return cor;
    }

    public char getTipo() {
        return tipo;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public int getPoder() {
        return poder;
    }

    public void setCodigo(long codigoNave) {
        this.codigoNave = codigoNave;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public void setPoder(int poder) {
        this.poder = poder;
    }
    
}
