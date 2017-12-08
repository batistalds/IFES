
package bladeofnight.entidades;

public class Jogador extends Nave {
    
    private long codigoJogador;
    private String nomeJogador;
    private long naveId;

    public Jogador(long codigoNave) {
        super(codigoNave);
    }
    
    public Jogador(long codigoNave, long codigoJogador) {
        super(codigoNave);
        this.codigoJogador = codigoJogador;
    }
    
    public Jogador(long codigoNave, String nomeJogador) {
        super(codigoNave);
        this.nomeJogador = nomeJogador;
        this.naveId = codigoNave;
    }
    
    public Jogador(long codigoJogador, long codigoNave, String nomeJogador) {
        super(codigoNave);
        this.codigoJogador = codigoJogador;
        this.nomeJogador = nomeJogador;
        this.naveId = codigoNave;
    }

    @Override
    public long getCodigo() {
        return codigoJogador;
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    @Override
    public void setCodigo(long codigoJogador) {
        this.codigoJogador = codigoJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public long getNaveId() {
        return naveId;
    }

    public void setNaveId(long naveId) {
        this.naveId = naveId;
    }

}
