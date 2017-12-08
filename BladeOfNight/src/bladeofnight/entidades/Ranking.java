
package bladeofnight.entidades;

public class Ranking implements Entidade {

    private long codigoRanking;
    private int dezPrimeirosPlayers;
    private int dezPrimeirosScores;
    private int dezPrimeirasDatas;
    private int imagemFundo;
    private int interfaceHud;

    public Ranking(long codigoRanking, int dezPrimeirosPlayers, int dezPrimeirosScores, int dezPrimeirasDatas, int imagemFundo, int interfaceHud) {
        this.codigoRanking = codigoRanking;
        this.dezPrimeirosPlayers = dezPrimeirosPlayers;
        this.dezPrimeirosScores = dezPrimeirosScores;
        this.dezPrimeirasDatas = dezPrimeirasDatas;
        this.imagemFundo = imagemFundo;
        this.interfaceHud = interfaceHud;
    }
    
    public Ranking(int dezPrimeirosPlayers, int dezPrimeirosScores, int dezPrimeirasDatas, int imagemFundo, int interfaceHud) {
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

    public int getImagemFundo() {
        return imagemFundo;
    }

    public int getInterfaceHud() {
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

    public void setImagemFundo(int imagemFundo) {
        this.imagemFundo = imagemFundo;
    }

    public void setInterfaceHud(int interfaceHud) {
        this.interfaceHud = interfaceHud;
    }
}
