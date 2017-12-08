
package bladeofnight.entidades;

public class Inimigo extends Nave {
    
    private long codigoInimigo;
    private String nomeInimigo;
    private char tipoInimigo;
    private long naveId;

    public Inimigo(long codigoNave) {
        super(codigoNave);
    }
    
    public Inimigo(long codigoNave, long codigoInimigo) {
        super(codigoNave);
        this.codigoInimigo = codigoInimigo;
    }
    
    public Inimigo(long codigoNave, String nomeInimigo) {
        super(codigoNave);
        this.nomeInimigo = nomeInimigo;
        this.naveId = codigoNave;
    }
    
    public Inimigo(long codigoNave, String nomeInimigo, char tipoInimigo) {
        super(codigoNave);
        this.nomeInimigo = nomeInimigo;
        this.naveId = codigoNave;
        this.tipoInimigo = tipoInimigo;
    }
    
    public Inimigo(long codigoInimigo, long codigoNave, String nomeInimigo, char tipoInimigo) {
        super(codigoNave);
        this.codigoInimigo = codigoInimigo;
        this.nomeInimigo = nomeInimigo;
        this.naveId = codigoNave;
        this.tipoInimigo = tipoInimigo;
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

    public long getNaveId() {
        return naveId;
    }

    public void setNaveId(long naveId) {
        this.naveId = naveId;
    }
    
}
