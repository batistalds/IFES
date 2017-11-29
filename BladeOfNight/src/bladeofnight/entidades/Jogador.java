
package bladeofnight.entidades;

public class Jogador extends Nave {
    
    private long codigoJogador;
    private String nomeJogador;

    public Jogador(long codigoJogador, String nomeJogador, long codigoNave, String cor, char tipo, int velocidade, int poder) {
        super(codigoNave, cor, tipo, velocidade, poder);
        this.codigoJogador = codigoJogador;
        this.nomeJogador = nomeJogador;
    }

    public Jogador(long codigoJogador, String nomeJogador, long codigoNave) {
        super(codigoNave);
        this.codigoJogador = codigoJogador;
        this.nomeJogador = nomeJogador;
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

}
