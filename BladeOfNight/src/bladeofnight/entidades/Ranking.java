
package bladeofnight.entidades;

public class Ranking implements Entidade {

    private long codigoRanking;
    private int dezPrimeirosPlayers;
    private int dezPrimeirosScores;
    private int dezPrimeirasDatas;
    private Background imagemFundo;
    private HUD interfaceHud;

    public Ranking(long codigoRanking, int dezPrimeirosPlayers, int dezPrimeirosScores, int dezPrimeirasDatas, Background imagemFundo, HUD interfaceHud) {
        this.codigoRanking = codigoRanking;
        this.dezPrimeirosPlayers = dezPrimeirosPlayers;
        this.dezPrimeirosScores = dezPrimeirosScores;
        this.dezPrimeirasDatas = dezPrimeirasDatas;
        this.imagemFundo = imagemFundo;
        this.interfaceHud = interfaceHud;
    }

    public Ranking(long codigoRanking) {
        this.codigoRanking = codigoRanking;
    }
    
    @Override
    public long getCodigo() {
        return codigoRanking;
    }

    public int getDezPrimeirosPlayers() {
        return dezPrimeirosPlayers;
    }

    public int getDezPrimeirosScores() {
        return dezPrimeirosScores;
    }

    public int getDezPrimeirasDatas() {
        return dezPrimeirasDatas;
    }

    public Background getImagemFundo() {
        return imagemFundo;
    }

    public HUD getInterfaceHud() {
        return interfaceHud;
    }

    public void setCodigo(long codigoRanking) {
        this.codigoRanking = codigoRanking;
    }

    public void setDezPrimeirosPlayers(int dezPrimeirosPlayers) {
        this.dezPrimeirosPlayers = dezPrimeirosPlayers;
    }

    public void setDezPrimeirosScores(int dezPrimeirosScores) {
        this.dezPrimeirosScores = dezPrimeirosScores;
    }

    public void setDezPrimeirasDatas(int dezPrimeirasDatas) {
        this.dezPrimeirasDatas = dezPrimeirasDatas;
    }

    public void setImagemFundo(Background imagemFundo) {
        this.imagemFundo = imagemFundo;
    }

    public void setInterfaceHud(HUD interfaceHud) {
        this.interfaceHud = interfaceHud;
    }
}
