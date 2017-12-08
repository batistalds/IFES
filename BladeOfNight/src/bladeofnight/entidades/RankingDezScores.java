
package bladeofnight.entidades;

public class RankingDezScores {
    
    private long codigoDezScores;
    private long pontuacaoPlayer;

    public RankingDezScores(long codigoDezScores, long pontuacaoPlayer) {
        this.codigoDezScores = codigoDezScores;
        this.pontuacaoPlayer = pontuacaoPlayer;
    }
    
    public RankingDezScores(long codigoDezScores) {
        this.codigoDezScores = codigoDezScores;
    }

    public long getCodigo() {
        return codigoDezScores;
    }

    public long getPontuacaoPlayer() {
        return pontuacaoPlayer;
    }

    public void setCodigo(long codigoDezScores) {
        this.codigoDezScores = codigoDezScores;
    }

    public void setPontuacaoPlayer(long pontuacaoPlayer) {
        this.pontuacaoPlayer = pontuacaoPlayer;
    }
    
}
