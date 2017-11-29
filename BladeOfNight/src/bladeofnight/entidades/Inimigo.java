
package bladeofnight.entidades;

public class Inimigo extends Nave {
    
    private long codigoInimigo;
    private String nomeInimigo;
    private char tipoInimigo;

    public Inimigo(long codigoInimigo, String nomeInimigo, char tipoInimigo, long codigoNave, String cor, char tipo, int velocidade, int poder) {
        super(codigoNave, cor, tipo, velocidade, poder);
        this.codigoInimigo = codigoInimigo;
        this.nomeInimigo = nomeInimigo;
        this.tipoInimigo = tipoInimigo;
    }

    public Inimigo(long codigoInimigo, String nomeInimigo, char tipoInimigo, long codigoNave) {
        super(codigoNave);
        this.codigoInimigo = codigoInimigo;
        this.nomeInimigo = nomeInimigo;
        this.tipoInimigo = tipoInimigo;
    }
    
    public Inimigo(long codigoInimigo, long codigoNave) {
        super(codigoNave);
        this.codigoInimigo = codigoInimigo;
    }
    
    @Override
    public long getCodigo() {
        return codigoInimigo;
    }

    public String getNomeInimigo() {
        return nomeInimigo;
    }

    public char getTipoInimigo() {
        return tipoInimigo;
    }

    @Override
    public void setCodigo(long codigoInimigo) {
        this.codigoInimigo = codigoInimigo;
    }

    public void setNomeInimigo(String nomeInimigo) {
        this.nomeInimigo = nomeInimigo;
    }

    public void setTipoInimigo(char tipoInimigo) {
        this.tipoInimigo = tipoInimigo;
    }
    
}
