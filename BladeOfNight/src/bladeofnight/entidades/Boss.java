
package bladeofnight.entidades;

public class Boss extends Inimigo {
    
    private long codigoBoss;
    private int fase;
    private long inimigoId;

    public Boss(long codigoNave, long codigoInimigo, long codigoBoss, int fase) {
        super(codigoNave, codigoInimigo);
        this.codigoBoss = codigoBoss;
        this.fase = fase;
        this.inimigoId = codigoInimigo;
    }
    
    public Boss(long codigoNave, long codigoInimigo, long codigoBoss) {
        super(codigoNave, codigoInimigo);
        this.codigoBoss = codigoBoss;
    }
    
    public Boss(long codigoNave, long codigoInimigo, int fase) {
        super(codigoNave, codigoInimigo);
        this.fase = fase;
        this.inimigoId = codigoInimigo;
    }
    
    public Boss(long codigoNave) {
        super(codigoNave);
    }

    public Boss(long codigoNave, long codigoInimigo) {
        super(codigoNave, codigoInimigo);
    }

    public Boss(long codigoNave, String nomeInimigo) {
        super(codigoNave, nomeInimigo);
    }

    public Boss(long codigoNave, String nomeInimigo, char tipoInimigo) {
        super(codigoNave, nomeInimigo, tipoInimigo);
    }

    public Boss(long codigoInimigo, long codigoNave, String nomeInimigo, char tipoInimigo) {
        super(codigoInimigo, codigoNave, nomeInimigo, tipoInimigo);
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

    public long getInimigoId() {
        return inimigoId;
    }

    public void setInimigoId(long inimigoId) {
        this.inimigoId = inimigoId;
    }

}
