
package bladeofnight.entidades;

public class Boss extends Inimigo {
    
    private long codigoBoss;
    private int fase;

    public Boss(long codigoBoss, int fase, long codigoInimigo, String nomeInimigo, char tipoInimigo, long codigoNave, String cor, char tipo, int velocidade, int poder) {
        super(codigoInimigo, nomeInimigo, tipoInimigo, codigoNave, cor, tipo, velocidade, poder);
        this.codigoBoss = codigoBoss;
        this.fase = fase;
    }

    public Boss(long codigoBoss, int fase, long codigoInimigo, String nomeInimigo, char tipoInimigo, long codigoNave) {
        super(codigoInimigo, nomeInimigo, tipoInimigo, codigoNave);
        this.codigoBoss = codigoBoss;
        this.fase = fase;
    }

    @Override
    public long getCodigo() {
        return codigoBoss;
    }

    public int getFase() {
        return fase;
    }

    @Override
    public void setCodigo(long codigoBoss) {
        this.codigoBoss = codigoBoss;
    }

    public void setFase(int fase) {
        this.fase = fase;
    }

}
