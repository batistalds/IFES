
package bladeofnight.entidades;

public class Background extends Imagem implements Entidade {

    private long codigoBackground;
    private boolean movimentoX;
    private boolean movimentoY;
    private int velMoviX;
    private int velMoviY;
    
    public Background(long codigoBackground, boolean movimentoX, boolean movimentoY, int velMoviX, int velMoviY) {
        this.codigoBackground = codigoBackground;
        this.movimentoX = movimentoX;
        this.movimentoY = movimentoY;
        this.velMoviX = velMoviX;
        this.velMoviY = velMoviY;
    }
    
    public Background(boolean movimentoX, boolean movimentoY, int velMoviX, int velMoviY) {
        this.movimentoX = movimentoX;
        this.movimentoY = movimentoY;
        this.velMoviX = velMoviX;
        this.velMoviY = velMoviY;
    }
    
    public Background(long codigoBackground) {
        this.codigoBackground = codigoBackground;
    }
    
    @Override
    public long getCodigo() {
        return codigoBackground;
    }

    public boolean isMovimentoX() {
        return movimentoX;
    }

    public boolean isMovimentoY() {
        return movimentoY;
    }

    public int getVelMoviX() {
        return velMoviX;
    }

    public int getVelMoviY() {
        return velMoviY;
    }

    public void setCodigo(long codigoBackground) {
        this.codigoBackground = codigoBackground;
    }

    public void setMovimentoX(boolean movimentoX) {
        this.movimentoX = movimentoX;
    }

    public void setMovimentoY(boolean movimentoY) {
        this.movimentoY = movimentoY;
    }

    public void setVelMoviX(int velMoviX) {
        this.velMoviX = velMoviX;
    }

    public void setVelMoviY(int velMoviY) {
        this.velMoviY = velMoviY;
    }
        
}
