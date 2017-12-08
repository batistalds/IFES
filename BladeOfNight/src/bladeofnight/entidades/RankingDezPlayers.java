
package bladeofnight.entidades;

public class RankingDezPlayers {
    
    private long codigoDezPlayers;
    private String nomePlayer;

    public RankingDezPlayers(long codigoDezPlayers, String nomePlayer) {
        this.codigoDezPlayers = codigoDezPlayers;
        this.nomePlayer = nomePlayer;
    }

    public RankingDezPlayers(long codigoDezPlayers) {
        this.codigoDezPlayers = codigoDezPlayers;
    }

    public RankingDezPlayers(String nomePlayer) {
        this.nomePlayer = nomePlayer;
    }

    public long getCodigo() {
        return codigoDezPlayers;
    }

    public String getNomePlayer() {
        return nomePlayer;
    }

    public void setCodigo(long codigoDezPlayers) {
        this.codigoDezPlayers = codigoDezPlayers;
    }

    public void setNomePlayer(String nomePlayer) {
        this.nomePlayer = nomePlayer;
    }
}
